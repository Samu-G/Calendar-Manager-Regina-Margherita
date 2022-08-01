package com.example.demo.controllers;

import com.example.demo.models.student.Student;
import com.example.demo.models.user.Account;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping
    public void saveUser(@RequestBody Account account) {
        userService.saveUser(account);
    }

}
