package com.qa.emp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.emp.entity.Customer;
import com.qa.emp.service.CustomerService;


@RestController
@RequestMapping("api/v1")
public class AuthController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupCustomer(@Valid @RequestBody Customer customer){
		System.out.println(customer);
		return new ResponseEntity<>(this.customerService.saveCustomer(customer),HttpStatus.CREATED);
	}
	
}
