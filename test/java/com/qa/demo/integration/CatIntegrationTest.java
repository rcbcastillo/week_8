package com.qa.demo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.domain.Cat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:cat-schema.sql", "classpath:cat-data.sql"})
public class CatIntegrationTest {
	
	@Autowired
	private MockMvc mvc; // performs mocked Http requests as a true user might
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test // because is Junit
	void testCreateCat()  throws Exception{
		Cat newCat = new Cat("Charman Meow", true, true, 12);
		String newCatAsJson = this.mapper.writeValueAsString(newCat);
		RequestBuilder req = post("/create").content(newCatAsJson).contentType(MediaType.APPLICATION_JSON);
		
		ResultMatcher checkStatus = status().isCreated();
		Cat created = new Cat(2, "Charman Meow", true, true, 12);
		String createAsJson = this.mapper.writeValueAsString(created);
		ResultMatcher checkBody = content().json(createAsJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAllCat() throws Exception{
		RequestBuilder req = get("/getAll");
		
		ResultMatcher checkStatus = status().isOk();
		List<Cat> catList = new ArrayList<Cat>();
		Cat created = new Cat(1, "Mr Wright", true, true, 27);
		catList.add(created);
		String resultCatListAsJson = mapper.writeValueAsString(catList);
		ResultMatcher checkBody = content().json(resultCatListAsJson);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
		
	}
	
	void testDeleteCat() throws Exception{
		int catId = 1;
		RequestBuilder req = delete("/remove/" + catId);
		
		ResultMatcher checkStatus = status().isOk();
		
		Cat created = new Cat(1, "Mr Wright", true, true, 27);
		String resultCatListAsJson = mapper.writeValueAsString(created);
		ResultMatcher checkBody = content().json(resultCatListAsJson);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);	
		
	}
	
	void testUpdateCat() throws Exception{
		int catId = 1;
		RequestBuilder req = patch("/update/" + catId).param("evil", "false").param("whiskers", "false").param("length", "25").param("name", "Mr Meow");
		
		ResultMatcher checkStatus = status().isOk();		
		Cat created = new Cat(1, "Mr Meow", false, false, 25);		
		String resultCatListAsJson = mapper.writeValueAsString(created);
		ResultMatcher checkBody = content().json(resultCatListAsJson);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);	
		
	}
	
	void testGetCatById() throws Exception{
		int catId = 1;
		RequestBuilder req = get("/get/" + catId);
		
		ResultMatcher checkStatus = status().isOk();		
		Cat created = new Cat(1, "Mr Wright", true, true, 27);		
		String resultCatListAsJson = mapper.writeValueAsString(created);
		ResultMatcher checkBody = content().json(resultCatListAsJson);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);	
		
	}
	
	void testIsNotFound() throws Exception{
		int catId = 1;
		RequestBuilder req = get("/get/" + catId);
		
		ResultMatcher checkStatus = status().isNotFound();
		this.mvc.perform(req).andExpect(checkStatus);	
		
	}
}
