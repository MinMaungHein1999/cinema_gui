package com.cinema.dao;

import com.cinema.model.User;

public abstract class UserDao extends AbstractDao<User>{
    public abstract User findUserByEmail(String email);
    public abstract User findUserByUsername(String username);
}
