package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LegalOpinion;

@RestController
@RequestMapping(path = "/legalOpinions")
public class LegalOpinionController extends CoreController<LegalOpinion, CrudRepository<LegalOpinion, Long>> {

}