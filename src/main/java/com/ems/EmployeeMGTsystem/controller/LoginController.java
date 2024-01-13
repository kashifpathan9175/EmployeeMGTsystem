package com.ems.EmployeeMGTsystem.controller;

import com.ems.EmployeeMGTsystem.entity.Employee;
import com.ems.EmployeeMGTsystem.entity.Registration;
import com.ems.EmployeeMGTsystem.service.EmployeeService;
import com.ems.EmployeeMGTsystem.service.LoginService;
import com.ems.EmployeeMGTsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin()
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/loggingin")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Registration registration)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        final Registration employeeByemailId = registrationService.getEmployeeByemailId(registration.getEmail());

        if (employeeByemailId != null)
        {
            if(employeeByemailId.getEmailId().equals(registration.getEmail()) && employeeByemailId.getPassword().equals(registration.getPassword()))
            {
                map.put("message","Valid User");
                map.put("UserId",employeeByemailId);

                return  ResponseEntity.status(HttpStatus.OK).body(map);
            }
            else {
                map.put("message","Invalid User");
                map.put("User",employeeByemailId);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        }
        else
        {
            map.put("message","Employee not found with this email address");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }

    }
}
