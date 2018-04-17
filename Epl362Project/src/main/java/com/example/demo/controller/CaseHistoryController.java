package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CaseHistory;

@RestController
@RequestMapping(path = "/casesHistorys")
public class CaseHistoryController extends CoreController<CaseHistory, CrudRepository<CaseHistory, Long>> {

}