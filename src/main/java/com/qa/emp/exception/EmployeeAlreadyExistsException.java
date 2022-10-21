package com.qa.emp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Employee Already Exists with these details")
public class EmployeeAlreadyExistsException extends Exception {

}
