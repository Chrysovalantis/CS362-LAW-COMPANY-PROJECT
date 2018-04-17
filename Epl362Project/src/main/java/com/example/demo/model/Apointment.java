package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Apointment extends MyModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private Date dateCreated= new Date();

	private boolean attented = false;
    
    private Date date;
    
    private String withWhoStaffId;
    
    private String branchID;
    
    private String caseId;
    
    private boolean dropin =false;
    
    
    /*
    @PrePersist
    protected void onCreate() {
    	dateCreated = Calendar.getInstance().toString();
    }
    */
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isAttented() {
		return attented;
	}

	public void setAttented(boolean attented) {
		this.attented = attented;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWithWhoStaffId() {
		return withWhoStaffId;
	}

	public void setWithWhoStaffId(String withWhoStaffId) {
		this.withWhoStaffId = withWhoStaffId;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public boolean isDropin() {
		return dropin;
	}

	public void setDropin(boolean dropin) {
		this.dropin = dropin;
	}

    
    
	
	
}