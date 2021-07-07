/**
 * 
 */
package com.andresvg8.employeesAPI.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andresvg8.employeesAPI.model.Position;

/**
 * @author ANDRES VILLALBA GAVIRIA
 *
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	@Query(name = "Position.listPositionsSortedByNameAsc")
	Set<Position> listPositionsSortedByNameAsc();

}
