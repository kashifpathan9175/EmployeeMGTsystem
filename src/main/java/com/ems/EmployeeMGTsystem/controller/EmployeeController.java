package com.ems.EmployeeMGTsystem.controller;


import com.ems.EmployeeMGTsystem.entity.Registration;
import com.ems.EmployeeMGTsystem.serviceimpl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ems.EmployeeMGTsystem.entity.Country;
import com.ems.EmployeeMGTsystem.entity.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin()
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;






	@RequestMapping("/getAllEmployee")
	public ResponseEntity<Object> getAllEmployee()// gets all the employee from database
	{
		return null;
	}
	
	//add employee
	
	@PostMapping("/addEmployee")// adds employee to database
	public  ResponseEntity<Object> addEmployee( @Valid @RequestBody Employee employee)
	{
		try {
			if (employee != null) {
				final int status = employeeService.addEmployee(employee);
				if (status == 1) {
					return new ResponseEntity<Object>("Employee added successfully", HttpStatus.OK);
				} else if (status == 2) {
					return new ResponseEntity<Object>("Employee not added ", HttpStatus.BAD_REQUEST);
				} else if (status == 3) {
					return new ResponseEntity<Object>("Country cannot be empty", HttpStatus.BAD_REQUEST);
				}
//			else if (status == 4)
//			{
//				return new ResponseEntity<Object>("Email address already in use", HttpStatus.BAD_REQUEST);
//			}
//			else if (status == 5)
//			{
//				return new ResponseEntity<Object>("phone number already in use", HttpStatus.BAD_REQUEST);
//			}
//			else if (status == 6)
//			{
//				return new ResponseEntity<Object>("password already in use", HttpStatus.BAD_REQUEST);
//			}
			} else {
				return new ResponseEntity<Object>("Employee is null", HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Object>("something wrong", HttpStatus.BAD_GATEWAY);
		}
		catch (DataIntegrityViolationException ex)
		{
			final String errorMessage = ex.getMostSpecificCause().getLocalizedMessage();
			System.out.println(errorMessage);
			if (errorMessage.contains("employee.UK_af534w03av8srcldugewrmpbi")) {
				return new ResponseEntity<>("Email address already in use", HttpStatus.BAD_REQUEST);
			} else if (errorMessage.contains("employee.UK_cw6rauu79yg8p8qly01ae6uf3")) {
				return new ResponseEntity<>("Phone number already in use", HttpStatus.BAD_REQUEST);
			} else if (errorMessage.contains("employee.UK_7u0lbcpn8njh0q3h74ii9qaq3")) {
				return new ResponseEntity<>("Password is already in use", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Data Integrity Violation $ " + errorMessage, HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PutMapping("/updateEmployeeById/{eid}") //update employee into database by using id
	public ResponseEntity<Object> updateEmployeeById( @PathVariable long eid,@RequestBody @Valid  Employee employee)
	{
		final int dbEmployee  = employeeService.updateEmployeeById(eid, employee);
		if(dbEmployee == 1){
			return new ResponseEntity<Object>("Employee updated successfully", HttpStatus.OK);
		}
		else if (dbEmployee == 5){
			return new ResponseEntity<Object>("password is already in use", HttpStatus.BAD_REQUEST);
		}
		else if (dbEmployee == 6) {
			return new ResponseEntity<Object>("phone is already in use", HttpStatus.BAD_REQUEST);
		} else if (dbEmployee ==3) {
			return new ResponseEntity<Object>("email id is already in use", HttpStatus.BAD_REQUEST);
		} else if (dbEmployee == 2) {
			return new ResponseEntity<Object>("employee data cannot be empty",HttpStatus.FORBIDDEN);
		}

		else {
			return new ResponseEntity<Object>("something wrong", HttpStatus.BAD_GATEWAY);
		}


	}
	
	@DeleteMapping("/deleteEmployee/{eid}")// delete employee from database using employee id
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable long eid)
	{
		final String s = employeeService.deleteEmployeeById(eid);
		
		if (s.equals("Employee deleted"))
		return new ResponseEntity<Object>("Employee deleted successfully",HttpStatus.OK);

		else if (s.equals("Employee not found")) {
			return  new ResponseEntity<>("Employee not found",	HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>("something wrong",	HttpStatus.BAD_GATEWAY);

	}
	
	@RequestMapping("/getEmployeeByName/{name}") //gets employee from database using employee name
	public ResponseEntity<Object> getEmployeeByName(@PathVariable String name)
	{
		return null;
	}
	
	@RequestMapping("/getEmployeeByCountry/{countryName}") // fetch employee from database using country name
	public ResponseEntity<Object> getEmployeeByCountry(@PathVariable String countryName)
	{
		return null;
	}
	
	@GetMapping("/getEmployeeByPhoneNo/{phoneNo}") //fetches employee from database using phone number
	public ResponseEntity<Object> getEmployeeByPhoneNo( @PathVariable String phoneNo)
	{
		final Employee employeeByPhoneNo = employeeService.getEmployeeByPhoneNo(phoneNo);

		if (employeeByPhoneNo != null)
		{
			System.out.println("EmployeeByPhoneNo: " + employeeByPhoneNo);
			return new ResponseEntity<Object> (employeeByPhoneNo,HttpStatus.FOUND);
		}
		else
		{
			return  new ResponseEntity<Object> ("No employee found with phone number ",HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/getEmployeeByEmailId/{emailId}")// fetches the employee by the given email
	public ResponseEntity<Object> getEmployeeByEmailId(@PathVariable String emailId)
	{
		final Employee employee = employeeService.getEmployeeByemailId(emailId);
		if (employee!= null)
		{
			return new ResponseEntity<Object>(employee, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Object>("Employee not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getEmployeeByPassword/{password}")
	public ResponseEntity<Object> getEmployeeByPassword(@PathVariable String password)
	{
		final Employee isExists = employeeService.getEmployeeByPassword(password);

		if (isExists != null)
		{
			return  new ResponseEntity<Object>(isExists,HttpStatus.FOUND);
		}
		else
		{
			return  new ResponseEntity<Object>("Employee doesn't exist with this password",HttpStatus.NOT_FOUND);
		}

	}
	
	@RequestMapping("/getEmployeeByDepartment/{deptName}") // fetches the employee by the department
	public ResponseEntity<Object> getEmployeeByDepartment(@PathVariable String deptName)
	{
		return null;
	}
	
	@GetMapping("/getAllCountry")
	public ResponseEntity<Object> getAllCountry() // fetch all country
	{
		final List<Country> listOfCountry = employeeService.getAllCountry();

		if (listOfCountry != null)
		{
			return new ResponseEntity<Object>(listOfCountry, HttpStatus.OK);
		}
		else {
			return  new ResponseEntity<Object>("list is null", HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/addCountry") // add country information to the database
	public ResponseEntity<Object> addCountry(@RequestBody Country country) {
		final String countryAdded = employeeService.addCountry(country);

		if (countryAdded.equals("Country added")) {
			return new ResponseEntity<Object>("Country added successfully",HttpStatus.OK);
		}
		else if (countryAdded.equals("Country code cannot be less than zero")) {
            return  new ResponseEntity<>("Country code cannot be less than zero",    HttpStatus.NOT_FOUND);
        }

		else {
			return new ResponseEntity<Object>("Country cannot be Null", HttpStatus.NOT_ACCEPTABLE);
		}


	}

	@RequestMapping("/updateCountry/{cid}") //update country by country id
	public ResponseEntity<Object> updateCountry(long cid) {
		// TODO Auto-generated method stub
		return null;
	}


	@RequestMapping("/deleteCountry/{cid}")// deleteCountry by country id
	public ResponseEntity<Object> deleteCountry(long cid) {
		return null;
	}

	@RequestMapping("/getCountryById/{cid}") //get country by country id
	public ResponseEntity<Object> getCountryById(@PathVariable long cid) {
		final Country countryById = employeeService.getCountryById(cid);
		if (countryById!= null){
			return new ResponseEntity<Object>(countryById,HttpStatus.OK);
		}
		else
		return new ResponseEntity<>("country cannot be found",HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getCountryByCnameAndCcode/{countryName}/{countryCode}")
	public ResponseEntity<Object> getCountryByCnameAndCcode(@PathVariable String countryName, @PathVariable long countryCode) {

		final Country countryByNameAndCode = employeeService.getCountryByNameAndCode(countryName, countryCode);
		if (countryByNameAndCode != null) {
			return  new ResponseEntity<Object>(countryByNameAndCode,HttpStatus.OK);
		}

		else {
			return new ResponseEntity<Object>("country not found", HttpStatus.NOT_FOUND);
		}
		}
	@RequestMapping("/getCountryByName/{cname}") // get country by name
	public ResponseEntity<Object> getCountryByName(@PathVariable String cname) {

		final Country countryByName = employeeService.getCountryByName(cname);
		if (countryByName != null)
		{
			return new ResponseEntity<Object>(countryByName,HttpStatus.OK);
		} else if (countryByName == null) {
			return new ResponseEntity<Object>("country cannot be found", HttpStatus.NOT_FOUND);
		}
		else
		return new ResponseEntity<Object>("something is wrong", HttpStatus.NO_CONTENT);

	}
	

}
