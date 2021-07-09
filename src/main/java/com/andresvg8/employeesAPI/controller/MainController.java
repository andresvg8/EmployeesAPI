/**
 * 
 */
package com.andresvg8.employeesAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresvg8.employeesAPI.dto.PositionResponse;
import com.andresvg8.employeesAPI.model.Employee;
import com.andresvg8.employeesAPI.model.Person;
import com.andresvg8.employeesAPI.model.Position;
import com.andresvg8.employeesAPI.service.EmployeeService;
import com.andresvg8.employeesAPI.service.PersonService;
import com.andresvg8.employeesAPI.service.PositionService;

/**
 * @author ANDRES VILLALBA GAVIRIA
 *
 */
@RestController
@RequestMapping("/api")
public class MainController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PositionService positionService;
	
	@PostMapping("/persons/create")
	public ResponseEntity<?> createPerson(@RequestBody Person person){
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
	}
	
	/**
	 * Get a person by id.
	 * @param personId
	 * @return
	 */
	@GetMapping("/persons/{personId}")
	public ResponseEntity<?> getPerson(@PathVariable Long personId){
		Optional<Person> oPerson = personService.findById(personId);
		if(!oPerson.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oPerson);
	}
	
	/**
	 * Updates a person by id and a Person object.
	 * @param personId
	 * @param person
	 * @return
	 */
	@PutMapping("/persons/update/{personId}")
	public ResponseEntity<?> updatePerson(@PathVariable Long personId, @RequestBody Person person){
		Optional<Person> oPerson = personService.findById(personId);
		if(!oPerson.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(person, oPerson.get());
		return ResponseEntity.status(HttpStatus.OK).body(personService.save(oPerson.get()));
	}
	
	/**
	 * Deletes a person by id.
	 * @param personId
	 * @return
	 */
	@DeleteMapping("/persons/delete/{personId}")
	public ResponseEntity<?> deletePerson(@PathVariable Long personId){
		Optional<Person> oPerson=personService.findById(personId);
		if(!oPerson.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		personService.deleteById(personId);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Get a list of all registered persons.
	 * @return
	 */
	@GetMapping("/persons/all")
	public List<Person> getAllPersons(){
		return StreamSupport.stream(personService.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	/**
	 * Creates a position. 
	 * @param position
	 * @return
	 */
	@PostMapping("/positions/create")
	public ResponseEntity<?> createPosition(@RequestBody Position position){
		return ResponseEntity.status(HttpStatus.CREATED).body(positionService.save(position));
	}
	
	/**
	 * Get a position by id. 
	 * @param positionId
	 * @return
	 */
	@GetMapping("/positions/{positionId}")
	public ResponseEntity<?> getPosition(@PathVariable Long positionId){
		Optional<Position> oPosition = positionService.findById(positionId);
		if(!oPosition.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oPosition);
	}
	
	/**
	 * Updates a position by id and a Position object. 
	 * @param positionId
	 * @param position
	 * @return
	 */
	@PutMapping("/positions/update/{positionId}")
	public ResponseEntity<?> updatePosition(@PathVariable Long positionId, @RequestBody Position position){
		Optional<Position> oPosition = positionService.findById(positionId);
		if(!oPosition.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(position, oPosition.get());
		return ResponseEntity.status(HttpStatus.OK).body(positionService.save(oPosition.get()));
	}
	
	/**
	 * Deletes a position by id. 
	 * @param positionId
	 * @return
	 */
	@DeleteMapping("/positions/delete/{positionId}")
	public ResponseEntity<?> deletePosition(@PathVariable Long positionId){
		Optional<Position> oPosition=positionService.findById(positionId);
		if(!oPosition.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		personService.deleteById(positionId);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Gets a list of all positions, without sorting.
	 * @return
	 */
	@GetMapping("/positions/all")
	public List<Position> getAllPositions(){
		return StreamSupport.stream(positionService.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	/**
	 * Creates an employee.
	 * @param personId
	 * @param positionId
	 * @param salary
	 * @return
	 */
	@PostMapping("/employees/create/personId/{personId}/positionId/{positionId}/salary/{salary}")
	public ResponseEntity<?> createEmployee(@PathVariable Long personId, @PathVariable Long positionId, @PathVariable Double salary){
		Optional<Person> oPerson=personService.findById(personId);
		if(!oPerson.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		employeeService.deleteByPersonId(personId);
		Optional<Position> oPosition=positionService.findById(positionId);
		if(!oPosition.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Employee employee=new Employee(oPerson.get(), oPosition.get(), salary);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
	}
	
	/**
	 * Get an employee by id.
	 * @param employeeId
	 * @return
	 */
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<?> getEmployee(@PathVariable Long employeeId){
		Optional<Employee> oEmployee = employeeService.findById(employeeId);
		if(!oEmployee.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oEmployee);
	}
	
	/**
	 * Update an employee.
	 * @param employeeId
	 * @param employee
	 * @return
	 */
	@PutMapping("/employees/update/{employeeId}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee){
		Optional<Employee> oEmployee = employeeService.findById(employeeId);
		if(!oEmployee.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Person> oPerson=personService.findById(employee.getPerson().getId());
		if(!oPerson.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Position> oPosition=positionService.findById(employee.getPosition().getId());
		if(!oPosition.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		//BeanUtils.copyProperties(employee, oEmployee.get());
		oEmployee.get().setPerson(employee.getPerson());
		oEmployee.get().setPosition(employee.getPosition());
		oEmployee.get().setSalary(employee.getSalary());
		Employee employeeResponse=employeeService.save(oEmployee.get());
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	/**
	 * Deletes an employee by id. 
	 * @param employeeId
	 * @return
	 */
	@DeleteMapping("/employees/delete/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId){
		Optional<Employee> oEmployee=employeeService.findById(employeeId);
		if(!oEmployee.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		employeeService.deleteById(employeeId);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Returns a list of all employees, without sorting. 
	 * @return
	 */
	@GetMapping("/employees/all")
	public List<Employee> getAllEmployees(){
		return (List<Employee>)employeeService.findAll();
	}
	
	/**
	 * Returns an Employee list, filtered by positionName, or employeeName, or both. If do not specify positionName or employeeName, returns all registered employees. 
	 * @return
	 */
	@GetMapping({"/employees/position-name/{positionName}/employee-name/{personName}", 
			"/employees/position-name/{positionName}/employee-name/", 
			"/employees/position-name//employee-name/{personName}", 
			"/employees/position-name//employee-name/"})
	public ResponseEntity<?> listEmployees(@PathVariable Optional<String> positionName, @PathVariable Optional<String> personName){
		/*Este Endpoint debe permitir filtrar por cargo o nombre
		Estos parámetros son opcionales, si no se envía alguno de estos, debe traer todos los empleados.*/
		List<Employee> employees;
		if((positionName.isPresent()) && (personName.isPresent()))
		{
			employees = StreamSupport.stream(employeeService.findByPositionNameOrPersonName(positionName.get(), personName.get()).spliterator(), false).collect(Collectors.toList());
		} else if(positionName.isPresent()){
			employees = StreamSupport.stream(employeeService.findByPositionName(positionName.get()).spliterator(), false).collect(Collectors.toList());
		} else if(personName.isPresent()) {
			employees = StreamSupport.stream(employeeService.findByPersonName(personName.get()).spliterator(), false).collect(Collectors.toList());
		} else {
			employees = StreamSupport.stream(employeeService.findAll().spliterator(), false).collect(Collectors.toList());
		}
		return ResponseEntity.ok(employees);
	}
	
	/**
	 * Returns an Employee list, sorted by position name, and descending salary.
	 * @return
	 */
	@GetMapping("/position-list")
	public ResponseEntity<?> listPositionsAndEmployeesSortedBySalaryDesc(){
		//Retornar una lista con todos los cargos, y dentro de los cargos todos los empleados ordenados por salario de mayor a menor.
		List<Position> positions = StreamSupport.stream(positionService.listPositionsSortedByNameAsc().spliterator(), false).collect(Collectors.toList());
		List<PositionResponse> positionResponses = new ArrayList<>();
		for(Position posit : positions) {
			PositionResponse pr=new PositionResponse();
			pr.setId(posit.getId());
			pr.setName(posit.getName());
			List<Employee> employes=StreamSupport.stream(employeeService.findByPositionId(posit.getId()).spliterator(), false).collect(Collectors.toList());
			pr.setEmployees(employes);
			positionResponses.add(pr);
		}
		return ResponseEntity.ok(positionResponses);
	}
	
	@GetMapping("/buscarPorNombrePersona/{personName}")
	public ResponseEntity<?> buscarPorNombrePersona(@PathVariable String personName){
		List<Employee> employees = StreamSupport.stream(employeeService.buscarPorNombrePersona(personName).spliterator(), false).collect(Collectors.toList());
		return ResponseEntity.ok(employees);
	}
}
