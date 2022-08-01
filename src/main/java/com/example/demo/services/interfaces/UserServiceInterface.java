package com.example.demo.services.interfaces;

import com.example.demo.exeption.studentAlreadyRegisterExeption;
import com.example.demo.exeption.studentNotFoundExeption;
import com.example.demo.models.user.Account;

import java.util.List;

public interface UserServiceInterface {
    List<Account> getUsers();
    Account saveUser(Account account) throws studentNotFoundExeption, studentAlreadyRegisterExeption;
    Account getUserByUsername(String username);
    void addRoleToUser(String username, String roleName);
}
