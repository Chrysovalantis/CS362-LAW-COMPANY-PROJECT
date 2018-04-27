package com.example.demo.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.MyModel;

public class CoreController<T extends MyModel,W extends CrudRepository<T, Long>> {
	@Autowired
	private W repository;

	@PostMapping(path="/add") 
	public @ResponseBody String addNewT (@Valid @RequestBody T t) {
		System.out.println(t.toString());
		t.setId(null);
		repository.save(t);
		return t.getId()+"";
	}
	

	@GetMapping(path="")
	public @ResponseBody Iterable<T> getAllTs() {
		return repository.findAll();
	}
	
	
	
	@GetMapping("/{id}")
	public T getT(@PathVariable(value = "id") Long id) {
		if(!repository.existsById(id)) {
			return null;
		}
	    return repository.findById(id).get();
	}
		
	@DeleteMapping(path="/delete/{id}") 
	public @ResponseBody String deleteT (@PathVariable(value = "id") Long id) {
		if(!repository.existsById(id)) {
			return "Error";
		}
		repository.deleteById(id);
		return id+"";
	}

	
	@PutMapping(path="/update") 
	public @ResponseBody String updateT (@Valid @RequestBody T t ) {
		if(!repository.existsById(t.getId())) {
			return "Record does not exist";
		}
		repository.save(t);
		return t.getId()+"";
	}
	
	// testing
	
	@PostMapping(path="/addNew") 
	public @ResponseBody T addNew (@Valid @RequestBody T t) {
		repository.save(t);
		return t;
	}
	
	@DeleteMapping(path="/deleteAll") 
	public @ResponseBody String deleteAll () {
		repository.deleteAll();
		return null;
	}
}