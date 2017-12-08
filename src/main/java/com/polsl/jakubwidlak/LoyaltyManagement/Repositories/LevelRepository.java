package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.LoyaltyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LoyaltyLevel, Integer> {
}
