/**
 * 
 */
package com.example.demo.controller;

/**
 * @author Chrysovalantis
 *
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.PersonForm;
import com.example.demo.model.Person;
import com.example.demo.model.repositorys.LegalOpinionRepository;
import com.example.demo.model.repositorys.RecomentationsRepository;
import com.example.demo.model.repositorys.ApointmentRepository;
import com.example.demo.model.repositorys.ClientRepository;

@Controller
public class MainController {
    @Autowired
    private LegalOpinionRepository legal;
    
    @Autowired
    private RecomentationsRepository recom;
    
    @Autowired
    private ApointmentRepository appts;
    
    @Autowired
    private ClientRepository clients;
    
    private static List<Person> persons = new ArrayList<Person>();
 
    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }
 
    // Inject via application.properties
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
 
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
 
        model.addAttribute("message", message);
 
        return "index";
    }
  
    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {
  
        
        return "personList";
    }
    
    @RequestMapping(value = { "/consultation" }, method = RequestMethod.GET)
    public String consultation(Model model) {
    	
    	model.addAttribute("legalOpinions", legal.findAll());
    	model.addAttribute("recommendations", recom.findAll());
 
        return "consultation";
    }
    
    @RequestMapping(value = { "/receptionist" }, method = RequestMethod.GET)
    public String receptionist(Model model) {
    	
    	model.addAttribute("appointments", appts.findAll());
    	model.addAttribute("appointmentsNumber", appts.count());
   
        return "receptionist";
    }
    
    @RequestMapping(value = { "/clientss" }, method = RequestMethod.GET)
    public String clientss(Model model) {
    	
    	model.addAttribute("clients", clients.findAll());
    	model.addAttribute("clientsNumber", clients.count());
   
        return "clientss";
    }
 
    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
 
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
 
        return "addPerson";
    }
 
    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
            @ModelAttribute("personForm") PersonForm personForm) {
 
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
 
        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName);
            persons.add(newPerson);
 
            return "redirect:/personList";
        }
 
        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }
 
}