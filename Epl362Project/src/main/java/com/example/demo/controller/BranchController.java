package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Branch;

@RestController
@RequestMapping(path = "/branches")
public class BranchController extends CoreController<Branch, CrudRepository<Branch, Long>> {

}