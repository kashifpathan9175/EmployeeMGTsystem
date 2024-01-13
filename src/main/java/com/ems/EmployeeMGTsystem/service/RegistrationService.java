package com.ems.EmployeeMGTsystem.service;

import com.ems.EmployeeMGTsystem.daoimpl.RegistrationDaoImpl;
import com.ems.EmployeeMGTsystem.entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;


public interface RegistrationService {

    public int registerUser(Registration registration);
    public Registration getRegisteredUserByEmail(String email);

    Registration getEmployeeByemailId(String email);
}
