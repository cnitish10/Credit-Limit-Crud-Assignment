package com.example.account.service;

import com.example.account.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAllAccounts();
    Account findAccountById(int id);
    Account saveAccount(Account account);
    Account updateAccount(Account account);

    void deleteAccount(int id);
}
