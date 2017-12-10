package com.polsl.jakubwidlak.LoyaltyManagement.Services;

import com.polsl.jakubwidlak.LoyaltyManagement.Repositories.*;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDataService {

    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private ReviewRepository reviewRepository;
    private ReferralRepository referralRepository;
    private OfferUserConnectionRepository offerUserConnectionRepository;
    private OfferRepository offerRepository;
    private TransactionRepository transactionRepository;
    private SystemSettingsRepository systemSettingsRepository;
    private LevelRepository levelRepository;
    private SendingRuleRepository sendingRuleRepository;
    private ActionEnumRepository actionEnumRepository;

    @Autowired
    public AdminDataService(UserRepository userRepository, RatingRepository ratingRepository, ReviewRepository reviewRepository, ReferralRepository referralRepository, OfferUserConnectionRepository offerUserConnectionRepository, OfferRepository offerRepository, TransactionRepository transactionRepository, SystemSettingsRepository systemSettingsRepository, LevelRepository levelRepository, SendingRuleRepository sendingRuleRepository, ActionEnumRepository actionEnumRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.reviewRepository = reviewRepository;
        this.referralRepository = referralRepository;
        this.offerUserConnectionRepository = offerUserConnectionRepository;
        this.offerRepository = offerRepository;
        this.transactionRepository = transactionRepository;
        this.systemSettingsRepository = systemSettingsRepository;
        this.levelRepository = levelRepository;
        this.sendingRuleRepository = sendingRuleRepository;
        this.actionEnumRepository = actionEnumRepository;
    }


    public Integer getSystemSettingValue(String systemSettingName){
        SystemSetting systemSetting = systemSettingsRepository.findBySystemSettingName(systemSettingName);
        return systemSetting.getSystemSettingValue();
    }

    public List<LoyaltyLevel> getLoyaltyLevels(){
        List<LoyaltyLevel> levelList = levelRepository.findAll();
        return levelList;
    }
}
