package com.ems.EmployeeMGTsystem.daoimpl;

import com.ems.EmployeeMGTsystem.dao.LoginDao;
import com.ems.EmployeeMGTsystem.entity.Registration;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {
    @Override
    public Registration login(String email, String password) {
        return null;
    }
}
