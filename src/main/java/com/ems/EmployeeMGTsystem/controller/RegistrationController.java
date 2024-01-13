package com.ems.EmployeeMGTsystem.controller;

import com.ems.EmployeeMGTsystem.entity.Registration;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/registration")
@RestController
public class RegistrationController {

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid Registration registration)
    {
        return  null;
    }
}
