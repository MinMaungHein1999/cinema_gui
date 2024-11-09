package com.cinema.service;

import com.cinema.converter.UserConverter;
import com.cinema.dao.UserDaoImpl;
import com.cinema.dto.UserDto;
import com.cinema.error.RegistrationException;
import com.cinema.model.User;
import com.cinema.util.EmailValidator;
import com.cinema.util.PasswordUtil;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrationService {
    private UserDaoImpl userDao;
    private List<String> errorMessages = new ArrayList<String>();
    private UserDto userDto;
    public UserRegistrationService(){
        this.userDao = new UserDaoImpl();
    }

    public void validateUserData() throws RegistrationException{
        this.checkEmail();
        this.checkConfirmPwd();
        this.checkUsername();
        if(errorMessages.isEmpty()){
            userCreationProcess();
        }else{
            throw new RegistrationException(errorMessages.toString());
        }
    }

    public void call(UserDto userDto) throws RegistrationException{
        this.errorMessages.clear();
        this.userDto = userDto;
        this.validateUserData();
    }

    public void userCreationProcess(){
        String hashPassword = PasswordUtil.hashPassword(this.userDto.getPassword());
        this.userDto.setHashPassword(hashPassword);
        User user = UserConverter.convertToModel(this.userDto);
        this.userDao.create(user);
    }

    public void checkEmail(){
        this.correctEmail();
        this.alreadyExistEmail();
    }

    private void alreadyExistEmail() {
        if(this.userDao.findUserByEmail(this.userDto.getEmail())!=null){
            this.errorMessages.add("Email already exist in the System!!!");
        }
    }

    private void correctEmail() {
        if(!EmailValidator.isValidEmail(this.userDto.getEmail())){
            this.errorMessages.add("Invalid Email address!!!");
        }
    }

    public void checkConfirmPwd(){
        if(!this.userDto.getPassword().equals(this.userDto.getConfirmPassword())){
            this.errorMessages.add("Incorrect Confirm Password!!!");
        }
    }

    public void checkUsername(){
        if(this.userDao.findUserByUsername(this.userDto.getUserName())!=null){
            this.errorMessages.add("UserName already exist!!!");
        }
    }



}
