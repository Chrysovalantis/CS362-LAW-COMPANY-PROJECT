package com.example.demo.form;

import java.util.Date;

import com.example.demo.model.CaseHistory;
import com.example.demo.model.Staff;

public class CaseHistoryForm {
	
	public Long id;
    
	public Long staffId;
    
	public Long caseId;
    
	public Long recomandationId;
    
	public String recomantationDetails;
    
	public Long legalOpinionId;
    
	public String legalOpinionDetails;
    
	public Date date;
	
	public String username;
	    
	public String password;
    
	public String name;
    
	public String surname;

	public String role;
    
    public CaseHistoryForm(CaseHistory ch,Staff st) {
    	id=ch.getCaseId();
    	staffId=ch.getStaffId();
    	caseId=ch.getCaseId();
    	recomandationId=ch.getRecomandationId();
    	recomantationDetails=ch.getRecomantationDetails();
    	legalOpinionDetails=ch.getLegalOpinionDetails();
    	legalOpinionId=ch.getLegalOpinionId();
    	date=ch.getDate();
    	username=st.getUsername();
    	password=st.getPassword();
    	name=st.getName();
    	surname=st.getSurname();
    	role=st.getRole();
	}
     
}