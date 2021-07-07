package com.andresvg8.employeesAPI.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ANDRES VILLALBA GAVIRIA
 *
 */
@Entity
@NamedQueries
({
	@NamedQuery(name="Position.listPositionsSortedByNameAsc", 
		query="SELECT p FROM Position p ORDER BY p.name ASC")
})
public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4326140036697447001L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="position")
	private List<Employee> employees=new ArrayList<>();

	/**
	 * @param name
	 */
	public Position(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public Position() {
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
		this.employees = employees;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
}
