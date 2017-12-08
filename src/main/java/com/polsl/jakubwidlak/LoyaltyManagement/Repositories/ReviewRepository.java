package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByReviewUserId(Long id);
}