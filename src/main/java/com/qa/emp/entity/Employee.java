package com.qa.emp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "emp_details")
public class Employee {
	
	@Id
	@Column(name = "emp_id")
	private int id;
	
	@Column(name = "emp_name")
	private String name;
	
	@Column(name = "emp_salary")
	private double salary;
	
	@Column(name = "emp_gender")
	private char gender;
	
	@Column(name = "emp_age")
	private byte age;
	
	@Column(name = "emp_dept")
	private String department;

}
