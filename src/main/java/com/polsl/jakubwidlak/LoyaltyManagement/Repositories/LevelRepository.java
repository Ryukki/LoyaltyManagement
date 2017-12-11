package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.LoyaltyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<LoyaltyLevel, Integer> {
    LoyaltyLevel findFirstByLevelLowerBoundGreaterThanEqual(Integer totalPoints);
    LoyaltyLevel findByLevelId(Long id);
    void deleteByLevelId(Long id);
    LoyaltyLevel findByLevelLowerBoundBetween(Integer lowerBound, Integer upperBound);
    LoyaltyLevel findByLevelUpperBoundBetween(Integer lowerBound, Integer upperBound);
    List<LoyaltyLevel> findAllByOrderByLevelLowerBoundAsc();
    List<LoyaltyLevel> findAllByLevelLowerBoundLessThanOrderByLevelLowerBoundDesc(Integer lowerBound);
    List<LoyaltyLevel> findAllByLevelUpperBoundGreaterThanOrderByLevelUpperBoundAsc(Integer upperBound);
    List<LoyaltyLevel> findAllByLevelLowerBoundGreaterThanAndAndLevelUpperBoundLessThan(Integer lowerBound, Integer upperBound);
}
