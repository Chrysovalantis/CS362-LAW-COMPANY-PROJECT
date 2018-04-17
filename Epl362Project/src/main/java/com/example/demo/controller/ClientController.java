package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Client;

@RestController
@RequestMapping(path = "/clients")
public class ClientController extends CoreController<Client, CrudRepository<Client, Long>> {

}