package com.example.demo.services;

import com.example.demo.exeption.StudentAlreadyRegisterException;
import com.example.demo.exeption.StudentNotFoundException;
import com.example.demo.exeption.UsernameAlreadyTakenException;
import com.example.demo.models.user.Account;
import com.example.demo.models.user.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.AccountRepository;
import com.example.demo.services.interfaces.RoleServiceInterface;
import com.example.demo.services.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService implements UserServiceInterface, RoleServiceInterface {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving a new role " + role.getName() + " to database");
        return roleRepository.save(role);
    }

    @Override
    public List<Account> getAccounts() {
        log.info("Getting users from database");
        return accountRepository.findAll();
    }

    @Override
    public Account saveStudentAccount(Account account) throws StudentNotFoundException, StudentAlreadyRegisterException, UsernameAlreadyTakenException {
        String name = account.getName().toUpperCase();
        account.setName(name);
        String surname = account.getSurname().toUpperCase();
        account.setSurname(surname);
        String username = account.getUsername().toUpperCase();
        account.setRole(roleRepository.findByName("ROLE_STUDENT"));

        if (studentRepository.findStudentByNameAndSurname(name, surname) == null) {
            log.info("Student " + name + " " + surname + " isn't present in the student list");
            throw new StudentNotFoundException();
        } else if (accountRepository.findAccountByNameAndSurname(name, surname) != null) {
            log.info("Student called " + name + " " + surname + " is already registered");
            throw new StudentAlreadyRegisterException();
        } else if(accountRepository.findByUsername(username) != null) {
            log.info("Username " + username + " already taken. Student called " + name + " " + surname + " is trying to register");
            throw new UsernameAlreadyTakenException();
        } else {
            Account added = accountRepository.save(account);
            log.info(account.getRole() + " called " + name  + " " + surname + " create his own account with id: " + added.getId());
            return added;
        }
    }

    @Override
    public Account getAccountByUsername(String username) {
        log.info("Getting user with username: " + username + " from database");
        return accountRepository.findByUsername(username);
    }

    @Override
    public void addRoleToAccount(String username, String roleName) {
        Account account = accountRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        log.info("Setting role " + roleName + " to the user " + account.getId() + ", name: " + account.getName() + ", surname: " + account.getSurname());
        account.setRole(role);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}



