package com.example.account.controller;

import com.example.account.entity.Account;
import com.example.account.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping
    public List<Account> findAllAccounts(){
        return accountService.findAllAccounts();
    }
    @GetMapping("/{id}")
    public Account findAccountById(@PathVariable("id") int id){
        return accountService.findAccountById(id);
    }
    @PostMapping
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @PutMapping
    public Account updateAccount(@RequestBody Account account){
        return accountService.updateAccount(account);
    }
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") int id){
        accountService.deleteAccount(id);
    }
}
