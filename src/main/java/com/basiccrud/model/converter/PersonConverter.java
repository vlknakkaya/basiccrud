package com.basiccrud.model.converter;

import org.springframework.stereotype.Component;

import com.basiccrud.model.dto.PersonDTO;
import com.basiccrud.model.entity.Person;

@Component
public class PersonConverter implements DTOConverter<Person, PersonDTO> {

	@Override
	public Person convertToEntity(PersonDTO dto) {
		if (dto == null) {
			return null;
		}

		Person entity = new Person();
		entity.setName(dto.getName());
		entity.setSurname(dto.getSurname());
		entity.setEmail(dto.getEmail());
		entity.setAge(dto.getAge());

		return entity;
	}

	@Override
	public PersonDTO convertToDTO(Person entity) {
		if (entity == null) {
			return null;
		}

		PersonDTO dto = new PersonDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setEmail(entity.getEmail());
		dto.setAge(entity.getAge());

		return dto;
	}

}
