package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CaseType;

@RestController
@RequestMapping(path = "/caseTypes")
public class CaseTypeController extends CoreController<CaseType, CrudRepository<CaseType, Long>> {

}