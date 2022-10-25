package com.qa.emp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.emp.dto.EmployeeDto;
import com.qa.emp.entity.Employee;
import com.qa.emp.exception.EmployeeAlreadyExistsException;
import com.qa.emp.exception.EmployeeNotFoundException;
import com.qa.emp.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	ModelMapper modelMapper;
	/*
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	} */

	@Override
	public List<Employee> getAllEmployees() {
		
		return this.empRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {		
		
		 Optional<Employee> findByIdOptional = this.empRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new EmployeeNotFoundException();
		return findByIdOptional.get();
	}

	@Override
	public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException {
		/*
		 * Check if employee is already present in the list  if yes
		 * throw EmployeeAlreadyExistsException
		 * 
		 */
		 Optional<Employee> findByIdOptional = this.empRepository.findById(employee.getId());
		 if(findByIdOptional.isPresent())
			 throw new EmployeeAlreadyExistsException();
		 else
			 return this.empRepository.save(employee);
		
	}

	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
		
		 Optional<Employee> findByIdOptional = this.empRepository.findById(employee.getId());
		 if(!findByIdOptional.isPresent())
			 throw new EmployeeNotFoundException();
		 else
			 return this.empRepository.save(employee);
	}

	@Override
	public boolean deleteEmployee(int id) throws EmployeeNotFoundException {
		boolean status = false;
		Optional<Employee> findByIdOptional = this.empRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new EmployeeNotFoundException();
		 
		 this.empRepository.delete(findByIdOptional.get());
		 status = true;
		 
		 return status;
	}

	@Override
	public List<Employee> getAllEmployeeByDepartment(String name) {
		
		return this.empRepository.findByDepartment(name);
	}

	@Override
	public List<Employee> findEmployeesByGenderAndDepartment(char gender, String department) {
		
		return this.empRepository.findEmployeesByGenderAndDepartment(gender, department);
	}

	@Override
	public Double findTotalSalariesOfAllEmployees() {
		
		return this.empRepository.findTotalSalariesOfAllEmployees();
	}

	@Override
	public Employee updateEmployeeDetails(int id, String department, double salary) throws EmployeeNotFoundException {
		Employee updatedEmployee = null;
		 Optional<Employee> findByIdOptional = this.empRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new EmployeeNotFoundException();
		 else{
			 int rows = this.empRepository.updateEmployeeDetails(id, department, salary);
			 if(rows > 0)
				 updatedEmployee = this.empRepository.findById(id).get();
		 }
		 return updatedEmployee;
	}

	@Override
	public List<EmployeeDto> getEmployeeDeptDetails() {
		/*List<Employee> empList = this.empRepository.findAll();
		List<EmployeeDto> empDtoList = new ArrayList<>();
		empList.forEach(emp ->{
			EmployeeDto empDto = EmployeeDto.builder().id(emp.getId())
					.name(emp.getName())
					.department(emp.getDepartment())
					.salary(emp.getSalary()).build();
				empDtoList.add(empDto);
			
		});
		
		return empDtoList; */
		
		return this.empRepository.findAll().stream().map(this::mapToEmployeeDto).collect(Collectors.toList());
		
	}
	
	private EmployeeDto mapToEmployeeDto(Employee employee) {
		return this.modelMapper.map(employee, EmployeeDto.class);
	}

}
