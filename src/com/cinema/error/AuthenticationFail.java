package com.cinema.error;

public class AuthenticationFail extends  Exception{
    public AuthenticationFail(String message){
        super(message);
    }
}
