package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Recomentation;

@RestController
@RequestMapping(path = "/recomentations")
public class RecomentationController extends CoreController<Recomentation, CrudRepository<Recomentation, Long>> {

}