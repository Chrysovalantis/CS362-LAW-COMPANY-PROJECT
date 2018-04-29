package com.example.demo.controller;

import javax.validation.Valid;

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
import com.example.demo.model.Desagrement;

@RestController
@RequestMapping(path = "/casesHistorys")
public class CaseHistoryController extends CoreController<CaseHistory, CrudRepository<CaseHistory, Long>> {
	@Autowired
	private CrudRepository<CaseHistory, Long> repositoryCaseHis;
	@Autowired
	private CrudRepository<Apointment, Long> repositoryApo;
	@Autowired
	private CrudRepository<ClientCase, Long> repositoryCases;
	@Autowired
	private CrudRepository<ChangeRequest, Long> repositoryChangeRequest;
	@Autowired
	private CrudRepository<Client, Long> repositoryClients;

	@Autowired
	private CrudRepository<Desagrement, Long> desagrement;

	/**
	 * Add case history from consultation form
	 * 
	 * @param caseHistory
	 *            the object with recommendation and legal opinions
	 * @return empty json
	 */
	@PostMapping(path = "/addConsultation")
	public @ResponseBody String AddCaseHistoryForm(@Valid @RequestBody AddCaseHistoryForm caseHistory) {
		CaseHistory ch = new CaseHistory();
		
		ClientCase c = repositoryCases.findById(caseHistory.caseId).get();
		ch.setApointmentId(caseHistory.apointmentId);
		ch.setCaseId(caseHistory.caseId);
		ch.setDate(caseHistory.date);
		if (!caseHistory.legalOpinionId.equals(0)) {
			ch.setLegalOpinionId(caseHistory.legalOpinionId);
			ch.setLegalOpinionDetails(caseHistory.legalOpinionDetails);
		} else {
			ch.setLegalOpinionId(null);
		}
		if (!caseHistory.recomandationId.equals(0)) {
			ch.setRecomandationId(caseHistory.recomandationId);
			ch.setRecomantationDetails(caseHistory.recomantationDetails);
		} else {
			ch.setRecomandationId(null);
		}
		if (!caseHistory.recomandationId.equals(0)) {
			for (Desagrement d : desagrement.findAll()) {
				if (d.getClientId().equals(c.getClientId())
						&& d.getRecomandationId().equals(caseHistory.recomandationId)) {
					d.setOverruled(true);
					d.setOverruledByStaffId(caseHistory.staffId);
					break;

				}
			}
		}
		ch.setStaffId(caseHistory.staffId);
		if ((caseHistory.recomantationDetails == null || caseHistory.recomantationDetails.equals(""))
				&& (caseHistory.legalOpinionDetails == null || caseHistory.legalOpinionDetails.equals("")))

		{
			caseHistory.moneyLayndring = true;
		}
		try {
			if (caseHistory.moneyLayndring != null) {

				ChangeRequest chr = new ChangeRequest();
				chr.setNewPotentialMoneyLaundring(true);
				chr.setClientId(c.getClientId());
				Client client = repositoryClients.findById(c.getClientId()).get();
				chr.setDeleted(false);
				chr.setDescription("Report of suspicies activity");
				chr.setNewName(client.getName());
				chr.setNewSurname(client.getSurname());
				chr.setState(ChangeRequest.UNPROSESED);
				repositoryChangeRequest.save(chr);
			}
		} catch (Exception e) {
		}

		repositoryCaseHis.save(ch);
		Apointment ap = repositoryApo.findById(caseHistory.apointmentId).get();
		ap.setAttented(true);
		repositoryApo.save(ap);
		return "";
	}
}