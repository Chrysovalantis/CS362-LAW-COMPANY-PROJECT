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
     * @return string with the created id
     */
    public ArrayList<String> loadDef() {
    	Branch br = new Branch();
    	br.setName("testbranch");
    	br=branchRep.save(br);
    	
    	CaseType ct = new CaseType();
    	ct.setType("Case type test");
    	ct=caseTypeRep.save(ct);
    	
    	Client cl = new Client();
    	cl.setLocked(false);
    	cl.setName("Client test name");
    	cl.setPotentialMoneyLaundring(false);
    	cl.setSurname("Client test surname");
    	cl=clientRep.save(cl);
    	
    	ClientCase cs = new ClientCase();
    	cs.setCaseTypeId(ct.getId());
    	cs.setClientId(cl.getId());
 
    	cs=clientCaseRep.save(cs);
    	
    	Staff st = new Staff();
    	st.setName("LEGAL");
    	st.setPassword("ls");
    	st.setRole(Staff.LEGAL_STAFF);
    	st.setSurname("STAFF");
    	st.setUsername("ls");
    	st = staffRep.save(st);
    	
    	Apointment ap = new Apointment();
    	ap.setAttented(false);
    	ap.setBranchID(br.getId());
    	ap.setCaseId(cs.getId());
    	
    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		cal.add(Calendar.HOUR_OF_DAY, +6);
		
		Date tomorow = cal.getTime();
		
    	ap.setDate(tomorow);
    	ap.setDropin(false);
    	ap.setWithWhoStaffId(st.getId());
    	ap = apointmentsRep.save(ap);
    	
    	
    	Apointment ap2 = new Apointment();
    	ap2.setAttented(false);
    	ap2.setBranchID(br.getId());
    	ap2.setCaseId(cs.getId());
    	
    	cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		Date yestarday = cal.getTime();
		
    	ap2.setDate(yestarday);
    	ap2.setDropin(false);
    	ap2.setWithWhoStaffId(st.getId());
    	ap2 = apointmentsRep.save(ap2);
    	
    	LegalOpinion lo = new LegalOpinion();
    	lo.setType("Legal Opinion Test");
    	lo=legalRep.save(lo);
    	
    	Recommendation rc = new Recommendation();
    	rc.setType("Recommendation Test");
    	rc=recomRep.save(rc);    	

    	CaseHistory ch = new CaseHistory();
    	ch.setCaseId(cs.getId());
    	ch.setLegalOpinionDetails("Legal opinion Details");
    	ch.setLegalOpinionId(lo.getId());
    	ch.setRecomandationId(rc.getId());
    	ch.setRecomantationDetails("Recommensation Details");
    	ch.setStaffId(st.getId());
    	ch.setApointmentId(ap.getId());
    	ch = caseHistoryRep.save(ch);
    	
    	Desagrement d = new Desagrement();
    	d.setClientId(cl.getId());
    	d.setOverruled(true);
    	d.setOverruledByStaffId(st.getId());
    	d.setRecomandationId(rc.getId());
    	desagrementRep.save(d);
    	
    	
    	ArrayList<String> ret = new ArrayList<>();
    	ret.add("Branch id:" + br.getId()+"\n");
    	ret.add("Case Type id:" + ct.getId()+"\n");
    	ret.add("Client id:" + cl.getId()+"\n");
    	ret.add("Client Case id:" + cs.getId()+"\n");
    	ret.add("Staff id:" + st.getId()+"\n");
    	ret.add("Apointment id:" + ap.getId()+"\n");
    	ret.add("Recomendation id:" + rc.getId()+"\n");
    	ret.add("Legal Opinion id:" + lo.getId()+"\n");
    	ret.add("Case History id:" + ch.getId()+"\n");
    	ret.add("Desagriment id:" + d.getId()+"\n");

    	Staff u =new Staff();
    	u.setName("HEAD");
    	u.setSurname("OFFICE");
    	u.setPassword("ho");
    	u.setUsername("ho");
    	u.setRole(Staff.HEAD_OFFICE);
    	staffRep.save(u);
    	
    	u =new Staff();
    	u.setName("LEGAL");
    	u.setSurname("OFFICE");
    	u.setPassword("lo");
    	u.setUsername("lo");
    	u.setRole(Staff.LEGAL_OFFICE);
    	staffRep.save(u); 	
    	
    	u =new Staff();
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
