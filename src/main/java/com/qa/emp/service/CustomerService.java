package com.qa.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.emp.entity.Customer;
import com.qa.emp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	public Customer saveCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}
	
}
