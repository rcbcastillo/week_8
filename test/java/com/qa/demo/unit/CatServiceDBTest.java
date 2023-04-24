package com.qa.demo.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.demo.domain.Cat;
import com.qa.demo.repo.CatRepo;
import com.qa.demo.service.CatService;

@SpringBootTest
public class CatServiceDBTest {

	@Autowired
	private CatService service;

	@MockBean
	private CatRepo repo;

	@Test
	void testUpdate() {
		// if it is not null set it, if you update you get exactly what you passed it
		int id = 1;
		Cat existing = new Cat(1, "Tiddles", true, false, 12);
		Cat updated = new Cat(1, "Fluffy", false, true, 15);
		Mockito.when(this.repo.findById((long) id)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		// id, name, length, whiskers, evil
		Assertions.assertEquals(updated, this.service.update(id, updated.getName(), updated.getLength(),
				updated.isWhiskers(), updated.isEvil()));

	}
	
	@Test
	void testDelete() {
		// that gets called and return
		int id = 1;
		Cat existing = new Cat(1, "Tiddles", true, false, 12);
		Mockito.when(this.repo.findById((long) id)).thenReturn(Optional.of(existing));
		Assertions.assertEquals(existing, this.service.delete(id));
	}
	
	@Test 
	void testGetAll() {
		List<Cat> catList = new ArrayList<Cat>();
		Cat existing = new Cat(1, "Tiddles", true, false, 12);
		catList.add(existing);
		Mockito.when(this.repo.findAll()).thenReturn(catList);
		Assertions.assertEquals(catList, this.service.getAll());
		
	}
}
