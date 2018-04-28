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
import com.example.demo.controller.LegalOpinionController;
import com.example.demo.model.LegalOpinion;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LegalOpinionControllerTest extends AllRepositories {

	@Autowired
    private LegalOpinionController legalOpinionController;

	@Test
	public void addAndDeleteCaseHistory() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
        LegalOpinion lo = new LegalOpinion();
    	lo.setType("Legal Opinion Test");
    	lo=legalRep.save(lo);

    	String result =  legalOpinionController.addNewT(lo);
		assertNotNull(result);

		long legalId = Long.parseLong(result);
		lo.setId(legalId);
		
		LegalOpinion lo1 = legalRep.findById(legalId).get();
		
		System.out.println(lo);
		System.out.println(legalRep.findById(legalId).get());
	    
		Map<String, Object> mapSent = oMapper.convertValue(lo, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(lo1, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    legalOpinionController.deleteT(legalId);
	    
	    assertFalse(clientRep.findById(legalId).isPresent());
	
	}
}
