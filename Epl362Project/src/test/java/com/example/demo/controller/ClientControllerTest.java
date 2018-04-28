package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.AllRepositories;
import com.example.demo.controller.ClientController;
import com.example.demo.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest extends AllRepositories{
	
	@Autowired
    private ClientController clientController;

	@Test
	public void addClient() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
        Client cl = new Client();
    	cl.setLocked(false);
    	cl.setName("Client test name");
    	cl.setPotentialMoneyLaundring(true);
    	cl.setSurname("Client test surname");
    	System.out.println(clientController);

    	String result =  clientController.addNewT(cl);
		assertNotNull(result);

		long clientId = Long.parseLong(result);
		cl.setId(clientId);
		
		Client c = clientRep.findById(clientId).get();
	    
		Map<String, Object> mapSent = oMapper.convertValue(cl, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(c, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	
	}
	
	

}