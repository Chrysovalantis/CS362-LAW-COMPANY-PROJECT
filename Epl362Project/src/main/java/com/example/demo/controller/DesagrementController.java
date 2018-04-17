package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Desagrement;

@RestController
@RequestMapping(path = "/desagrements")
public class DesagrementController extends CoreController<Desagrement, CrudRepository<Desagrement, Long>> {

}