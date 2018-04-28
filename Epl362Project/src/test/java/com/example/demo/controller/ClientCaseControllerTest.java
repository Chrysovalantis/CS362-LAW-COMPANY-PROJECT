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
import com.example.demo.controller.ClientCaseController;
import com.example.demo.model.ClientCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientCaseControllerTest extends AllRepositories {

	@Autowired
    private ClientCaseController clientCaseController;

	@Test
	public void addAndDeleteCaseHistory() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
    	ClientCase cs = new ClientCase();
    	cs.setCaseTypeId((long) 1);
    	cs.setClientId((long) 1);
 

    	String result =  clientCaseController.addNewT(cs);
		assertNotNull(result);

		long clientCaseId = Long.parseLong(result);
		cs.setId(clientCaseId);
		
		ClientCase cs1 = clientCaseRep.findById(clientCaseId).get();
		
		System.out.println(cs);
		System.out.println(clientCaseRep.findById(clientCaseId).get());
	    
		Map<String, Object> mapSent = oMapper.convertValue(cs, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(cs1, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    clientCaseController.deleteT(clientCaseId);
	    
	    assertFalse(clientCaseRep.findById(clientCaseId).isPresent());
	
	}

}
