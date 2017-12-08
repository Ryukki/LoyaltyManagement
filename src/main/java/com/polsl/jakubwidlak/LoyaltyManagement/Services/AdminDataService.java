package com.polsl.jakubwidlak.LoyaltyManagement.Services;

import com.polsl.jakubwidlak.LoyaltyManagement.Repositories.*;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDataService {

    private UserRepository userRepository;
    private OfferRepository offerRepository;
    private LevelRepository levelRepository;
    private SendingRuleRepository sendingRuleRepository;

    @Autowired
    public AdminDataService(UserRepository userRepository, OfferRepository offerRepository, LevelRepository levelRepository, SendingRuleRepository sendingRuleRepository) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.levelRepository = levelRepository;
        this.sendingRuleRepository = sendingRuleRepository;
    }

    public List<LoyaltyLevel> getLoyaltyLevels(){
        List<LoyaltyLevel> levelList = levelRepository.findAll();
        return levelList;
    }
}
