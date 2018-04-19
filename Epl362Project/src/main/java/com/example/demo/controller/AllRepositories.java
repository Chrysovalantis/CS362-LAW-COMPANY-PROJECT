package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

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
