package com.ems.EmployeeMGTsystem.serviceimpl;

import com.ems.EmployeeMGTsystem.dao.RegistrationDao;
import com.ems.EmployeeMGTsystem.entity.Registration;
import com.ems.EmployeeMGTsystem.service.RegistrationService;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

@Autowired
    RegistrationDao registrationDao;
    @Override
    public int registerUser(Registration registration) {
        return 0;
    }

    @Override
    public Registration getRegisteredUserByEmail(String email) {
        return registrationDao.getRegisteredUserByEmail(email);
    }

    @Override
    public Registration getEmployeeByemailId(String email) {
        return registrationDao.getEmployeeByEmailId(email);
    }
}
