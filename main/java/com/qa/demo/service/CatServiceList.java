package com.qa.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qa.demo.domain.Cat;

@Service
public class CatServiceList implements CatService {
	private List<Cat> cats = new ArrayList<>();
	@Override
	public Cat createCat(Cat c) {
		this.cats.add(c);
		return this.cats.get(this.cats.size() -1);
	}

	@Override
	public Cat getById(int id) {
		return this.cats.get(id);
	}

	@Override
	public List<Cat> getAll() {
		return this.cats;
	}

	@Override
	public Cat delete(int id) {
		return this.cats.remove(id);
	}

	@Override
	public Cat update(int id, String name, Integer length, Boolean whiskers, Boolean evil) {
		Cat c = this.cats.get(id);
		if (evil != null) c.setEvil(evil);
		if (whiskers != null) c.setWhiskers(whiskers);
		if (length != null) c.setLength(length);
		if (name != null) c.setName(name);
		return c;
	}
	
}
