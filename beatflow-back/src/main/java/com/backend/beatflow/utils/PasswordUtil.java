package com.backend.beatflow.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String generateSalt() {
        // Genera un valor de salt aleatorio utilizando bcrypt
        String salt = BCrypt.gensalt();
        return salt;
    }
    public static String hashPassword(String password, String salt) {
        // Genera el hash de la contraseña utilizando bcrypt
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }

    public static boolean verifyPassword(String password, String salt, String hashedPassword) {
        String newHashedPassword = hashPassword(password, salt);
        return newHashedPassword.equals(hashedPassword);
    }
}
