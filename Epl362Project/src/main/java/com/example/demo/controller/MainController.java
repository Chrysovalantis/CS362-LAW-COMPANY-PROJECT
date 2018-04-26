/**
 * 
 */
package com.example.demo.controller;

/**
 * @author Chrysovalantis
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.form.CaseHistoryForm;
import com.example.demo.form.ChangeRequestForm;
import com.example.demo.form.PersonForm;
import com.example.demo.model.Apointment;
import com.example.demo.model.Branch;
import com.example.demo.model.CaseHistory;
import com.example.demo.model.ChangeRequest;
import com.example.demo.model.Client;
import com.example.demo.model.ClientCase;
import com.example.demo.model.Desagrement;
import com.example.demo.model.Person;
import com.example.demo.model.Recommendation;
import com.example.demo.model.Staff;

@Controller
public class MainController extends AllRepositories {

	private static List<Person> persons = new ArrayList<Person>();

	static {
		persons.add(new Person("Bill", "Gates"));
		persons.add(new Person("Steve", "Jobs"));
	}

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	@RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
	public String personList(Model model) {

		return "personList";
	}

	@GetMapping(value = { "/loadDefaults" })
	public String loadDefaults(Model model) {
		model.addAttribute("prints", loadDef());
		return "ok";
	}

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
				chform.legalOpinionDetails = legalRep.findById(chform.legalOpinionId).get().getType()+": "+chform.legalOpinionDetails; 
				chform.recomantationDetails = recomRep.findById(chform.recomandationId).get().getType()+": "+chform.recomantationDetails; 

				caseH.add(chform);
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

	@RequestMapping(value = { "/appointments" }, method = RequestMethod.GET)
	public String receptionist(Model model) {

		model.addAttribute("appointments", apointmentsRep.findAll());
		model.addAttribute("appointmentsNumber", apointmentsRep.count());

		return "appointments";
	}

	@RequestMapping(value = { "/legal_appointments" }, method = RequestMethod.GET)
	public String legal_appointments(Model model) {

		model.addAttribute("appointments", apointmentsRep.findAll());
		model.addAttribute("appointmentsNumber", apointmentsRep.count());

		return "legal_appointments";
	}

	@RequestMapping(value = { "/head_office" }, method = RequestMethod.GET)
	public String head_office(Model model) {

		return "head_office";
	}

	@RequestMapping(value = { "/newAppointment" }, method = RequestMethod.GET)
	public String newAppointment(Model model) {

		model.addAttribute("staffs", staffRep.findAll());
		model.addAttribute("branches", branchRep.findAll());
		model.addAttribute("cases", caseHistoryRep.findAll());

		return "newAppointment";
	}

	@RequestMapping(value = { "/addInfo" }, method = RequestMethod.GET)
	public String addInfo(Model model) {

		model.addAttribute("recommendations", recomRep.findAll());
		model.addAttribute("branches", branchRep.findAll());
		model.addAttribute("caseTypes", caseTypeRep.findAll());
		model.addAttribute("clients", clientRep.findAll());
		model.addAttribute("legalOpinions", legalRep.findAll());

		return "addInfo";
	}

	@RequestMapping(value = { "/clientss" }, method = RequestMethod.GET)
	public String clientss(Model model) {

		model.addAttribute("customers", clientRep.findAll());
		model.addAttribute("clientsNumber", clientRep.count());

		return "clientss";
	}

	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {

		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);

		return "addPerson";
	}

	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {

		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();

		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			Person newPerson = new Person(firstName, lastName);
			persons.add(newPerson);

			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addPerson";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public RedirectView login(String username, String password) {
		Iterable<Staff> users = staffRep.findAll();
		for (Staff user : users) {
			System.out.println(user);
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
					return new RedirectView("consultation");
				}
				if (role.compareTo(Staff.LEGAL_STAFF) == 0) {
					return new RedirectView("consultation");
				}
				if (role.compareTo(Staff.RECEPTIONIST) == 0) {
					return new RedirectView("consultation");
				}
				if (role.compareTo(Staff.HEAD_OFFICE) == 0) {
					return new RedirectView("consultation");
				}
			}
		}

		// TODO add error
		return new RedirectView("");

	}

	@RequestMapping(value = { "/changeRequestLO" }, method = RequestMethod.GET)
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
		return "changeRequests";
	}

	@RequestMapping(value = {
			"/processRequest" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RedirectView processRequest(@RequestParam MultiValueMap<String, String> paramMap) {
		for (String val : paramMap.keySet()) {
			System.out.println("Key: " + val + " Value: " + paramMap.get(val));
		}
		Optional<ChangeRequest> temp = changeRequestRepository.findById(Long.parseLong(paramMap.get("id").get(0)));
		ChangeRequest chr = temp.get();
		System.out.println(paramMap.get("button").toString());
		if (paramMap.get("button").toString().equals("[Aprove]")) {
			System.out.println("Aprove");
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
		return new RedirectView("/changeRequestLO");
	}

	@RequestMapping(value = { "/reports" }, method = RequestMethod.GET)
	public String reports(Model model) {

		return "reports";
	}
}