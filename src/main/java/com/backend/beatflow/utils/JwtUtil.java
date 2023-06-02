package com.backend.beatflow.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class JwtUtil {
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Duration EXPIRATION_DURATION = Duration.ofSeconds(100);

    public static String generateToken(String userId, String username, String email) {
        Instant now = Instant.now();
        Date expiration = Date.from(now.plus(EXPIRATION_DURATION));

        String test = Jwts.builder()
                .claim("username", username)
                .claim("email", email)
                .setIssuedAt(Date.from(now))
                .setExpiration(expiration)
                .signWith(SECRET_KEY)
                .compact();

                System.out.println(test);
                return test;
    }

    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // Token inválido o expirado
            throw new IllegalArgumentException("Token inválido o expirado");
        }
    }

}
