package com.example.account.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "account")
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "customer_id",unique = true)
    private int customerId;
    @Column(name = "account_limit")
    private double accountLimit;
    @Column(name = "per_transaction_limit")
    private double perTransactionLimit;
    @Column(name = "last_account_limit")
    private double lastAccountLimit;
    @Column(name = "last_per_transaction_limit")
    private double lastPerTransactionLimit;
    @Column(name = "account_limit_update_time")
    private LocalDateTime accountLimitUpdateTime;
    @Column(name = "per_transaction_limit_update_time")
    private LocalDateTime perTransactionLimitUpdateTime;

    public Account(int accountId, int customerId, double accountLimit,
                   double perTransactionLimit, double lastAccountLimit,
                   double lastPerTransactionLimit, LocalDateTime accountLimitUpdateTime,
                   LocalDateTime perTransactionLimitUpdateTime) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountLimit = accountLimit;
        this.perTransactionLimit = perTransactionLimit;
        this.lastAccountLimit = lastAccountLimit;
        this.lastPerTransactionLimit = lastPerTransactionLimit;
        this.accountLimitUpdateTime = accountLimitUpdateTime;
        this.perTransactionLimitUpdateTime = perTransactionLimitUpdateTime;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }

    public double getPerTransactionLimit() {
        return perTransactionLimit;
    }

    public void setPerTransactionLimit(double perTransactionLimit) {
        this.perTransactionLimit = perTransactionLimit;
    }

    public double getLastAccountLimit() {
        return lastAccountLimit;
    }

    public void setLastAccountLimit(double lastAccountLimit) {
        this.lastAccountLimit = lastAccountLimit;
    }

    public double getLastPerTransactionLimit() {
        return lastPerTransactionLimit;
    }

    public void setLastPerTransactionLimit(double lastPerTransactionLimit) {
        this.lastPerTransactionLimit = lastPerTransactionLimit;
    }

    public LocalDateTime getAccountLimitUpdateTime() {
        return accountLimitUpdateTime;
    }

    public void setAccountLimitUpdateTime(LocalDateTime accountLimitUpdateTime) {
        this.accountLimitUpdateTime = accountLimitUpdateTime;
    }

    public LocalDateTime getPerTransactionLimitUpdateTime() {
        return perTransactionLimitUpdateTime;
    }

    public void setPerTransactionLimitUpdateTime(LocalDateTime perTransactionLimitUpdateTime) {
        this.perTransactionLimitUpdateTime = perTransactionLimitUpdateTime;
    }
}
