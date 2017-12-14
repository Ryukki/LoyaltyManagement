package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(Long id);
    User findByUserMail(String user_mail);
    User findByUserReferralCode(String referralCode);
    List<User> findAllByUserLevel(String level);
    List<User> findAllByUserNameContainingIgnoreCase(String name);
    List<User> findAllByUserSurnameContainingIgnoreCase(String name);
    List<User> findAllByUserMailContainingIgnoreCase(String name);
    List<User> findByUserMailIn(List<String> mails);
}