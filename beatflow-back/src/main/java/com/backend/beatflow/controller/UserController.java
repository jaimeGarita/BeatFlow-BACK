package com.backend.beatflow.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.beatflow.model.UserModel;
import com.backend.beatflow.respository.UserRepository;
import com.backend.beatflow.services.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired 
    UserServiceImpl userService;
    
    @GetMapping
    public String generateSalt(@RequestHeader("Authorization") String authorizationHeader){
        System.out.println("Authorization Header: " + authorizationHeader);

        return "ERES TONTO";
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(UserModel user){

        Optional<UserModel> userDb = userService.getUserById(user.getIdUser());

        if(userDb.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TEST");
        }

        return ResponseEntity.ok().body("BUILD");

    }

}
