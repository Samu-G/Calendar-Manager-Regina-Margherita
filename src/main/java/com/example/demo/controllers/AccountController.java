package com.example.demo.controllers;

import com.example.demo.models.user.Account;
import com.example.demo.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/get")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok().body(accountService.getAccounts());
    }

    @PostMapping("/account/save")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        return ResponseEntity.created(null).body(accountService.saveAccount(account));
    }
}



