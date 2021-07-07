package com.andresvg8.employeesAPI.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * @author ANDRES VILLALBA GAVIRIA
 *
 */
@Entity
@NamedQueries
({
    @NamedQuery(name = "Employee.findByPositionIdOrPersonId", 
		query = "SELECT e FROM Employee e WHERE e.position.id = :positionId OR e.person.id = :personId"),
    @NamedQuery(name = "Employee.findByPositionNameOrPersonName", 
		query = "SELECT e FROM Employee e WHERE e.position.name LIKE :positionName OR e.person.name LIKE :personName OR e.person.lastName LIKE :personName"),
    @NamedQuery(name = "Employee.findByPositionName", 
		query = "SELECT e FROM Employee e WHERE e.position.name = :positionName"),
    @NamedQuery(name = "Employee.findByPersonName", 
		query = "SELECT e FROM Employee e WHERE e.person.name = :personName OR e.person.lastName LIKE :personName"),
    @NamedQuery(name = "Employee.findAllWithPositionAndSalary", 
		query = "SELECT e FROM Employee e ORDER BY e.position.name, e.salary DESC"),
	@NamedQuery(name = "Employee.deleteByPersonId", 
		query = "DELETE FROM Employee WHERE person.id = :personId"), 
	@NamedQuery(name = "Employee.findByPositionId", 
		query = "SELECT e FROM Employee e WHERE e.position.id = :positionId ORDER BY e.salary DESC")
})
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3655220476690349846L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Person person;
	
	@ManyToOne(fetch = FetchType.EAGER/*, cascade={CascadeType.PERSIST, CascadeType.MERGE}*/)
	@JoinColumn(name = "position_id")
	private Position position;
	
	private Double salary;

	/**
	 * @param person
	 * @param position
	 * @param salary
	 */
	public Employee(Person person, Position position, Double salary) {
		this.person = person;
		this.position = position;
		this.salary = salary;
	}

	/**
	 * 
	 */
	public Employee() {
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}
}
