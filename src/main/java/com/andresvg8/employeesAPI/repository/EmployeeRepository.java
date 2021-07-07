/**
 * 
 */
package com.andresvg8.employeesAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andresvg8.employeesAPI.model.Employee;

/**
 * @author ANDRES VILLALBA GAVIRIA
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Transactional
	@Modifying
	@Query(name="Employee.deleteByPersonId")
	void deleteByPersonId(Long personId);
	
	@Query(name = "Employee.findByPositionNameOrPersonName")
	List<Employee> findByPositionNameOrPersonName(String positionName, String personName);

	@Query(name = "Employee.findByPositionName")
	List<Employee> findByPositionName(String positionName);

	@Query(name = "Employee.findByPersonName")
	List<Employee> findByPersonName(String personName);

	@Query(name = "Employee.findAllWithPositionAndSalary")
	List<Employee> findAllWithPositionAndSalary();
	
	@Query(name = "Employee.findByPositionId")
	List<Employee> findByPositionId(Long positionId);
}
