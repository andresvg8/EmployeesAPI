package com.andresvg8.employeesAPI.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OrderBy;

import com.andresvg8.employeesAPI.model.Employee;

/**
 * @author ANDRES VILLALBA GAVIRIA
 *
 */
public class PositionResponse {
	private Long id;
	private String name;
	@OrderBy("salary DESC")
	private List<Employee> employees=new ArrayList<>();
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}
	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		//this.employees = employees;
		for(Employee empl : employees) {
			this.employees.add(empl);
		}
	}
}
