package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.domain.OfferUserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferUserConnectionRepository extends JpaRepository<OfferUserConnection, Integer> {
    public List<OfferUserConnection> findAllByUid(Long id);
}
