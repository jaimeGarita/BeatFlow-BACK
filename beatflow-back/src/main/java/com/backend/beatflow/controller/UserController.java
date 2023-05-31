package com.backend.beatflow.controller;

import java.security.Key;
import java.util.Date;

import org.apache.catalina.connector.Response;
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

import com.backend.beatflow.model.Test;
import com.backend.beatflow.model.UserModel;
import com.backend.beatflow.services.tokenService.TokenServiceImpl;
import com.backend.beatflow.services.userService.UserServiceImpl;
import com.backend.beatflow.utils.JwtUtil;
import com.backend.beatflow.utils.PasswordUtil;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel user) {

        UserModel userDb = userService.getUserByUsername(user.getUserName());
        if (userDb != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO EXISTE EL USUARIO");
        }

        /**
         * VOY A TENER QUE HACER LO SIGUIENTE
         * 
         * 
         * COMPROBAR LA CONTRASEÑA EN CASO DE EXISTA
         * 
         * HAGO UN POST PORQUE VOY A TENER QUE ALMACENAR EL TOKEN
         * 
         */

        return ResponseEntity.ok().body("ENTRA");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel user) {

        UserModel userDb = userService.getUserByUsername(user.getUserName());

        if (userDb != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO EXISTE EL USUARIO");
        }

        String salt = PasswordUtil.generateSalt();
        user.setSalt(salt);
        userService.save(user);
        String token = saveToken(user);

        // Devolver el token JWT en la respuesta
        return ResponseEntity.ok().body(token);

    }

    private String saveToken(UserModel user) {
        // Generar el token JWT
        String token = JwtUtil.generateToken("1", user.getUserName(), user.getEmail());

        Key signingKey = JwtUtil.SECRET_KEY;
        Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
        Date expirationDate = claims.getExpiration();

        // Guardar el token en la base de datos
        tokenService.saveToken(user.getIdUser(), token, expirationDate);

        return token;
    }

}
