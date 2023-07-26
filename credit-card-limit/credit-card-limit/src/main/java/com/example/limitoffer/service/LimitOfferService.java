package com.example.limitoffer.service;

import com.example.limitoffer.entity.LimitOffer;

import java.util.List;
import java.util.Optional;

public interface LimitOfferService {
    List<LimitOffer> findAllLimitOffers();
    List<LimitOffer> findLimitOfferByAccountId(int accountId);
    LimitOffer saveLimitOffer(LimitOffer limitOffer);
    LimitOffer updateLimitOffer(LimitOffer limitOffer);

    LimitOffer findLimitOfferById(int id);

    void deleteLimitOffer(int id);
}
