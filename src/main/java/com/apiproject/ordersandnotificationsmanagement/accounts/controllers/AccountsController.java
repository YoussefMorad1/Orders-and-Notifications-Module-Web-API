package com.apiproject.ordersandnotificationsmanagement.accounts.controllers;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.accounts.models.AccountCredentials;
import com.apiproject.ordersandnotificationsmanagement.accounts.services.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountsController {
    private AccountsService accountsService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody Account account) {
        if (accountsService.registerAccount(account)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid account input (username already exists or balance is negative)");
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> checkForLogin(@RequestBody AccountCredentials accountCredentials) {
        Account account = accountsService.checkForLogin(
                accountCredentials.getUsername(),
                accountCredentials.getPassword()
        );
        if (account == null) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        } else {
            return ResponseEntity.ok(account);
        }
    }
}
