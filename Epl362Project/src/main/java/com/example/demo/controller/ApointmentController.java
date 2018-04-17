package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Apointment;

@RestController
@RequestMapping(path = "/apointments")
public class ApointmentController extends CoreController<Apointment, CrudRepository<Apointment, Long>> {

}