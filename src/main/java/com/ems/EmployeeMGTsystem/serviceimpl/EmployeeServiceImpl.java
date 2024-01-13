package com.ems.EmployeeMGTsystem.serviceimpl;

import com.ems.EmployeeMGTsystem.daoimpl.EmployeeDaoImpl;
import com.ems.EmployeeMGTsystem.entity.Country;
import com.ems.EmployeeMGTsystem.entity.Employee;
import com.ems.EmployeeMGTsystem.entity.Registration;
import com.ems.EmployeeMGTsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDaoImpl employeeDaoImpl;

    @Override
    public List<Employee> getAllEmployee() {
        final List<Employee> allEmployee = employeeDaoImpl.getAllEmployee();

        return allEmployee;
    }

    @Override
    public Registration login(String email, String password) {

        final Registration registration = employeeDaoImpl.login(email, password);
        return registration;
    }

    @Override
    public int registerUser(Registration registration) {
        return 0;
    }

    @Override
    public int addEmployee(Employee employee) {
        int status = employeeDaoImpl.addEmployee(employee);
        return status;
    }

    @Override
    public int updateEmployeeById(long eid, Employee employee) {
        int status = employeeDaoImpl.updateEmployeeById(eid, employee);
        return status;
    }

    @Override
    public int updateEmployeeByName(String employeeName, Employee employee) {
        final int updateEmployeeByName = employeeDaoImpl.updateEmployeeByName(employeeName, employee);

        return updateEmployeeByName;
    }


    @Override
    public String deleteEmployeeById(long eid) {
        final String deleteEmployeeById = employeeDaoImpl.deleteEmployeeById(eid);
        return deleteEmployeeById;
    }

    @Override
    public List<Employee> getEmployeeByName(String ename) {
        final List<Employee> employeeByName = employeeDaoImpl.getEmployeeByName(ename);
        return employeeByName;
    }

    @Override
    public Employee getEmployeeByCountry(String cname) {

        final Employee employeeByCountry = employeeDaoImpl.getEmployeeByCountry(cname);
        return employeeByCountry;
    }

    @Override
    public Employee getEmployeeByPhoneNo(String phoneNo) {
        final Employee employeeByPhoneNo = employeeDaoImpl.getEmployeeByPhoneNo(phoneNo);
        return employeeByPhoneNo;
    }

    @Override
    public Employee getEmployeeByemailId(String emailId) {
        final Employee employeeByemailId = employeeDaoImpl.getEmployeeByemailId(emailId);
        return employeeByemailId;
    }

    @Override
    public Employee getEmployeeByPassword(String password) {

        final Employee employeeByPassword = employeeDaoImpl.getEmployeeByPassword(password);
        return employeeByPassword;
    }

    @Override
    public Employee getEmployeeByDepartment(String deptId) {
        final Employee employeeByDepartment = employeeDaoImpl.getEmployeeByDepartment(deptId);
        return employeeByDepartment;
    }

    @Override
    public List<Country> getAllCountry() {
        return employeeDaoImpl.getAllCountry();
    }

    @Override
    public String addCountry(Country country) {
        final String addCountry = employeeDaoImpl.addCountry(country);
        return addCountry;
    }

    @Override
    public String updateCountry(long cid) {
        final String updateCountry = employeeDaoImpl.updateCountry(cid);
        return updateCountry;
    }

    @Override
    public String deleteCountry(long cid) {
        final String deleteCountry = employeeDaoImpl.deleteCountry(cid);
        return deleteCountry;
    }

	@Override
	public Country getCountryByName(String cname) {
		final Country countryByName = employeeDaoImpl.getCountryByName(cname);
		return countryByName;
	}

    @Override
    public Country getCountryByNameAndCode(String cname, long countryCode) {
        return employeeDaoImpl.getCountryByNameAndCode(cname, countryCode);
    }

    @Override
    public Country getCountryById(long cid) {
        final Country countryById = employeeDaoImpl.getCountryById(cid);
        return countryById;
    }


}
