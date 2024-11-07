package com.cinema.converter;

import com.cinema.dto.UserDto;
import com.cinema.model.User;

public class UserConverter {
    public static User convertToModel(UserDto userDto){
        if(userDto == null){
            return null;
        }

        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getHashPassword());
        user.setUserRole(userDto.getUserRole());

        return user;
    }
}
