package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.AllRepositories;
import com.example.demo.controller.ApointmentController;
import com.example.demo.model.Apointment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import static org.junit.Assert.*;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentControllerTest extends AllRepositories {

	@Autowired
    private ApointmentController appointmentController;

	@Test
	public void addAndDeleteAppointment() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
      	Apointment ap = new Apointment();
    	ap.setAttented(false);
    	ap.setBranchID((long) 2);
    	ap.setCaseId((long) 3);

    	String result =  appointmentController.addNewT(ap);
		assertNotNull(result);
		
		long appID = Long.parseLong(result);
	
		ap.setId(appID);
		
		Apointment ap1 = apointmentsRep.findById(appID).get();
		System.out.println(ap);
		System.out.println(apointmentsRep.findById(appID).get());
	    ap.setDateCreated(null);
	    ap1.setDateCreated(null);
		Map<String, Object> mapSent = oMapper.convertValue(ap, Map.class);
		Map<String, Object> mapDatabse = oMapper.convertValue(ap1, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    appointmentController.deleteT(appID);
	    
	    assertFalse(clientRep.findById(appID).isPresent());
	
	}
}
