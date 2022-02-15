package com.basiccrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basiccrud.model.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByName(String name);

	List<Person> findBySurname(String surname);

	Optional<Person> findByEmail(String email);

	List<Person> findByAge(int age);

	List<Person> findByAgeLessThanEqual(int age);

	List<Person> findByAgeGreaterThanEqual(int age);

}
