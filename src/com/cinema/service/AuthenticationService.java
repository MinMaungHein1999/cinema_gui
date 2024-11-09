package com.cinema.service;

import com.cinema.dao.UserDao;
import com.cinema.dao.UserDaoImpl;
import com.cinema.dto.LoginDto;
import com.cinema.error.AuthenticationFail;
import com.cinema.model.User;
import com.cinema.util.PasswordUtil;
import com.cinema.util.TokenUtil;

public class AuthenticationService {
    private static User currentUser;
    private UserDaoImpl userDao;

    public AuthenticationService(){
        this.userDao = new UserDaoImpl();
    }

    public void validateLoginToken() throws AuthenticationFail{
        if(currentUser == null){
            throw new AuthenticationFail("Need to Login!!!");
        }

        User user = userDao.findUserByUserNameANDToken(currentUser);
        if(user == null){
            throw new AuthenticationFail("Invalid Access Token !!!");
        }
    }

    public void call(LoginDto loginDto) throws AuthenticationFail{
        String hashPassword = PasswordUtil.hashPassword(loginDto.getPassword());
        User user = userDao.authenticate(loginDto.getUserName(), hashPassword);
        String token = TokenUtil.generateToken(user.getUsername());
        user.setAccessToken(token);
        userDao.updateToken(user);
        currentUser = user;
        if(user == null){
            throw new AuthenticationFail("Username or Password Incorrect!!!");
        }
    }
}
