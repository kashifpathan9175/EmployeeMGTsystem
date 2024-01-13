package com.ems.EmployeeMGTsystem.dao;

import com.ems.EmployeeMGTsystem.entity.Registration;

public interface RegistrationDao {

    public int registerUser(Registration registration);

    public Registration getRegisteredUserByEmail(String email);

    Registration getEmployeeByEmailId(String email);
}
