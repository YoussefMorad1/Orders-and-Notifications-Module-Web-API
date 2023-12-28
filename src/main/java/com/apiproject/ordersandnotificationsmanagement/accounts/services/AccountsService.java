package com.apiproject.ordersandnotificationsmanagement.accounts.services;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.accounts.repos.AccountsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsService {
    private final AccountsRepo accountsRepo;
    public boolean registerAccount(Account account) {
        if (account == null || accountsRepo.isAccountExists(account.getAccountCredentials().getUsername())
                || account.getBalance() < 0) {
            return false;
        }
        accountsRepo.addAccount(account);
        return true;
    }
    public Account checkForLogin(String username, String password) {
        Account account = accountsRepo.getAccount(username);
        if (account == null || !account.getAccountCredentials().getPassword().equals(password)) {
            return null;
        }
        return account;
    }
    public Account getAccount(String username) {
        return accountsRepo.getAccount(username);
    }
}
