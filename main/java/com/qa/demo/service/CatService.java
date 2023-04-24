package com.qa.demo.service;

import java.util.List;
import com.qa.demo.domain.Cat;

public interface CatService {	
	
	Cat createCat(Cat c);
	Cat getById(int id);
	List<Cat> getAll();
	Cat delete(int id);
	Cat update(int id, String name, Integer length, Boolean whiskers, Boolean evil);

}
