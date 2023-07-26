package com.example.limitoffer.controller;

import com.example.account.entity.Account;
import com.example.account.service.AccountService;
import com.example.limitoffer.entity.LimitOffer;
import com.example.limitoffer.entity.OfferStatus;
import com.example.limitoffer.service.LimitOfferService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/limit-offers")
public class LimitOfferController {
    public final LimitOfferService limitOfferService;
    public final AccountService accountService;

    public LimitOfferController(LimitOfferService limitOfferService, AccountService accountService) {
        this.limitOfferService = limitOfferService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<LimitOffer> findAllOffers(){
        return limitOfferService.findAllLimitOffers();
    }

    /**
     * Allows to create a limit offer for an account.
     * Offer should only be created for a greater limit than the current limit.
     * Initially when an offer is created, status of the offer will be PENDING.
     * @param limitOffer
     */
    @PostMapping
    public LimitOffer createLimitOffer(@RequestBody LimitOffer limitOffer) {

            //fetching the details of current account for which we want to create the offer
            Account currentAccount = getAccountDetails(limitOffer.getAccountId());


            if (limitOffer.getAccountLimit() <= currentAccount.getAccountLimit()) {
                throw new IllegalArgumentException("New account limit must be greater than the current limit.");
            } else if (limitOffer.getPerTransactionLimit() <= currentAccount.getPerTransactionLimit()) {
                throw new IllegalArgumentException("New per transaction limit must be greater than the current limit.");
            }

            if (limitOffer.getOfferActivationTime().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Offer activation time must be in the future.");
            }

            if (limitOffer.getOfferExpiryTime().isBefore(limitOffer.getOfferActivationTime())) {
                throw new IllegalArgumentException("Offer expiry time must be after the offer activation time.");
            }

            limitOffer.setOfferStatus(OfferStatus.PENDING);
            return limitOfferService.saveLimitOffer(limitOffer);
        }

    /**
     * Allows to fetch active offers for a given account id and active date.
     * An active offer, is the one which is PENDING,
     * and offerActivationTime is before activeDate and offerExpiryTime is after activeDate.
     * @param accountId
     * @param activeDate
     * @return List<activeOffers>
     */
    @GetMapping("/{accountId}")
    public List<LimitOffer> listActiveLimitOffers(@PathVariable int accountId,
                                                  @RequestParam(required = false)
                                                  LocalDateTime activeDate) {
            List<LimitOffer> activeOffers = new ArrayList<>();

            List<LimitOffer> allOffers = limitOfferService.findLimitOfferByAccountId(accountId);

            for(LimitOffer offer :allOffers){
                if (offer.getOfferStatus() == OfferStatus.PENDING &&
                        offer.getOfferActivationTime().isBefore(activeDate) &&
                        offer.getOfferExpiryTime().isAfter(activeDate)) {
                    activeOffers.add(offer);
                }
            }

            return activeOffers;
    }

    /**
     * Allows to update status of an active and pending offer to accepted and rejected.
     * Once an offer is accepted, we need to update limit values (current and last),
     * as well as limit update date in the account object.
     * @param limitOffer
     */
    @PutMapping
    public void updateLimitOfferStatus(@RequestBody LimitOffer limitOffer) {

            LimitOffer offer = limitOfferService.findLimitOfferById(limitOffer.getOfferId());

            // Check if the offer status is pending and if so, update it to the provided status from limitOffer.
            if (offer.getOfferStatus() == OfferStatus.PENDING) {
                offer.setOfferStatus(limitOffer.getOfferStatus());

                if (offer.getOfferStatus() == OfferStatus.ACCEPTED) {
                    updateAccountLimits(offer);
                }
                saveUpdatedLimitOffer(offer);
            } else {
                throw new IllegalArgumentException("Offer status can only be updated for PENDING offers.");
            }
    }
    @DeleteMapping("/{id}")
    public void deleteLimitOffer(@PathVariable("id") int id){
        limitOfferService.deleteLimitOffer(id);
    }

    private void updateAccountLimits(LimitOffer limitOffer) {
        //updating account fields as per requirements
            Account updatedAccount = accountService.findAccountById(limitOffer.getAccountId());
            updatedAccount.setLastAccountLimit(updatedAccount.getAccountLimit());
            updatedAccount.setAccountLimit(limitOffer.getAccountLimit());
            updatedAccount.setLastPerTransactionLimit(updatedAccount.getPerTransactionLimit());
            updatedAccount.setPerTransactionLimit(limitOffer.getPerTransactionLimit());
            updatedAccount.setAccountLimitUpdateTime(limitOffer.getOfferExpiryTime());
            accountService.updateAccount(updatedAccount);
    }

    private void saveUpdatedLimitOffer(LimitOffer limitOffer) {
          limitOfferService.saveLimitOffer(limitOffer);
    }


    private Account getAccountDetails(int accountId) {
            return accountService.findAccountById(accountId);
    }
}
