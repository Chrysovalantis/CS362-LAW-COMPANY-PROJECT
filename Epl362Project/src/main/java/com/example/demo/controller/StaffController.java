package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Staff;

@RestController
@RequestMapping(path = "/staffs")
public class StaffController extends CoreController<Staff, CrudRepository<Staff, Long>> {

}