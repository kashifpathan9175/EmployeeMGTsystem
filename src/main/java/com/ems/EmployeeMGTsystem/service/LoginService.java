package com.ems.EmployeeMGTsystem.service;

import com.ems.EmployeeMGTsystem.entity.Registration;


public interface LoginService {
    public Registration login(String email, String password);

}
