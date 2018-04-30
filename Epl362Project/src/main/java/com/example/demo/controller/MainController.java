/**
 * 
 */
package com.example.demo.controller;

/**
 * @author Chrysovalantis
 *
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.form.BranchReport;
import com.example.demo.form.CaseHistoryForm;
import com.example.demo.form.ChangeRequestForm;
import com.example.demo.form.ClientCaseReport;
import com.example.demo.form.PersonForm;
import com.example.demo.model.Apointment;
import com.example.demo.model.Branch;
import com.example.demo.model.CaseHistory;
import com.example.demo.model.ChangeRequest;
import com.example.demo.model.Client;
import com.example.demo.model.ClientCase;
import com.example.demo.model.Desagrement;
import com.example.demo.model.Recommendation;
import com.example.demo.model.Staff;

@Controller
public class MainController extends AllRepositories {

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	/**
	 * Main login form
	 * 
	 * @param model
	 *            the model
	 * @return the index template
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	/**
	 * The case history view for similar cases
	 * 
	 * @param model
	 *            the model
	 * @return the case history template
	 */
	@RequestMapping(value = { "/case_history/{staffId}" }, method = RequestMethod.GET)
	public String caseHistory(Model model, @PathVariable(value = "staffId") Long staffId) {

		ArrayList<ClientCaseReport> clientCases = new ArrayList<>();

		for (ClientCase c : clientCaseRep.findAll()) {
			ClientCaseReport r = new ClientCaseReport();
			r.id = c.getId();
			Client clien = clientRep.findById(c.getClientId()).get();
			r.name = clien.getName() + " " + clien.getSurname();
			r.type = caseTypeRep.findById(c.getCaseTypeId()).get().getType();
			clientCases.add(r);
		}

		model.addAttribute("casesT", clientCases);
		model.addAttribute("staffId", staffId);

		return "case_history";
	}

	/**
	 * Generate default testing data in database
	 * 
	 * @param model
	 *            the model
	 * @return the ok template with the info of ids created
	 */
	@GetMapping(value = { "/loadDefaults" })
	public String loadDefaults(Model model) {
		model.addAttribute("prints", loadDef());
		return "ok";
	}

	/**
	 * Show the consultation view base on an appointment id
	 * 
	 * @param model
	 *            the model
	 * @param apointmentId
	 *            the appointment id
	 * @return the consultation template
	 */
	@RequestMapping(value = { "/consultation/{apointmentId}" }, method = RequestMethod.GET)
	public String consultation(Model model, @PathVariable(value = "apointmentId") Long apointmentId) {
		Apointment apoi = null;
		try {
			apoi = apointmentsRep.findById(apointmentId).get();
		} catch (Exception e) {
			return "index";
		}
		Long caseId = apoi.getCaseId();
		Long branchId = apoi.getBranchID();
		Long staffId = apoi.getWithWhoStaffId();
		ClientCase cs = clientCaseRep.findById(caseId).get();
		Client cl = clientRep.findById(cs.getClientId()).get();

		Branch br = branchRep.findById(branchId).get();
		Staff st = staffRep.findById(staffId).get();

		ArrayList<CaseHistoryForm> caseH = new ArrayList<>();
		for (CaseHistory ch : caseHistoryRep.findAll()) {
			if (ch != null && ch.getCaseId() != null && ch.getCaseId().equals(caseId)) {
				Staff s = staffRep.findById(ch.getStaffId()).get();
				CaseHistoryForm chform = new CaseHistoryForm(ch, s);
				if (chform.legalOpinionId != 0) {
					chform.legalOpinionDetails = legalRep.findById(chform.legalOpinionId).get().getType() + ": "
							+ chform.legalOpinionDetails;
				}
				if (chform.recomandationId != 0) {
					chform.recomantationDetails = recomRep.findById(chform.recomandationId).get().getType() + ": "
							+ chform.recomantationDetails;
					caseH.add(chform);
				}
			}
		}
		ArrayList<Recommendation> recoms = new ArrayList<>();
		for (Recommendation r : recomRep.findAll()) {
			boolean desagr = false;
			boolean overuled = false;
			String overuledBy = "";
			for (Desagrement d : desagrementRep.findAll()) {
				if (d.getClientId().equals(cl.getId()) && d.getRecomandationId().equals(r.getId())) {
					desagr = true;

					if (d.getOverruled()) {
						overuled = true;
						overuledBy = d.getOverruledByStaffId() + "";
					}
					break;
				}
			}
			String add = "";
			if (desagr) {
				add += " (Desagriment)";
			}
			if (overuled) {
				add += " (Overuled By: " + overuledBy + ")";
			}
			r.setType(r.getType() + add);
			recoms.add(r);
		}
		String dangerous = "";
		if (cl.isPotentialMoneyLaundring()) {
			dangerous += " (DANGEROUS)";
		}

		model.addAttribute("recommendations", recoms);
		model.addAttribute("staffName", st.getName());
		model.addAttribute("staffId", staffId);
		model.addAttribute("caseHistory", caseH);
		model.addAttribute("caseHistoryNumber", caseH.size());
		model.addAttribute("caseId", caseId);
		model.addAttribute("apoiDateCreated", apoi.getDateCreated());
		model.addAttribute("apoiDate", apoi.getDate());
		model.addAttribute("branchName", br.getName());
		model.addAttribute("apoiDate", apoi.getDate());
		model.addAttribute("legalOpinions", legalRep.findAll());
		model.addAttribute("apointmentId", apointmentId);
		model.addAttribute("clientName", cl.getName() + " " + cl.getSurname() + dangerous);

		return "consultation";
	}

	/**
	 * List all the appointments coming
	 * 
	 * @param model
	 *            the model
	 * @return the appointments template
	 */
	@RequestMapping(value = { "/appointments" }, method = RequestMethod.GET)
	public String receptionist(Model model) {

		ArrayList<Apointment> apos = new ArrayList<>();

		for (Apointment a : apointmentsRep.findAll()) {

			if (!a.isAttented() && a.getDate().after(new Date())) {
				apos.add(a);
			}

		}
		model.addAttribute("appointments", apos);
		model.addAttribute("appointmentsNumber", apos.size());

		return "appointments";
	}

	/**
	 * The appointments of a particular legal staff
	 * 
	 * @param model
	 *            the model
	 * @return legal appointments template
	 */
	@RequestMapping(value = { "/legal_appointments/{staffId}" }, method = RequestMethod.GET)
	public String legal_appointments(Model model, @PathVariable(value = "staffId") Long staffId) {

		ArrayList<Apointment> apos = new ArrayList<>();
		ArrayList<Apointment> mised = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Date now = cal.getTime();
		cal.add(Calendar.DATE, -2);
		Date lastTwoDays = cal.getTime();

		for (Apointment a : apointmentsRep.findAll()) {
			if (a.getWithWhoStaffId() != staffId) {
				continue;
			}
			cal.setTime(a.getDate());
			if (!a.isAttented() && cal.getTime().after(now)) {
				apos.add(a);
			}

			if (!a.isAttented() && cal.getTime().before(now) && cal.getTime().after(lastTwoDays)) {
				mised.add(a);
			}
		
		}
		model.addAttribute("appointments", apos);
		model.addAttribute("appointmentsNumber", apos.size());

		model.addAttribute("staffId", staffId);

		model.addAttribute("missed", mised);
		model.addAttribute("missedNum", mised.size());

		return "legal_appointments";
	}

	/**
	 * The head office view
	 * 
	 * @param model
	 *            the model
	 * @return the head office template
	 */
	@RequestMapping(value = { "/head_office" }, method = RequestMethod.GET)
	public String head_office(Model model) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -30);

		Date lastMonth = cal.getTime();

		cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -7);

		Date lastWeek = cal.getTime();

		HashMap<Long, HashMap<Long, String>> casePerBranch = new HashMap<>();

		for (Apointment a : apointmentsRep.findAll()) {
			if (a.getDate().before(lastMonth)) {
				continue;
			}
			HashMap<Long, String> temp = casePerBranch.get(a.getBranchID());
			if (temp == null) {
				temp = new HashMap<>();

			}
			ClientCase ccc = clientCaseRep.findById(a.getCaseId()).get();
			temp.put(ccc.getClientId(), "");
			casePerBranch.put(a.getBranchID(), temp);
		}
		ArrayList<BranchReport> br = new ArrayList<>();
		for (java.util.Map.Entry<Long, HashMap<Long, String>> e : casePerBranch.entrySet()) {
			BranchReport b = new BranchReport();
			b.id = e.getKey();
			b.name = branchRep.findById(e.getKey()).get().getName();
			b.total = e.getValue().size();
			br.add(b);
		}
		model.addAttribute("branches", br);

		HashMap<Long, Integer> legalOpinionPerBranch = new HashMap<>();

		for (CaseHistory c : caseHistoryRep.findAll()) {
			if (c.getLegalOpinionId() == null || c.getDate().before(lastMonth)) {
				continue;
			}
			Integer temp = legalOpinionPerBranch.get(c.getLegalOpinionId());
			if (temp == null) {
				temp = new Integer(1);
			} else {
				temp = new Integer(temp + 1);
			}
			legalOpinionPerBranch.put(c.getLegalOpinionId(), temp);
		}
		ArrayList<BranchReport> legalOpinions = new ArrayList<>();
		for (java.util.Map.Entry<Long, Integer> e : legalOpinionPerBranch.entrySet()) {
			BranchReport b = new BranchReport();
			b.id = e.getKey();
			b.name = legalRep.findById(e.getKey()).get().getType();
			b.total = e.getValue();
			legalOpinions.add(b);
		}
		model.addAttribute("legalOpinions", legalOpinions);

		HashMap<String, HashMap<Long, String>> casePerBranchPerDay = new HashMap<>();

		for (Apointment a : apointmentsRep.findAll()) {
			if (a.getDate().before(lastWeek)) {
				continue;
			}
			@SuppressWarnings("deprecation")
			String key = a.getBranchID() + "@" + a.getDate().getDate() + "/" + (a.getDate().getMonth() + 1) + "/"
					+ (a.getDate().getYear()+1900);
			HashMap<Long, String> temp = casePerBranchPerDay.get(key);
			if (temp == null) {
				temp = new HashMap<>();

			}
			ClientCase ccc = clientCaseRep.findById(a.getCaseId()).get();
			temp.put(ccc.getClientId(), "");
			casePerBranchPerDay.put(key, temp);
		}
		ArrayList<BranchReport> brD = new ArrayList<>();
		for (java.util.Map.Entry<String, HashMap<Long, String>> e : casePerBranchPerDay.entrySet()) {
			BranchReport b = new BranchReport();
			String[] temp = e.getKey().split("@");
			b.id = Long.parseLong(temp[0]);
			b.name = branchRep.findById(Long.parseLong(temp[0])).get().getName();
			b.total = e.getValue().size();
			b.day = temp[1];
			brD.add(b);
		}
		model.addAttribute("branchesD", brD);

		HashMap<Long, Integer> recommendationPerBranch = new HashMap<>();

		for (CaseHistory c : caseHistoryRep.findAll()) {
			if (c.getRecomandationId() == null || c.getDate().before(lastMonth)) {
				continue;
			}
			Integer temp = recommendationPerBranch.get(c.getRecomandationId());
			if (temp == null) {
				temp = new Integer(1);
			} else {
				temp = new Integer(temp + 1);
			}
			recommendationPerBranch.put(c.getRecomandationId(), temp);
		}
		ArrayList<BranchReport> recommendation = new ArrayList<>();
		for (java.util.Map.Entry<Long, Integer> e : recommendationPerBranch.entrySet()) {
			BranchReport b = new BranchReport();
			b.id = e.getKey();
			b.name = recomRep.findById(e.getKey()).get().getType();
			b.total = e.getValue();
			recommendation.add(b);
		}
		model.addAttribute("recommendations", recommendation);

		HashMap<Long, Integer> casesPerBranch = new HashMap<>();

		for (CaseHistory c : caseHistoryRep.findAll()) {
			ClientCase clC = clientCaseRep.findById(c.getCaseId()).get();
			if (clC.getCaseTypeId() == null || c.getDate().before(lastMonth)) {
				continue;
			}
			Integer temp = casesPerBranch.get(clC.getCaseTypeId());
			if (temp == null) {
				temp = new Integer(1);
			} else {
				temp = new Integer(temp + 1);
			}
			casesPerBranch.put(clC.getCaseTypeId(), temp);
		}
		ArrayList<BranchReport> consultations = new ArrayList<>();
		for (java.util.Map.Entry<Long, Integer> e : casesPerBranch.entrySet()) {
			BranchReport b = new BranchReport();
			b.id = e.getKey();
			b.name = caseTypeRep.findById(e.getKey()).get().getType();
			b.total = e.getValue();
			consultations.add(b);
		}
		model.addAttribute("consultations", consultations);

		return "head_office";
	}

	/**
	 * Show the new appointment view
	 * 
	 * @param model
	 *            the model
	 * @return new appointment template
	 */
	@RequestMapping(value = { "/newAppointment" }, method = RequestMethod.GET)
	public String newAppointment(Model model) {
		ArrayList<Staff> st = new ArrayList<>();
		for (Staff s : staffRep.findAll()) {
			if (s.getRole().equals(Staff.LEGAL_STAFF))
				st.add(s);
		}
		model.addAttribute("staffs", st);
		model.addAttribute("branches", branchRep.findAll());
		model.addAttribute("cases", clientCaseRep.findAll());

		return "newAppointment";
	}

	/**
	 * Show the add info view
	 * 
	 * @param model
	 *            the model
	 * @return add info template
	 */
	@RequestMapping(value = { "/addInfo" }, method = RequestMethod.GET)
	public String addInfo(Model model) {

		model.addAttribute("recommendations", recomRep.findAll());
		model.addAttribute("branches", branchRep.findAll());
		model.addAttribute("caseTypes", caseTypeRep.findAll());
		ArrayList<Client> cl = new ArrayList<>();
		for (Client c : clientRep.findAll()) {
			if (c.isLocked()) {
				continue;
			}
			cl.add(c);
		}
		model.addAttribute("clientsAll", clientRep.findAll());

		model.addAttribute("clients", cl);
		model.addAttribute("legalOpinions", legalRep.findAll());

		return "addInfo";
	}

	/**
	 * Show all the clients in the database
	 * 
	 * @param model
	 *            the model
	 * @return the clients template
	 */
	@RequestMapping(value = { "/clientss" }, method = RequestMethod.GET)
	public String clientss(Model model) {

		model.addAttribute("customers", clientRep.findAll());
		model.addAttribute("clientsNumber", clientRep.count());

		return "clientss";
	}

	/**
	 * Show the add person template
	 * 
	 * @param model
	 *            the model
	 * @return the add person template
	 */
	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {

		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);

		return "addPerson";
	}

	/**
	 * The login controller
	 * 
	 * @param username
	 *            the user to be logged in
	 * @param password
	 *            its corresponding password
	 * @return the correct template based on role or if not exist at the login view
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public RedirectView login(String username, String password) {
		Iterable<Staff> users = staffRep.findAll();
		for (Staff user : users) {
			String uUsername = user.getUsername();
			String uPassword = user.getPassword();
			if (uUsername == null || uPassword == null) {
				continue;
			}
			if (uUsername.equals(username) && uPassword.equals(password)) {
				String role = user.getRole();
				if (role == null) {
					continue;
				}
				if (role.compareTo(Staff.LEGAL_OFFICE) == 0) {
					return new RedirectView("legalOffice");
				}
				if (role.compareTo(Staff.LEGAL_STAFF) == 0) {
					return new RedirectView("legal_appointments/" + user.getId());
				}
				if (role.compareTo(Staff.RECEPTIONIST) == 0) {
					return new RedirectView("appointments");
				}
				if (role.compareTo(Staff.HEAD_OFFICE) == 0) {
					return new RedirectView("head_office");
				}
			}
		}
		return new RedirectView("");

	}

	/**
	 * Show the view of the legal office
	 * 
	 * @param model
	 *            the model
	 * @return the change requests template
	 */
	@RequestMapping(value = { "/legalOffice" }, method = RequestMethod.GET)
	public String changeRequest(Model model) {
		Iterable<ChangeRequest> chrs = changeRequestRepository.findAll();
		ArrayList<ChangeRequestForm> chReqs = new ArrayList<>();
		for (ChangeRequest chr : chrs) {
			if (chr != null && chr.getState() != null && chr.getState().equals(ChangeRequest.UNPROSESED)) {
				Client cl = clientRep.findById(chr.getClientId()).get();
				ChangeRequestForm nChrForm = new ChangeRequestForm(chr, cl);
				chReqs.add(nChrForm);

			}
		}
		model.addAttribute("changeRequests", chReqs);
		return "legalOffice";
	}

	/**
	 * Accept or decline a change request
	 * 
	 * @param paramMap
	 *            the input form
	 * @return the change requests template
	 */
	@RequestMapping(value = {
			"/processRequest" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RedirectView processRequest(@RequestParam MultiValueMap<String, String> paramMap) {
	
		Optional<ChangeRequest> temp = changeRequestRepository.findById(Long.parseLong(paramMap.get("id").get(0)));
		ChangeRequest chr = temp.get();
		if (paramMap.get("button").toString().equals("[Aprove]")) {
			Client updatedClient = clientRep.findById(chr.getClientId()).get();
			if (chr.isDeleted()) {
				updatedClient.setLocked(true);
			} else {
				updatedClient.setName(chr.getNewName());
				updatedClient.setPotentialMoneyLaundring(chr.isNewPotentialMoneyLaundring());
				updatedClient.setSurname(chr.getNewSurname());
				clientRep.save(updatedClient);
			}
		}

		chr.setState(ChangeRequest.PROSESED);
		changeRequestRepository.save(chr);
		return new RedirectView("/legalOffice");
	}

	/**
	 * Show the reports to the head office
	 * 
	 * @param model
	 *            the model
	 * @return the reports template
	 */
	@RequestMapping(value = { "/reports" }, method = RequestMethod.GET)
	public String reports(Model model) {

		return "reports";
	}

	@RequestMapping(value = { "/case-history/{caseId}" }, method = RequestMethod.GET)
	public String caseHistoryById(Model model, @PathVariable(value = "caseId") Long caseId) {
		ArrayList<CaseHistoryForm> caseHistory = new ArrayList<>();
		for (CaseHistory ch : caseHistoryRep.findAll()) {
			if (ch != null && ch.getCaseId() != null && ch.getCaseId().equals(caseId)) {
				Staff s = staffRep.findById(ch.getStaffId()).get();
				CaseHistoryForm chform = new CaseHistoryForm(ch, s);
				chform.legalOpinionDetails = legalRep.findById(chform.legalOpinionId).get().getType() + ": "
						+ chform.legalOpinionDetails;
				chform.recomantationDetails = recomRep.findById(chform.recomandationId).get().getType() + ": "
						+ chform.recomantationDetails;

				caseHistory.add(chform);
			}
		}

		model.addAttribute("caseHistory", caseHistory);
		model.addAttribute("caseHistoryNumber", caseHistory.size());

		return "caseHistory";
	}

}