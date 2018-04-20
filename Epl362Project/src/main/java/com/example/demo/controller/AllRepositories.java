package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.form.ChangeRequestForm;
import com.example.demo.model.ChangeRequest;
import com.example.demo.model.Client;
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
    
}
