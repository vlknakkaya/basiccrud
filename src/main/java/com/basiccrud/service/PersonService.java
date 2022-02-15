package com.basiccrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basiccrud.exception.EntityNotFoundException;
import com.basiccrud.model.entity.Person;
import com.basiccrud.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	public Person findById(long id) {
		return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person", "id", id));
	}

	public List<Person> findByName(String name) {
		return personRepository.findByName(name);
	}

	public List<Person> findBySurname(String surname) {
		return personRepository.findBySurname(surname);
	}

	public Person findByEmail(String email) {
		return personRepository.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException("Person", "email", email));
	}

	public List<Person> findByAge(int age) {
		return personRepository.findByAge(age);
	}

	public List<Person> findByAgeLessThanEqual(int age) {
		return personRepository.findByAgeLessThanEqual(age);
	}

	public List<Person> findByAgeGreaterThanEqual(int age) {
		return personRepository.findByAgeGreaterThanEqual(age);
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public void removeById(long id) {
		personRepository.deleteById(id);
	}

}
