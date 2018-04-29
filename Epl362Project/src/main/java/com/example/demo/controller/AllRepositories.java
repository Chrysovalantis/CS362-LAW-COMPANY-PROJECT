package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Apointment;
import com.example.demo.model.Branch;
import com.example.demo.model.CaseHistory;
import com.example.demo.model.CaseType;
import com.example.demo.model.Client;
import com.example.demo.model.ClientCase;
import com.example.demo.model.Desagrement;
import com.example.demo.model.LegalOpinion;
import com.example.demo.model.Recommendation;
import com.example.demo.model.Staff;
import com.example.demo.model.repositorys.*;

public class AllRepositories {
	@Autowired
	public LegalOpinionRepository legalRep;

	@Autowired
	public RecommendationRepository recomRep;

	@Autowired
	public ApointmentRepository apointmentsRep;

	@Autowired
	public BranchRepository branchRep;
	@Autowired
	public CaseHistoryRepository caseHistoryRep;

	@Autowired
	public CaseTypeRepository caseTypeRep;
	@Autowired
	public ChangeRequestRepository changeRequestRepository;

	@Autowired
	public ClientCaseRepository clientCaseRep;
	@Autowired
	public ClientRepository clientRep;

	@Autowired
	public DesagrementRepository desagrementRep;
	@Autowired
	public StaffRepository staffRep;

	/**
	 * Testing purposes
	 * 
	 * @return string with the created id
	 */
	public ArrayList<String> loadDef() {
		ArrayList<Long> apIds = new ArrayList<>();
		ArrayList<Long> staffId = new ArrayList<>();
		ArrayList<Long> loIds = new ArrayList<>();
		ArrayList<Long> rcIds = new ArrayList<>();

		Branch br = new Branch();
		br.setName("testbranch");
		br = branchRep.save(br);

		CaseType ct = new CaseType();
		ct.setType("Case type test");
		ct = caseTypeRep.save(ct);

		Client cl = new Client();
		cl.setLocked(false);
		cl.setName("Client test name");
		cl.setPotentialMoneyLaundring(false);
		cl.setSurname("Client test surname");
		cl = clientRep.save(cl);

		ClientCase cs = new ClientCase();
		cs.setCaseTypeId(ct.getId());
		cs.setClientId(cl.getId());

		cs = clientCaseRep.save(cs);

		for (int i = 0; i < 5; i++) {
			Staff st = new Staff();
			st.setName("LEGAL");
			st.setPassword("ls" + i);
			st.setRole(Staff.LEGAL_STAFF);
			st.setSurname("STAFF " + i);
			st.setUsername("ls" + i);
			st = staffRep.save(st);
			staffId.add(st.getId());
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, +6);

		for (int i = 0; i < 5; i++) {
			Apointment ap = new Apointment();
			ap.setAttented(false);
			ap.setBranchID(br.getId());
			ap.setCaseId(cs.getId());
			ap.setDate(cal.getTime());
			ap.setDropin(false);
			ap.setWithWhoStaffId(staffId.get(0));
			ap = apointmentsRep.save(ap);
			cal.add(Calendar.DATE, 1);
		}
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, -6);

		for (int i = 0; i < 3; i++) {
			Apointment ap = new Apointment();
			ap.setAttented(false);
			ap.setBranchID(br.getId());
			ap.setCaseId(cs.getId());
			ap.setDate(cal.getTime());
			ap.setDropin(false);
			ap.setWithWhoStaffId(staffId.get(0));
			ap = apointmentsRep.save(ap);
			cal.add(Calendar.HOUR_OF_DAY, 1);
		}

		for (int i = 0; i < 5; i++) {
			Apointment ap = new Apointment();
			ap.setAttented(true);
			ap.setBranchID(br.getId());
			ap.setCaseId(cs.getId());
			ap.setDate(cal.getTime());
			ap.setDropin(false);
			ap.setWithWhoStaffId(staffId.get(i));
			ap = apointmentsRep.save(ap);
			cal.add(Calendar.DATE, -1);
			apIds.add(ap.getId());

		}

		for (int i = 0; i < 5; i++) {
			LegalOpinion lo = new LegalOpinion();
			lo.setType("Legal Opinion " + i);
			lo = legalRep.save(lo);
			loIds.add(lo.getId());
		}

		for (int i = 0; i < 5; i++) {
			Recommendation rc = new Recommendation();
			rc.setType("Recommendation " + i);
			rc = recomRep.save(rc);
			rcIds.add(rc.getId());
		}

		int k = 0;

		for (Long l : apIds) {
			CaseHistory ch = new CaseHistory();
			ch.setCaseId(cs.getId());
			ch.setLegalOpinionDetails("Legal opinion Details");
			ch.setLegalOpinionId(loIds.get(k));
			ch.setRecomandationId(rcIds.get(k));
			ch.setRecomantationDetails("Recommensation Details");
			ch.setStaffId(staffId.get(k));
			ch.setApointmentId(l);
			ch = caseHistoryRep.save(ch);
			k++;
		}
		Desagrement d = new Desagrement();
		d.setClientId(cl.getId());
		d.setOverruled(true);
		d.setOverruledByStaffId(staffId.get(0));
		d.setRecomandationId(rcIds.get(0));
		desagrementRep.save(d);

		ArrayList<String> ret = new ArrayList<>();
		ret.add("Initialization finished\n");

		Staff u = new Staff();
		u.setName("HEAD");
		u.setSurname("OFFICE");
		u.setPassword("ho");
		u.setUsername("ho");
		u.setRole(Staff.HEAD_OFFICE);
		staffRep.save(u);

		u = new Staff();
		u.setName("LEGAL");
		u.setSurname("OFFICE");
		u.setPassword("lo");
		u.setUsername("lo");
		u.setRole(Staff.LEGAL_OFFICE);
		staffRep.save(u);

		u = new Staff();
		u.setName("RECEPTIONIST");
		u.setSurname("RECEPTIONIST");
		u.setPassword("re");
		u.setUsername("re");
		u.setRole(Staff.RECEPTIONIST);
		staffRep.save(u);

		return ret;

	}

	public Staff StaffIdToStaff(Long id) {
		return null;
	}
}
