package com.qa.emp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int id;
	
	
	@NotNull
	@Size(min = 2, max = 20, message = "name must be between 2 and 20 characters only")
	@Pattern(regexp = "^[A-Za-z0-9]*", message = "invalid name, must contain only alphanumeric")
	@Column(name = "emp_name")
	private String name;
	
	
	
	@NotNull
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Invalid email")
	@Column(name = "emp_email")
	private String email;
	
	
	@NotNull	
	@Pattern(regexp = "^(6|7|8|9)\\d{9}$", message = "invalid mobile number")
	@Column(name = "emp_contactno")
	private String contactno;
	
	
	@NotNull
	@Min(0)	
	@Column(name = "emp_salary")
	private double salary;
	
	@Column(name = "emp_age")
	@Min(value = 0, message = "Age must be min 0")
	@Max(value = 100, message="Age must be less than 100")
	private int age;
	
	@Column(name = "emp_gender")
	private char gender;
	
	@Column(name = "emp_dept")
	@NotNull
	@Size(min = 2, max = 20, message = "department must be between 2 and 20 characters only")
	@Pattern(regexp = "^[A-Za-z]*", message = "invalid department, must contain only alphabets")
	private String department;
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name="address_id") private Address address;
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "emp_address", joinColumns = {
            @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "address_id", referencedColumnName = "id")
    })
	private List<Address> addressList;

}
