package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository  extends JpaRepository<Offer, Integer> {
    Offer findByOfferId(Long id);
    Offer findByOfferName(String name);
    void deleteByOfferId(Long id);
}
