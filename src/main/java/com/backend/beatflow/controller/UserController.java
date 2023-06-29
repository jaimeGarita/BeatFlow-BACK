package com.backend.beatflow.controller;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.beatflow.model.UserModel;
import com.backend.beatflow.model.DTO.UserDTO;
import com.backend.beatflow.model.mapper.UserMaperImpl;
import com.backend.beatflow.services.tokenService.TokenServiceImpl;
import com.backend.beatflow.services.userService.UserServiceImpl;
import com.backend.beatflow.utils.JwtUtil;
import com.backend.beatflow.utils.PasswordUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TokenServiceImpl tokenService;

    @Autowired
    UserMaperImpl usermapper;

    @GetMapping("/id")
    public ResponseEntity<?> getUserById(@RequestParam Long id) {
        ResponseEntity<?> response = null;

        Optional<UserModel> user = userService.getUserById(id);

        response = new ResponseEntity<UserDTO>(usermapper.toDto(user.get()), HttpStatus.OK);
        return response;
    }

    /**
     * Handles the login request for a user.
     *
     * @param user The user who will access the system.
     * @return ResponseEntity with a success message and generated token if login is
     *         successful, or an error message if login fails.
     */
    @PostMapping("/login/")
    public ResponseEntity<String> login(@RequestBody UserModel user) {

        UserModel userDb = userService.getUserByUsername(user.getUserName());
        // Check if the userName isn't in the database
        if (userDb == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EL USARIO NO EXISTE");
        }

        String hashWithSalt = user.getPassword() + userDb.getSalt();
        String stretchedHash = BCrypt.hashpw(hashWithSalt, userDb.getSalt());
        /**
         * If the passwords match, generate a token and return it in the response
         * If the passwords don't match, return an unauthorized response
         **/
        return stretchedHash.equals(userDb.getPassword()) ? ResponseEntity.ok().body(saveToken(userDb))
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("EL USUARIO NO PUEDE ENTRAR");

    }

    // Unfinished Method
    @PostMapping("/register/")
    public ResponseEntity<String> registerUser(@RequestBody UserModel user) {

        UserModel userDb = userService.getUserByUsername(user.getUserName());

        if (userDb != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EL USARIO YA EXISTE");
        }

        user.setSalt(PasswordUtil.generateSalt());
        userService.save(user);

        return ResponseEntity.ok().body("USUARIO REGISTRADO");

    }

    private String saveToken(UserModel user) {
        // Generar el token JWT
        String token = JwtUtil.generateToken(user.getUserName(), user.getEmail());

        Key signingKey = JwtUtil.SECRET_KEY;
        Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
        Date expirationDate = claims.getExpiration();

        // Guardar el token en la base de datos
        tokenService.saveToken(user.getIdUser(), token, expirationDate);

        return token;
    }
}
