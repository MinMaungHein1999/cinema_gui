package com.cinema.util;

import java.util.Base64;
import java.util.Date;

public class TokenUtil {
    public static String generateToken(String username){
        long timestamp = new Date().getTime();
        String tokenDate = username + ":" + timestamp;
        return Base64.getEncoder().encodeToString(tokenDate.getBytes());
    }
}
