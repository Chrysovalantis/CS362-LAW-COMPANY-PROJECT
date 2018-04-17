package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ClientCase;

@RestController
@RequestMapping(path = "/clientCases")
public class ClientCaseController extends CoreController<ClientCase, CrudRepository<ClientCase, Long>> {

}