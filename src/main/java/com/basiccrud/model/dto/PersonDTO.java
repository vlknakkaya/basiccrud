package com.basiccrud.model.dto;

public class PersonDTO {

	private long id;
	private String name;
	private String surname;
	private String email;
	private int age;

	public PersonDTO() {
		super();
	}

	public PersonDTO(long id, String name, String surname, String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", age=" + age
				+ "]";
	}

}
