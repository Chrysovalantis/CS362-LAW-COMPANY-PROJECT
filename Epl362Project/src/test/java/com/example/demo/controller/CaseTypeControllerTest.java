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
import com.example.demo.controller.CaseTypeController;
import com.example.demo.model.CaseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CaseTypeControllerTest extends AllRepositories {

	@Autowired
    private CaseTypeController caseTypeController;

	@Test
	public void addAndDeleteCaseType() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
    	CaseType ct = new CaseType();
    	ct.setType("Case type test");
    	ct=caseTypeRep.save(ct);

    	String result =  caseTypeController.addNewT(ct);
		assertNotNull(result);

		long caseTypeId = Long.parseLong(result);
		ct.setId(caseTypeId);
		
		CaseType ct1 = caseTypeRep.findById(caseTypeId).get();
		
		System.out.println(ct);
		System.out.println(caseTypeRep.findById(caseTypeId).get());
	    
		Map<String, Object> mapSent = oMapper.convertValue(ct, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(ct1, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    caseTypeController.deleteT(caseTypeId);
	    
	    assertFalse(caseTypeRep.findById(caseTypeId).isPresent());
	
	}

}
