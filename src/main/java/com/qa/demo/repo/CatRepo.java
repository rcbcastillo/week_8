package com.qa.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.Cat;

@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {

	List<Cat> findByNameOrderByLengthAsc(String name);
}
