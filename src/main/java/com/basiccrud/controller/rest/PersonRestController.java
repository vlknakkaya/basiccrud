package com.basiccrud.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basiccrud.model.converter.PersonConverter;
import com.basiccrud.model.dto.PersonDTO;
import com.basiccrud.model.entity.Person;
import com.basiccrud.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonRestController {

	@Autowired
	private PersonService personService;
	@Autowired
	private PersonConverter personConverter;

	@GetMapping
	public List<PersonDTO> getAllPersons() {
		return personConverter.convertToDTOList(personService.findAll());
	}

	@GetMapping("/{id}")
	public PersonDTO getPersonById(@PathVariable long id) {
		return personConverter.convertToDTO(personService.findById(id));
	}

	@GetMapping("/name/{name}")
	public List<PersonDTO> getPersonsByName(@PathVariable String name) {
		return personConverter.convertToDTOList(personService.findByName(name));
	}

	@GetMapping("/surname/{surname}")
	public List<PersonDTO> getPersonsBySurname(@PathVariable String surname) {
		return personConverter.convertToDTOList(personService.findBySurname(surname));
	}

	@GetMapping("/email/{email}")
	public PersonDTO getPersonByEmail(@PathVariable String email) {
		return personConverter.convertToDTO(personService.findByEmail(email));
	}

	@GetMapping("/age/{age}")
	public List<PersonDTO> getPersonsByAge(@PathVariable int age, @RequestParam(required = false) String type) {
		List<Person> persons = personService.findByAge(age);

		if (type != null) {
			switch (type) {
			case "le":
				persons = personService.findByAgeLessThanEqual(age);
				break;
			case "ge":
				persons = personService.findByAgeGreaterThanEqual(age);
				break;

			default:
				break;
			}
		}

		return personConverter.convertToDTOList(persons);
	}

	@PostMapping
	public ResponseEntity<String> createPerson(@RequestBody PersonDTO person) {
		if (personService.save(personConverter.convertToEntity(person)) != null) {
			return new ResponseEntity<>("Person was created successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error while creating new person, please see logs.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updatePerson(@PathVariable long id, @RequestBody PersonDTO person) {
		Person entity = personService.findById(id);

		if (StringUtils.hasText(person.getName())) {
			entity.setName(person.getName());
		}

		if (StringUtils.hasText(person.getSurname())) {
			entity.setSurname(person.getSurname());
		}

		if (StringUtils.hasText(person.getEmail())) {
			entity.setEmail(person.getEmail());
		}

		if (StringUtils.hasText(String.valueOf(person.getAge()))) {
			entity.setAge(person.getAge());
		}

		if (personService.save(entity) != null) {
			return new ResponseEntity<>("Person was updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error while updating new person, please see logs.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removePersonById(@PathVariable long id) {
		personService.removeById(id);

		return new ResponseEntity<>("Person was removed successfully.", HttpStatus.OK);
	}

}
