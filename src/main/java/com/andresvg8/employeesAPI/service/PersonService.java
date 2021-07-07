/**
 * 
 */
package com.andresvg8.employeesAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresvg8.employeesAPI.model.Person;
import com.andresvg8.employeesAPI.repository.PersonRepository;

/**
 * @author ANDRES
 *
 */
@Service
public class PersonService {
	@Autowired
	private PersonRepository repo;

	@Transactional(readOnly=true)
	public Iterable<Person> findAll() {
		return repo.findAll();
	}

	@Transactional(readOnly=true)
	public Page<Person> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Optional<Person> findById(Long id) {
		return repo.findById(id);
	}

	@Transactional
	public Person save(Person person) {
		return repo.save(person);
	}

	@Transactional
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
}
