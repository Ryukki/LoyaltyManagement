package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.OfferSendingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendingRuleRepository extends JpaRepository<OfferSendingRule, Integer> {
    void deleteBySendingRuleId(Long id);
    void deleteBySendingRuleOfferId(Long id);
}
