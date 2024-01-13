package com.ems.EmployeeMGTsystem.service;

import java.util.List;

import com.ems.EmployeeMGTsystem.entity.Country;
import com.ems.EmployeeMGTsystem.entity.Employee;
import com.ems.EmployeeMGTsystem.entity.Registration;

public interface EmployeeService {

	public Registration login(String email,String password);

	public int registerUser(Registration registration);

	public List<Employee> getAllEmployee();

	public int addEmployee(Employee employee);
	
	public int updateEmployeeById(long eid,Employee employee);

	public int updateEmployeeByName(String employeeName,Employee employee);

	public String deleteEmployeeById(long eid);
	
	public List<Employee>  getEmployeeByName(String ename);
	
	public Employee getEmployeeByCountry(String cname);
	
	public Employee getEmployeeByPhoneNo(String phoneNo);

	public Employee getEmployeeByemailId(String emailId);

	public Employee getEmployeeByPassword(String password);

	public Employee getEmployeeByDepartment(String deptName);
	
	public List<Country> getAllCountry();
	
	public  String addCountry(Country country);
	
	public String updateCountry(long cid);
	
	public String deleteCountry(long cid);

    public Country getCountryByName(String cname);

	public Country getCountryByNameAndCode(String cname ,long countryCode);


	public Country getCountryById(long cid);


	

}
