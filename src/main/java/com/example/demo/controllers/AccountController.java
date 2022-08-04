package com.example.demo.controllers;

import com.example.demo.exeption.StudentAlreadyRegisterException;
import com.example.demo.exeption.StudentNotFoundException;
import com.example.demo.exeption.UsernameAlreadyTakenException;
import com.example.demo.models.user.Account;
import com.example.demo.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/get")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok().body(accountService.getAccounts());
    }

    @PostMapping("/save")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        ResponseEntity<Account> response;
        Account newAccount;
        try {
            newAccount = accountService.saveStudentAccount(account);
            response = ResponseEntity.ok().body(newAccount);
        } catch (StudentNotFoundException ex) {
            response = ResponseEntity.status(513).build();
        } catch (StudentAlreadyRegisterException ey) {
            response = ResponseEntity.status(514).build();
        } catch (UsernameAlreadyTakenException ez) {
            response = ResponseEntity.status(515).build();
        }
        return response;
    }

    @PostMapping("/role/addRoleToAccount")
    public ResponseEntity<?> addRoleStudentToAccount(@RequestBody RoleToAccountForm form) {
        form.setRoleName("ROLE_STUDENT");
        accountService.addRoleToAccount(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}






