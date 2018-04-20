package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;


import com.example.demo.model.Apointment;
import com.example.demo.model.Branch;
import com.example.demo.model.CaseHistory;
import com.example.demo.model.CaseType;
import com.example.demo.model.Client;
import com.example.demo.model.ClientCase;
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
    	st.setName("Staff test name");
    	st.setPassword("test");
    	st.setRole(Staff.LEGAL_STAFF);
    	st.setSurname("Staff test surname");
    	st.setUsername("test");
    	st = staffRep.save(st);
    	
    	Apointment ap = new Apointment();
    	ap.setAttented(false);
    	ap.setBranchID(br.getId());
    	ap.setCaseId(cs.getId());
    	ap.setDate(new Date());
    	ap.setDropin(false);
    	ap.setWithWhoStaffId(st.getId());
    	ap = apointmentsRep.save(ap);
    	
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
    	ch = caseHistoryRep.save(ch);
    	
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

    	
    	return ret;
    	
    }
    
}
