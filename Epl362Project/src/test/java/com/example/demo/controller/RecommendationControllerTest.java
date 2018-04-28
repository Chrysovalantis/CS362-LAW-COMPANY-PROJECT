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
import com.example.demo.controller.RecommendationController;
import com.example.demo.model.Recommendation;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendationControllerTest extends AllRepositories {

	@Autowired
    private RecommendationController recommendationController;

	@Test
	public void addAndDeleteCaseHistory() {
	    assertTrue(true);

        ObjectMapper oMapper = new ObjectMapper();

    	Recommendation rc = new Recommendation();
    	rc.setType("Recommendation Test");
    	rc=recomRep.save(rc); ;

    	String result =  recommendationController.addNewT(rc);
		assertNotNull(result);

		long recomId = Long.parseLong(result);
		rc.setId(recomId);
		
		Recommendation r = recomRep.findById(recomId).get();
		
		System.out.println(rc);
		System.out.println(recomRep.findById(recomId).get());
	    
		Map<String, Object> mapSent = oMapper.convertValue(rc, Map.class);
	    Map<String, Object> mapDatabse = oMapper.convertValue(r, Map.class);
	    assertTrue(mapSent.equals(mapDatabse));
	    
	    recommendationController.deleteT(recomId);
	    
	    assertFalse(recomRep.findById(recomId).isPresent());
	
	}


}
