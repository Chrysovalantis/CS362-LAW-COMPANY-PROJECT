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
import com.example.demo.controller.StaffController;
import com.example.demo.model.Staff;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaffControllerTest extends AllRepositories {

	@Autowired
    private StaffController staffController;

	@Test
	public void addAndDeleteStaff() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();
        
        Staff u =new Staff();
    	u.setName("HEAD");
    	u.setSurname("OFFICE");
    	u.setPassword("ho");
    	u.setUsername("ho");
    	u.setRole(Staff.HEAD_OFFICE);
    	staffRep.save(u);

    	String result =  staffController.addNewT(u);
		assertNotNull(result);

		long staffId = Long.parseLong(result);
		u.setId(staffId);
		
		Staff b = staffRep.findById(staffId).get();
		
		System.out.println(u);
		System.out.println(staffRep.findById(staffId).get());
	    
		Map<String, Object> mapSent = oMapper.convertValue(u, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(b, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    staffController.deleteT(staffId);
	    
	    assertFalse(staffRep.findById(staffId).isPresent());
	
	}

}
