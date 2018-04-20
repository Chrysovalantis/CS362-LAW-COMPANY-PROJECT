package com.example.demo.form;

import com.example.demo.model.ChangeRequest;
import com.example.demo.model.Client;

public class ChangeRequestForm {
	
	public Long id;
	
	public boolean deleted =false;
		
	public Long clientId;
    
	public String newName;
    
	public String newSurname;
    
	public boolean newPotentialMoneyLaundring = false;
    
	public String description;
    
	public String state;
         
	public String name;
    
	public String surname;

	public boolean locked=false;
    
	public boolean potentialMoneyLaundring = false;
    
    public ChangeRequestForm(ChangeRequest chr,Client cl) {
    	id=chr.getId();
    	deleted=chr.isDeleted();
    	clientId=chr.getClientId();
    	newName=chr.getNewName();
    	newSurname=chr.getNewSurname();
    	newPotentialMoneyLaundring=chr.isNewPotentialMoneyLaundring();
    	description=chr.getDescription();
    	state=chr.getState();
    	name=cl.getName();
    	surname=cl.getSurname();
    	locked=cl.isLocked();
    	potentialMoneyLaundring = cl.isPotentialMoneyLaundring();
    	
    	
	}
     
}