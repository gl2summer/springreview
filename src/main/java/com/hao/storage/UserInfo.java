package com.hao.storage;

public class UserInfo {
	private Integer id;
	private String name;
	private Integer age;
	
	
	public UserInfo(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	
	public UserInfo(Integer id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
