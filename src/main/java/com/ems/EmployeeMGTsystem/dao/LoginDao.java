package com.ems.EmployeeMGTsystem.dao;

import com.ems.EmployeeMGTsystem.entity.Registration;

public interface LoginDao {
    public Registration login(String email, String password);

}
