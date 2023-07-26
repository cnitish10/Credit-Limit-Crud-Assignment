package com.example.account.service.impl;

import com.example.account.entity.Account;
import com.example.account.exception.ResourceNotFoundException;
import com.example.account.repository.AccountRepository;
import com.example.account.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    public final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(int id) {
        return accountRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Account","Id",id));
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        Account existingAccount = accountRepository.findById(account.getAccountId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","id",account.getAccountId()));
        existingAccount.setAccountLimit(account.getAccountLimit());
        existingAccount.setLastAccountLimit(account.getLastAccountLimit());
        existingAccount.setAccountLimitUpdateTime(account.getAccountLimitUpdateTime());
        existingAccount.setPerTransactionLimit(account.getPerTransactionLimit());
        existingAccount.setLastPerTransactionLimit(account.getLastPerTransactionLimit());
        existingAccount.setCustomerId(account.getCustomerId());
        accountRepository.save(existingAccount);
        return existingAccount;
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Account","id",id));
        accountRepository.deleteById(id);
    }
}
