package com.backend.beatflow.utils;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Duration EXPIRATION_DURATION = Duration.ofSeconds(100);

    /**
     * Generates a JWT token for a user.
     *
     * @param username The username associated with the token.
     * @param email    The email associated with the token.
     * @return The generated JWT token as a string.
     */
    public static String generateToken(String username, String email) {
        Instant now = Instant.now();
        Date expiration = Date.from(now.plus(EXPIRATION_DURATION));

        String token = Jwts.builder()
                .claim("username", username)
                .claim("email", email)
                .setIssuedAt(Date.from(now))
                .setExpiration(expiration)
                .signWith(SECRET_KEY)
                .compact();

        return token;
    }

    /**
     * Parses and validates a JWT token, returning the claims contained in the
     * token.
     *
     * @param token The JWT token to parse.
     * @return The claims extracted from the token.
     * @throws IllegalArgumentException If the token is invalid or expired.
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException("Token inválido o expirado");
        }
    }

}
