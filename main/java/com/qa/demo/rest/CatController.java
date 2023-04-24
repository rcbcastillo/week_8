package com.qa.demo.rest;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.domain.Cat;
import com.qa.demo.service.CatService;



@RestController

public class CatController {
	private CatService service;
	
	public CatController(CatService service) {
		this.service = service;
	}
	
	
	@GetMapping("/")
	public String greeting() {
		return "Hello World";
	}
	
	@PostMapping("/create")
	//CREATED, changing the 200 to 201
	public ResponseEntity <Cat> create( @RequestBody Cat c) {		
		Cat created = this.service.createCat(c);
		return new ResponseEntity<>(created,  HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public List<Cat> getAll() {
		return this.service.getAll();
	}
	
	@GetMapping("/get/{id}")// setups the address
	public Cat get(@PathVariable int id) {
		return this.service.getById(id);
	}
	
	@DeleteMapping("/remove/{id}")
	public Cat remove (@PathVariable int id) {
		return this.service.delete(id);
	}
	
	@PatchMapping("/update/{id}")
	public Cat update (
			@PathVariable int id, 
			@RequestParam(name="name", required=false) String name,
			@RequestParam(name="whiskers", required=false) Boolean whiskers,
			@RequestParam(name="length", required=false) Integer length,
			@RequestParam(name="evil", required=false) Boolean evil) {
		return this.service.update(id, name, length, whiskers, evil);
		
	}

}
