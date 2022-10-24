package com.qa.emp.controller;

import java.util.List;

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

import com.qa.emp.entity.Employee;
import com.qa.emp.exception.EmployeeAlreadyExistsException;
import com.qa.emp.exception.EmployeeNotFoundException;
import com.qa.emp.service.EmployeeServiceImpl;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl empService;
	
	ResponseEntity<?> responseEntity;
	
	/*
	 * End Points          
	 *  getAllEmployees (GET)
	 */
	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployees(){
		try {
			List<Employee> empList = this.empService.getAllEmployees();
			responseEntity = new ResponseEntity<>(empList,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	//employees/1
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException{
		try {
			Employee employee = this.empService.getEmployeeById(id);
			responseEntity = new ResponseEntity<>(employee,HttpStatus.OK);
		} catch(EmployeeNotFoundException e) {
			throw e;
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return responseEntity;
	}
	
	
	@PostMapping("/employees")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException{
		try {
			Employee addedEmployee = this.empService.addEmployee(employee);
			System.out.println("added employee" + addedEmployee);
			responseEntity = new ResponseEntity<>(employee,HttpStatus.CREATED);
		} catch(EmployeeAlreadyExistsException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}

	
	@PutMapping("/employees")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException{
		try {
			Employee updatedEmployee = this.empService.updateEmployee(employee);			
			responseEntity = new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
		} catch(EmployeeNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException{
		try {
			boolean status = this.empService.deleteEmployee(id);			
			if(status)
			responseEntity = new ResponseEntity<>("Employee deleted Successfuly !!",HttpStatus.OK);
		} catch(EmployeeNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@GetMapping("/employees/department/{name}")
	public ResponseEntity<?> getAllEmployeesByDepartment(@PathVariable("name") String name){
		try {
			List<Employee> empListByDepartment = this.empService.getAllEmployeeByDepartment(name);
			responseEntity = new ResponseEntity<>(empListByDepartment,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/employees/gender/{gender}/department/{dept}")
	public ResponseEntity<?> findEmployeesByGenderAndDepartment(@PathVariable("gender") char gender,@PathVariable("dept") String department){
		try {
			List<Employee> empListByGenderAndDepartment = this.empService.findEmployeesByGenderAndDepartment(gender,department);
			responseEntity = new ResponseEntity<>(empListByGenderAndDepartment,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/employees/total_salary")
	public ResponseEntity<?> findTotalSalariesOfAllEmployees(){
		try {
			Double findTotalSalariesOfAllEmployees = this.empService.findTotalSalariesOfAllEmployees();
			responseEntity = new ResponseEntity<>(findTotalSalariesOfAllEmployees,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
}
