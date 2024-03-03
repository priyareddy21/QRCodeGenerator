package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
   private final EmployeeRepository empRepo;
   @Autowired
   public EmployeeService(EmployeeRepository empRepo) {
       this.empRepo = empRepo;
   }
    public List<Employee> getAllEmployees(){
    	return empRepo.findAll();	
    }

    public Employee addEmployee( Employee employee) {
    	return empRepo.save(employee);
    }
    
    public Employee findById(Long id) {
		return empRepo.findById(id)
				.orElseThrow(()->new RuntimeException("Invalid Employee id"));
    	
    }
	
}
