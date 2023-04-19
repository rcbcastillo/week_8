package com.qa.demo.rest;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.qa.demo.domain.Cat;

@RestController
public class CatController {

	private List<Cat> cats = new ArrayList<>();

	@GetMapping("/getAll")
	public List<Cat> getAll() {
		return this.cats;
	}

	@GetMapping("/get/{id}")
	public Cat getById(@PathVariable int id) {
		return this.cats.get(id);
	}

	@PostMapping("/create")
	public ResponseEntity<Cat> create(@RequestBody Cat c) {
		this.cats.add(c);
		Cat created = this.cats.get(this.cats.size() - 1);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PatchMapping("/update/{id}")
	public Cat update(@PathVariable Integer id, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "hasWhiskers", required = false) Boolean hasWhiskers,
			@RequestParam(name = "evil", required = false) Boolean evil) {
		Cat toUpdate = this.cats.get(id);

		if (name != null)
			toUpdate.setName(name);
		if (length != null)
			toUpdate.setLength(length);
		if (hasWhiskers != null)
			toUpdate.setHasWhiskers(hasWhiskers);
		if (evil != null)
			toUpdate.setEvil(evil);
		return toUpdate;
	}

	@DeleteMapping("/remove/{id}")
	public Cat removeById(@PathVariable int id) {
		return this.cats.remove(id);
	}
}
