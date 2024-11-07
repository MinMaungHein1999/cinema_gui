package com.cinema.util;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtil {
    public static String hashPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

}
