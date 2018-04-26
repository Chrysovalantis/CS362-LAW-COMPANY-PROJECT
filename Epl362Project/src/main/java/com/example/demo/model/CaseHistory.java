package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class CaseHistory extends MyModel{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private Long staffId;
    
    private Long apointmentId;
    
   	public Long getApointmentId() {
		return apointmentId;
	}

	public void setApointmentId(Long apointmentId) {
		this.apointmentId = apointmentId;
	}

	private Long caseId;
    
    private Long recomandationId;
    
    private String recomantationDetails;
    
    private Long legalOpinionId;
    
	private String legalOpinionDetails;
    
    private Date date=new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Long getRecomandationId() {
		return recomandationId;
	}

	public void setRecomandationId(Long recomandationId) {
		this.recomandationId = recomandationId;
	}

	public String getRecomantationDetails() {
		return recomantationDetails;
	}

	public void setRecomantationDetails(String recomantationDetails) {
		this.recomantationDetails = recomantationDetails;
	}

	public Long getLegalOpinionId() {
		return legalOpinionId;
	}

	public void setLegalOpinionId(Long legalOpinionId) {
		this.legalOpinionId = legalOpinionId;
	}

	public String getLegalOpinionDetails() {
		return legalOpinionDetails;
	}

	public void setLegalOpinionDetails(String legalOpinionDetails) {
		this.legalOpinionDetails = legalOpinionDetails;
	}
	
	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
   
	 
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CaseHistory [id=" + id + ", staffId=" + staffId + ", apointmentId=" + apointmentId + ", caseId="
				+ caseId + ", recomandationId=" + recomandationId + ", recomantationDetails=" + recomantationDetails
				+ ", legalOpinionId=" + legalOpinionId + ", legalOpinionDetails=" + legalOpinionDetails + ", date="
				+ date + "]";
	}
	
	
}