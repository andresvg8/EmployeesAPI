/**
 * 
 */
package com.andresvg8.employeesAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresvg8.employeesAPI.model.Person;

/**
 * @author ANDRES VILLALBA GAVIRIA
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
