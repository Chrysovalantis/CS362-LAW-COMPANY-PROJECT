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
import com.example.demo.controller.CaseHistoryController;
import com.example.demo.model.CaseHistory;
import com.example.demo.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CaseHistoryControllerTest extends AllRepositories {

	@Autowired
    private CaseHistoryController caseHistoryController;

	@Test
	public void addAndDeleteCaseHistory() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
        CaseHistory ch = new CaseHistory();
    	ch.setCaseId((long) 1);
    	ch.setLegalOpinionDetails("Legal opinion Details");
    	ch.setLegalOpinionId((long) 1);
    	ch.setRecomandationId((long) 1);
    	ch.setRecomantationDetails("Recommensation Details");
    	ch.setStaffId((long) 1);
    	ch.setApointmentId((long) 1);

    	String result =  caseHistoryController.addNewT(ch);
		assertNotNull(result);

		long caseId = Long.parseLong(result);
		ch.setId(caseId);
		
		CaseHistory c = caseHistoryRep.findById(caseId).get();
		c.setDate(null);
		ch.setDate(null);
		System.out.println(ch);
		System.out.println(caseHistoryRep.findById(caseId).get());
	    
		Map<String, Object> mapSent = oMapper.convertValue(ch, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(c, Map.class);
	    
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    caseHistoryController.deleteT(caseId);
	    
	    assertFalse(clientRep.findById(caseId).isPresent());
	
	}

}
