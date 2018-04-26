package com.example.demo.form;

import java.util.Date;

public class AddCaseHistoryForm {
	public Long apointmentId;
	public Long caseId;
	public Long legalOpinionId;
	public Long recomandationId;
	public Long staffId;	
	public Date date = new Date();
	public String legalOpinionDetails;
	public String recomantationDetails;
	public Boolean moneyLayndring;
	@Override
	public String toString() {
		return "AddCaseHistoryForm [apointmentId=" + apointmentId + ", caseId=" + caseId + ", legalOpinionId="
				+ legalOpinionId + ", recomandationId=" + recomandationId + ", staffId=" + staffId + ", date=" + date
				+ ", legalOpinionDetails=" + legalOpinionDetails + ", recomantationDetails=" + recomantationDetails
				+ ", moneyLayndring=" + moneyLayndring + "]";
	}

	
}
