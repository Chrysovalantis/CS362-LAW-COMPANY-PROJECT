package com.example.demo.model;

import java.util.Calendar;
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
    
    private Long withWhoStaffId;
    
    private Long branchID;
    
    private Long caseId;
    
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

	@Override
	public String toString() {
		return "Apointment [id=" + id + ", dateCreated=" + dateCreated + ", attented=" + attented + ", date=" + date
				+ ", withWhoStaffId=" + withWhoStaffId + ", branchID=" + branchID + ", caseId=" + caseId + ", dropin="
				+ dropin + "]";
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
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -3);
		this.date = cal.getTime();
	}

	public Long getWithWhoStaffId() {
		return withWhoStaffId;
	}

	public void setWithWhoStaffId(Long withWhoStaffId) {
		this.withWhoStaffId = withWhoStaffId;
	}

	public Long getBranchID() {
		return branchID;
	}

	public void setBranchID(Long branchID) {
		this.branchID = branchID;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public boolean isDropin() {
		return dropin;
	}

	public void setDropin(boolean dropin) {
		this.dropin = dropin;
	}

    
    
	
	
}