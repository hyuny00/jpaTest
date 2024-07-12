package com.example.dto;

import com.example.db1.entity.User1;

public class UserDto {

	
	public UserDto(Long id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	
	public UserDto(User1 u) {
		this.id = u.getId();
		this.name = u.getName();
		this.age = u.getAge();
	}

	private Long id;

	private String name;

	private Integer age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
	

}
