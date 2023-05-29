package com.backend.beatflow.controller;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.beatflow.model.UserModel;
import com.backend.beatflow.services.tokenService.TokenServiceImpl;
import com.backend.beatflow.services.userService.UserServiceImpl;
import com.backend.beatflow.utils.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TokenServiceImpl tokenService;

    @GetMapping
    public String generateSalt(@RequestHeader("Authorization") String authorizationHeader) {
        System.out.println("Authorization Header: " + authorizationHeader);

        return "ERES TONTO";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel user) {

        UserModel userDb = userService.getUserByUsername(user.getUserName());

        if (userDb != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TEST");
        }
        
        // Generar el token JWT
        String token = JwtUtil.generateToken("1",user.getUserName(), user.getEmail());

        Key signingKey = JwtUtil.SECRET_KEY;
        Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
        Date expirationDate = claims.getExpiration();

        // Guardar el token en la base de datos
        tokenService.saveToken(user.getIdUser(), token, expirationDate);

        // Devolver el token JWT en la respuesta
        return ResponseEntity.ok().body(token);

    }

    private boolean verifyUserCredentials(String username, String password) {
      
        UserModel user = userService.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true; // Las credenciales son válidas
        } else {
            return false; // Las credenciales son inválidas
        }
    }

}
