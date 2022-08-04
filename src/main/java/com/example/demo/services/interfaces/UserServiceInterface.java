package com.example.demo.services.interfaces;

import com.example.demo.exeption.StudentAlreadyRegisterException;
import com.example.demo.exeption.StudentNotFoundException;
import com.example.demo.exeption.UsernameAlreadyTakenException;
import com.example.demo.models.user.Account;

import java.util.List;

public interface UserServiceInterface {
    List<Account> getAccounts();
    Account saveStudentAccount(Account account) throws StudentNotFoundException, StudentAlreadyRegisterException, UsernameAlreadyTakenException;
    Account getAccountByUsername(String username);
    void addRoleToAccount(String username, String roleName);
}
