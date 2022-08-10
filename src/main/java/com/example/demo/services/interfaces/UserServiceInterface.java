package com.example.demo.services.interfaces;

import com.example.demo.exception.StudentAlreadyRegisterException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.UsernameAlreadyTakenException;
import com.example.demo.models.users.Account;

import java.util.List;

public interface UserServiceInterface {
    List<Account> getAccounts();
    Account saveStudentAccount(Account account) throws StudentNotFoundException, StudentAlreadyRegisterException, UsernameAlreadyTakenException;
    Account getAccountByUsername(String username);
    void addRoleToAccount(String username, String roleName);
}
