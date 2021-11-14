package net.sriram.springbootpost.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sriram.springbootpost.exception.ResourceNotFoundException;
import net.sriram.springbootpost.model.Employee;
import net.sriram.springbootpost.repository.EmployeeRepository;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get  all Employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();		
	}
	
	//create an employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
		
	}
	//get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
		throws ResourceNotFoundException{
			Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not fouund for this is ::"+employeeId));
					return ResponseEntity.ok().body(employee);
		
	}
	//save employee
	//update
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,  
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not fouund for this is ::"+employeeId));
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		
		return ResponseEntity.ok(this.employeeRepository.save(employee));
	}
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
	
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not fouund for this is ::"+employeeId));
		
		this.employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
	}
}
