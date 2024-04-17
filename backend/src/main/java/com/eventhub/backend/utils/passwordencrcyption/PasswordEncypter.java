package com.eventhub.backend.utils.passwordencrcyption;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncypter {
    public static String encodePassword(String password) {
        // Generate a salt
        String salt = BCrypt.gensalt();

        // Hash the password with the salt
        String hashedPassword = BCrypt.hashpw(password, salt);

        return hashedPassword;
    }

    public static boolean validatePassword(String password, String hashedPassword) {
        // Check if the password matches the hashed password
        return BCrypt.checkpw(password, hashedPassword);
    }
}
