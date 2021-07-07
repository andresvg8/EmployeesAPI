/**
 * 
 */
package com.andresvg8.employeesAPI.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresvg8.employeesAPI.model.Position;
import com.andresvg8.employeesAPI.repository.PositionRepository;

/**
 * @author ANDRES
 *
 */
@Service
public class PositionService {
	@Autowired
	private PositionRepository repo;

	@Transactional(readOnly=true)
	public Iterable<Position> findAll() {
		return repo.findAll();
	}

	@Transactional(readOnly=true)
	public Page<Position> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Optional<Position> findById(Long id) {
		return repo.findById(id);
	}

	@Transactional
	public Position save(Position position) {
		return repo.save(position);
	}

	@Transactional
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Set<Position> listPositionsSortedByNameAsc() {
		return repo.listPositionsSortedByNameAsc();
	}
}
