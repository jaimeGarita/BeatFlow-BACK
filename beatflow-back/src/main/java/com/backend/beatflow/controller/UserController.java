package com.backend.beatflow.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    
    @GetMapping
    public String generateSalt(){
        System.out.println("TONTOO");
        return "ERES TONTO";
    }

}
