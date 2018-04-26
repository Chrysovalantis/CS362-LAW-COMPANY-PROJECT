package com.example.demo.controller;

import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.validation.Valid;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.AddCaseHistoryForm;
import com.example.demo.model.Apointment;
import com.example.demo.model.CaseHistory;
import com.example.demo.model.ChangeRequest;
import com.example.demo.model.Client;
import com.example.demo.model.ClientCase;

@RestController
@RequestMapping(path = "/casesHistorys")
public class CaseHistoryController extends CoreController<CaseHistory, CrudRepository<CaseHistory, Long>> {
	@Autowired
	private  CrudRepository<CaseHistory, Long> repositoryCaseHis;
	@Autowired
	private  CrudRepository<Apointment, Long> repositoryApo;
	@Autowired
	private  CrudRepository<ClientCase, Long> repositoryCases;
	@Autowired
	private  CrudRepository<ChangeRequest, Long> repositoryChangeRequest;
	@Autowired
	private  CrudRepository<Client, Long> repositoryClients;
	@PostMapping(path = "/addConsultation")
	public @ResponseBody String AddCaseHistoryForm(@Valid @RequestBody AddCaseHistoryForm caseHistory) {
		CaseHistory ch = new CaseHistory();
		ch.setApointmentId(caseHistory.apointmentId);
		ch.setCaseId(caseHistory.caseId);
		ch.setDate(caseHistory.date);
		if (caseHistory.legalOpinionId != 0) {
			ch.setLegalOpinionId(caseHistory.legalOpinionId);
			ch.setLegalOpinionDetails(caseHistory.legalOpinionDetails);
		}
		else {
			ch.setLegalOpinionId(null);
		}
		if (caseHistory.recomandationId != 0) {
			ch.setRecomandationId(caseHistory.recomandationId);
			ch.setRecomantationDetails(caseHistory.recomantationDetails);
		}
		else {
			ch.setRecomandationId(null);
		}
		ch.setStaffId(caseHistory.staffId);
		System.out.println(caseHistory.toString());
		try {
			if(caseHistory.moneyLayndring!=null) {
				
				
			
			ChangeRequest chr = new ChangeRequest();
			chr.setNewPotentialMoneyLaundring(true);
			ClientCase c = repositoryCases.findById(ch.getCaseId()).get();
			chr.setClientId(c.getClientId());
			Client client = repositoryClients.findById(c.getClientId()).get(); 
			chr.setDeleted(false);
			chr.setDescription("Report of suspicies activity");
			chr.setNewName(client.getName());
			chr.setNewSurname(client.getSurname());
			chr.setState(ChangeRequest.UNPROSESED);
			repositoryChangeRequest.save(chr);
			}
		}
		catch (Exception e) {
		}
		
		repositoryCaseHis.save(ch);
		Apointment ap = repositoryApo.findById(caseHistory.apointmentId).get();
		ap.setAttented(true);
		repositoryApo.save(ap);
		return "";
	}
}