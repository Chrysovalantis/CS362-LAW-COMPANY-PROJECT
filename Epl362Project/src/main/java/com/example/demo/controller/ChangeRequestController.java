package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChangeRequest;

@RestController
@RequestMapping(path = "/changeRequests")
public class ChangeRequestController extends CoreController<ChangeRequest, CrudRepository<ChangeRequest, Long>> {

}