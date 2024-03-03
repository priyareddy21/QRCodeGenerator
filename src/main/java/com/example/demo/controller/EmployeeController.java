package com.example.demo.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.QRCodeGenerator;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.google.zxing.WriterException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	    @GetMapping("all")
	    public String getAllEmployees(Model model) throws Exception {
	    	 List<Employee> emp = employeeService.getAllEmployees();
	         if (emp.size() != 0){
	             for (Employee employee : emp){
	                 QRCodeGenerator.generateQRCode(employee);
	             }
	         }
	         model.addAttribute("employees", emp);
	         return "employees";

	       // return ResponseEntity.ok(employeeService.getAllEmployees());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	        return ResponseEntity.ok(employeeService.findById(id));
	    }

	    @PostMapping("/all")
	    public Employee addEmployee(@RequestBody Employee employee) {
	        return employeeService.addEmployee(employee);

	    }



	


	 

}
