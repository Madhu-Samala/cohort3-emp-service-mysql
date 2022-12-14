package com.qa.emp.service;

import java.util.List;

import com.qa.emp.dto.EmployeeDto;
import com.qa.emp.entity.Employee;
import com.qa.emp.exception.EmployeeAlreadyExistsException;
import com.qa.emp.exception.EmployeeNotFoundException;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException;
	public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException;
	public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException;
	public boolean deleteEmployee(int id) throws EmployeeNotFoundException;
	public List<Employee> getAllEmployeeByDepartment(String name);
	public List<Employee> findEmployeesByGenderAndDepartment(char gender, String department);
	public Double findTotalSalariesOfAllEmployees();
	 public Employee updateEmployeeDetails(int id, String department, double salary) throws EmployeeNotFoundException;

	 public List<EmployeeDto> getEmployeeDeptDetails();
}
