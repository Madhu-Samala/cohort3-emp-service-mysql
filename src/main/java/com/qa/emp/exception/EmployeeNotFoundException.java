package com.qa.emp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//if code throws this exception, the we send the response to the client
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Employee found with this id")
public class EmployeeNotFoundException extends Exception {
	
	

}
