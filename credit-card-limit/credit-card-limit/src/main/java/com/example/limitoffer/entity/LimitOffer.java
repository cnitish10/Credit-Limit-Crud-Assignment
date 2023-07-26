package com.example.limitoffer.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "limit_offer")
@NoArgsConstructor
public class LimitOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private int offerId;

    @Column(name = "account_id",nullable = false)
    private int accountId;
    @Column(name = "offer_status")
    private OfferStatus offerStatus;
    @Column(name = "per_transaction_limit")
    private double perTransactionLimit;
    @Column(name = "account_limit")
    private double accountLimit;
    @Column(name = "offer_activation_time")
    private LocalDateTime offerActivationTime;
    @Column(name = "offer_expiry_time")
    private LocalDateTime offerExpiryTime;

    public LimitOffer(int offerId, int accountId, OfferStatus offerStatus, double perTransactionLimit, double accountLimit, LocalDateTime offerActivationTime, LocalDateTime offerExpiryTime) {
        this.offerId = offerId;
        this.accountId = accountId;
        this.offerStatus = offerStatus;
        this.perTransactionLimit = perTransactionLimit;
        this.accountLimit = accountLimit;
        this.offerActivationTime = offerActivationTime;
        this.offerExpiryTime = offerExpiryTime;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public double getPerTransactionLimit() {
        return perTransactionLimit;
    }

    public void setPerTransactionLimit(double perTransactionLimit) {
        this.perTransactionLimit = perTransactionLimit;
    }

    public double getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }

    public LocalDateTime getOfferActivationTime() {
        return offerActivationTime;
    }

    public void setOfferActivationTime(LocalDateTime offerActivationTime) {
        this.offerActivationTime = offerActivationTime;
    }

    public LocalDateTime getOfferExpiryTime() {
        return offerExpiryTime;
    }

    public void setOfferExpiryTime(LocalDateTime offerExpiryTime) {
        this.offerExpiryTime = offerExpiryTime;
    }
}
