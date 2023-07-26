package com.example.limitoffer.service.impl;

import com.example.account.exception.ResourceNotFoundException;
import com.example.limitoffer.entity.LimitOffer;
import com.example.limitoffer.repository.LimitOfferRepository;
import com.example.limitoffer.service.LimitOfferService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LimitOfferServiceImpl implements LimitOfferService {
    public final LimitOfferRepository limitOfferRepository;

    public LimitOfferServiceImpl(LimitOfferRepository limitOfferRepository) {
        this.limitOfferRepository = limitOfferRepository;
    }

    @Override
    public List<LimitOffer> findAllLimitOffers() {
        return limitOfferRepository.findAll();
    }

    @Override
    public List<LimitOffer> findLimitOfferByAccountId(int accountId) {
        return Collections.singletonList(limitOfferRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Limit-offer", "Id", accountId)));
    }

    @Override
    public LimitOffer saveLimitOffer(LimitOffer limitOffer) {
        return limitOfferRepository.save(limitOffer);
    }

    @Override
    public LimitOffer updateLimitOffer(LimitOffer limitOffer) {

        // check if limit offer with given id exist or not
        LimitOffer existingLimitOffer = limitOfferRepository.findById(limitOffer.getOfferId()).
                orElseThrow(
                ()->new ResourceNotFoundException("limit-offer","id",limitOffer.getOfferId()));
        existingLimitOffer.setOfferStatus(limitOffer.getOfferStatus());
        existingLimitOffer.setAccountLimit(limitOffer.getAccountLimit());
        existingLimitOffer.setPerTransactionLimit(limitOffer.getPerTransactionLimit());
        existingLimitOffer.setOfferActivationTime(limitOffer.getOfferActivationTime());
        existingLimitOffer.setOfferExpiryTime(limitOffer.getOfferExpiryTime());
        limitOfferRepository.save(existingLimitOffer);
        return existingLimitOffer;
    }

    @Override
    public LimitOffer findLimitOfferById(int id) {
        return limitOfferRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("limit-offer","Id",id));
    }

    @Override
    public void deleteLimitOffer(int id) {
        limitOfferRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("limit-offer","id",id));
        limitOfferRepository.deleteById(id);
    }
}
