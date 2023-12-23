package com.apiproject.ordersandnotificationsmanagement.accounts.repos;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class AccountsRepo {
    private final ArrayList<Account> accounts;

    AccountsRepo() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void deleteAccount(String username) {
        accounts.removeIf(account -> account.getAccountCredentials().getUsername().equals(username));
    }

    public Account getAccount(String username) {
        for (Account account : accounts) {
            if (account.getAccountCredentials().getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public boolean isAccountExists(String username) {
        for (Account account : accounts) {
            if (account.getAccountCredentials().getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
