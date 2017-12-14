package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository  extends JpaRepository<Offer, Integer> {
    Offer findByOfferId(Long id);
    Offer findByOfferName(String name);
    void deleteByOfferId(Long id);
}
