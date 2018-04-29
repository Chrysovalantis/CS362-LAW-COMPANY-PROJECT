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
import com.example.demo.controller.BranchController;
import com.example.demo.model.Branch;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BranchControllerTest extends AllRepositories {

	@Autowired
    private BranchController branchController;

	@Test
	public void addAndDeleteBranch() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
       	Branch br = new Branch();
    	br.setName("testbranch");
    	br=branchRep.save(br);

    	String result =  branchController.addNewT(br);
		assertNotNull(result);

		long branchId = Long.parseLong(result);
		br.setId(branchId);
		
		Branch b = branchRep.findById(branchId).get();
		
		System.out.println(br);
		System.out.println(branchRep.findById(branchId).get());
	    
		Map<String, Object> mapSent = oMapper.convertValue(br, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(b, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    branchController.deleteT(branchId);
	    
	    assertFalse(clientRep.findById(branchId).isPresent());
	
	}

}
