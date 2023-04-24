package com.qa.demo.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer length;
	private boolean whiskers;
	private boolean evil;
	
	public Cat() {
		super();
	}
	
	public Cat(Integer id, String name,  boolean whiskers, boolean evil, Integer length) {
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.whiskers = whiskers;
		this.evil = evil;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(evil, id, length, name, whiskers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		return evil == other.evil && Objects.equals(id, other.id) && Objects.equals(length, other.length)
				&& Objects.equals(name, other.name) && whiskers == other.whiskers;
	}

	public Cat( String name,  boolean whiskers, boolean evil, Integer length) {
		super();

		this.name = name;
		this.length = length;
		this.whiskers = whiskers;
		this.evil = evil;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public boolean isWhiskers() {
		return whiskers;
	}

	public void setWhiskers(boolean whiskers) {
		this.whiskers = whiskers;
	}

	public boolean isEvil() {
		return evil;
	}

	public void setEvil(boolean evil) {
		this.evil = evil;
	}

}