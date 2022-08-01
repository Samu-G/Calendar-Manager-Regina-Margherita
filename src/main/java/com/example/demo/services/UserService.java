package com.example.demo.services;

import com.example.demo.exeption.studentAlreadyRegisterExeption;
import com.example.demo.exeption.studentNotFoundExeption;
import com.example.demo.models.user.Account;
import com.example.demo.models.user.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.interfaces.RoleServiceInterface;
import com.example.demo.services.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserServiceInterface, RoleServiceInterface {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving a new role " + role.getName() + " to database");
        return roleRepository.save(role);
    }

    @Override
    public List<Account> getUsers() {
        log.info("Getting users from database");
        return userRepository.findAll();
    }

    @Override
    public Account saveUser(Account account) throws studentNotFoundExeption, studentAlreadyRegisterExeption {
        String name = account.getName();
        String surname = account.getSurname();

        if (studentRepository.findStudentByNameAndSurname(name, surname) == null) {
            log.info("Student " + name + surname + " isn't present in the student list");
            throw new studentNotFoundExeption();
        } else if (userRepository.findByNameAndSurname(name, surname) != null) {
            log.info("Student called " + name + surname + " is already registered");
            throw new studentAlreadyRegisterExeption();
        } else {
            log.info("Student called " + name + surname + " create his own account with id: " + account.getId());
            return userRepository.save(account);
        }
    }

    @Override
    public Account getUserByUsername(String username) {
        log.info("Getting user with username: " + username + " from database");
        return userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Account account = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        log.info("Setting role " + roleName + " to the user " + account.getId() + ", name: " + account.getName() + ", surname: " + account.getSurname());
        account.setRole(role);
    }
}



