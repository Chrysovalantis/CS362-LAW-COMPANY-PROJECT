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
import com.example.demo.controller.DesagrementController;
import com.example.demo.model.Desagrement;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesagrementControllerTest extends AllRepositories {

	@Autowired
    private DesagrementController desagrementController;

	@Test
	public void addAndDeleteCaseHistory() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
      	Desagrement d = new Desagrement();
    	d.setClientId((long)1);
    	d.setOverruled(true);
    	d.setOverruledByStaffId((long)1);
    	d.setRecomandationId((long)1);
    	desagrementRep.save(d);

    	String result =  desagrementController.addNewT(d);
		assertNotNull(result);

		long desagrementId = Long.parseLong(result);
		d.setId(desagrementId);
		
		Desagrement b = desagrementRep.findById(desagrementId).get();
		
		System.out.println(d);
		System.out.println(desagrementRep.findById(desagrementId).get());
	    
		Map<String, Object> mapSent = oMapper.convertValue(d, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(b, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    desagrementController.deleteT(desagrementId);
	    
	    assertFalse(desagrementRep.findById(desagrementId).isPresent());
	
	}

}
