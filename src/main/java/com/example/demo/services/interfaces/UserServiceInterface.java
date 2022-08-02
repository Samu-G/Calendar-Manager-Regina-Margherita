package com.example.demo.services.interfaces;

import com.example.demo.exeption.studentAlreadyRegisterExeption;
import com.example.demo.exeption.studentNotFoundExeption;
import com.example.demo.models.user.Account;

import java.util.List;

public interface UserServiceInterface {
    List<Account> getAccounts();
    Account saveAccount(Account account) throws studentNotFoundExeption, studentAlreadyRegisterExeption;
    Account getAccountByUsername(String username);
    void addRoleToAccount(String username, String roleName);
}
