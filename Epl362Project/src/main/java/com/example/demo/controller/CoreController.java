package com.example.demo.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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
	
	/**
	 * Add an object to the database
	 * @param t the object to be added to the database
	 * @return the id of the object created
	 */
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public @ResponseBody String addNewT (@Valid @RequestBody T t) {
		//System.out.println(t.toString());
		t.setId(null);
		repository.save(t);
		return t.getId()+"";
	}

	
	/**
	 * Get all the objects from the database
	 * @return all the objects
	 */
	@GetMapping(path="")
	public @ResponseBody Iterable<T> getAllTs() {
		return repository.findAll();
	}
	
	
	/**
	 * Get an object based on id
	 * @param id the id of the object requested
	 * @return the object requested
	 */
	@GetMapping("/{id}")
	public T getT(@PathVariable(value = "id") Long id) {
		if(!repository.existsById(id)) {
			return null;
		}
	    return repository.findById(id).get();
	}
	
	/**
	 * Delete an object based on its id
	 * @param id the id of the object to be deleted
	 * @return the deleted objects id
	 */
	@DeleteMapping(path="/delete/{id}") 
	public @ResponseBody String deleteT (@PathVariable(value = "id") Long id) {
		if(!repository.existsById(id)) {
			return "Error";
		}
		repository.deleteById(id);
		return id+"";
	}

	/**
	 * Update an object at the database
	 * @param t the object to be updated
	 * @return the updates object id
	 */
	@PutMapping(path="/update") 
	public @ResponseBody String updateT (@Valid @RequestBody T t ) {
		if(!repository.existsById(t.getId())) {
			return "Record does not exist";
		}
		repository.save(t);
		return t.getId()+"";
	}
	
	// testing
	/**
	 * Add a new null object for testing purposes
	 * @param t an empty object
	 * @return the object
	 */
	@PostMapping(path="/addNew") 
	public @ResponseBody T addNew (@Valid @RequestBody T t) {
		repository.save(t);
		return t;
	}
	
	/**
	 * Delete all the objects from the database
	 * @return null
	 */
	@DeleteMapping(path="/deleteAll") 
	public @ResponseBody String deleteAll () {
		repository.deleteAll();
		return null;
	}
}