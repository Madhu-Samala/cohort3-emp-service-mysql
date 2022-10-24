package com.qa.emp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.emp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	/*
	 * It will implement all the basic CRUD Methods
	 * findById(int id)  Optional<Employee>
	 * findAll() List<Employee>
	 * save(Employee) Employee
	 * delete(Employee) void
	 * 
	 * 
	 * select * from employee where ..... group by  order by having
	 * aggregate function
	 * update ...
	 * delete ...
	 *
	 *Query Method
	 */
	 //select * from emp_details where emp_dept = 'development'
	  List<Employee> findByDepartment(String department);
	  
	  //Find Employees by gender and department
	  //select * from emp_details where emp_gender = 'M' and emp_department = 'development'
	  /*
	   * Positional parameters - ?1, 2
	   */
	  /*
	  @Query(value = "select * from emp_details where emp_gender= ?1  and emp_dept = ?2 ", nativeQuery = true)
	  List<Employee> findEmployeesByGenderAndDepartment(char gender, String department);
	  */
	  /*
	   * Named Parameters -> :gender, :department
	   */
	  /*
	  @Query(value = "select * from emp_details where emp_gender= :gender  and emp_dept = :department ", nativeQuery = true)
	  List<Employee> findEmployeesByGenderAndDepartment(char gender, String department);
	  */
	  
	  //select sum(emp_salary) from emp_details
	  @Query(value = "select sum(emp_salary) from emp_details", nativeQuery = true)
	  Double findTotalSalariesOfAllEmployees();
	  
	  //JPQL
	  
	  @Query("select e from Employee e where e.gender = :gender  and e.department = :department")
	  List<Employee> findEmployeesByGenderAndDepartment(char gender, String department);
		
	
	

}
