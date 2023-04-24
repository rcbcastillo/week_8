package com.qa.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.qa.demo.domain.Cat;
import com.qa.demo.exception.NotFoundException;
import com.qa.demo.repo.CatRepo;

@Primary
@Service
public class CatServiceDB implements CatService {
	// CatSetviceDB is dependent in repo
	private CatRepo repo;

	public CatServiceDB(CatRepo repo) {
		this.repo = repo;
	}

	@Override
	public Cat createCat(Cat c) {
		return this.repo.save(c);
	}

	@Override
	// read
	public Cat getById(int id) {
		return this.repo.findById((long) id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	// read all
	public List<Cat> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Cat delete(int id) {
		Cat removed = this.getById(id); // we use the method we defined to get the id
		this.repo.deleteById((long) id); // returns the object you deleted
		return removed;
	}

	@Override
	public Cat update(int id, String name, Integer length, Boolean whiskers, Boolean evil) {
		// find the cat
		Cat c = this.getById(id);
		// change it
		if (name != null)
			c.setName(name);
		if (length != null)
			c.setLength(length);
		if (whiskers != null)
			c.setWhiskers(whiskers);
		if (evil != null)
			c.setEvil(evil);
		// save it
		return this.repo.save(c);
	}

}
