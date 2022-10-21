package com.qa.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.emp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	/*
	 * It will implement all the basic CRUD Methods
	 * 
	 * 
	 */

}
