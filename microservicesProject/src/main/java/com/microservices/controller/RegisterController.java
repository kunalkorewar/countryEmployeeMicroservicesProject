package com.microservices.controller;

import com.microservices.entities.Register;
import com.microservices.registerservice.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    RegisterService service;

    //login API
    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody Register register){
        HashMap<String,Object> map=service.loginValidation(register);
        return map;
    }
}
