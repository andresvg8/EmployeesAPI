/**
 * 
 */
package com.andresvg8.employeesAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresvg8.employeesAPI.model.Employee;
import com.andresvg8.employeesAPI.repository.EmployeeRepository;

/**
 * @author ANDRES
 *
 */
@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repo;

	@Transactional(readOnly=true)
	public Iterable<Employee> findAll() {
		return repo.findAll();
	}

	@Transactional(readOnly=true)
	public Page<Employee> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Optional<Employee> findById(Long id) {
		return repo.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Employee> findByPositionNameOrPersonName(String positionName, String personName) {
		return repo.findByPositionNameOrPersonName(positionName, personName);
	}

	@Transactional(readOnly=true)
	public List<Employee> findByPositionName(String positionName) {
		return repo.findByPositionName(positionName);
	}

	@Transactional(readOnly=true)
	public List<Employee> findByPersonName(String personName) {
		return repo.findByPersonName(personName);
	}

	@Transactional
	public Employee save(Employee employee) {
		return repo.save(employee);
	}

	@Transactional
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	@Transactional
	public void deleteByPersonId(Long personId) {
		repo.deleteByPersonId(personId);
	}

	public List<Employee> findAllWithPositionAndSalary() {
		return repo.findAllWithPositionAndSalary();
	}
	
	@Transactional
	public List<Employee> findByPositionId(Long positionId){
		return repo.findByPositionId(positionId);
	}
}
