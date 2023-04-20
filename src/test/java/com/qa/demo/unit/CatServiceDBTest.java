package com.qa.demo.unit;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.demo.domain.Cat;
import com.qa.demo.repo.CatRepo;
import com.qa.demo.service.CatService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatServiceDBTest {

	@Autowired
	private CatService service;

	@MockBean
	private CatRepo repo;

	@Test
	void testUpdate() {
		int id = 1;
		Cat existing = new Cat(id, "Tiddles", true, false, 12);
		Cat updated = new Cat(id, "Fluffy", false, true, 99);
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updated)).thenReturn(updated);

//		import org.junit.jupiter.api.Assertions;
		Assertions.assertEquals(updated, this.service.update(id, updated.getName(), updated.getLength(),
				updated.isHasWhiskers(), updated.isEvil()));
	}

}
