package com.polsl.jakubwidlak.LoyaltyManagement.Services;

import com.polsl.jakubwidlak.LoyaltyManagement.Repositories.*;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public List<Offer> getOffers(){
        return offerRepository.findAll();
    }
    public List<User> getUsers(){return userRepository.findAll();}

    public void sendOfferToLevel(String offerName, LoyaltyLevel loyaltyLevel){
        Offer offer = offerRepository.findByOfferName(offerName);
        List<User> userList= userRepository.findAllByUserLevel(loyaltyLevel.getLevelName());
        sendOfferToUsers(offerName, userList);
    }

    public void removeOffer(Long id){
        offerRepository.deleteByOfferId(id);
    }

    public Offer getOfferWithId(Long id){
        return offerRepository.findByOfferId(id);
    }

    public void sendOfferToUsers(String offerName, List<User> userList){
        Offer offer = offerRepository.findByOfferName(offerName);
        for (User user:userList) {
            OfferUserConnection offerUserConnection = new OfferUserConnection();
            offerUserConnection.setConnectionOfferId(offer.getOfferId());
            offerUserConnection.setConnectionUserId(user.getUserId());
            offerUserConnectionRepository.save(offerUserConnection);
        }
    }

    public ActionEnum getActionEnumWithId(Long id){
        return actionEnumRepository.findByActionEnumId(id);
    }

    public void removeSendingRule(Long id){
        sendingRuleRepository.deleteBySendingRuleId(id);
    }

    public Integer getSystemSettingValue(String systemSettingName){
        SystemSetting systemSetting = systemSettingsRepository.findBySystemSettingName(systemSettingName);
        return systemSetting.getSystemSettingValue();
    }

    public void setSystemSetting(String name, Integer value){
        SystemSetting systemSetting = systemSettingsRepository.findBySystemSettingName(name);
        systemSetting.setSystemSettingValue(value);
        systemSettingsRepository.save(systemSetting);
    }

    public List<LoyaltyLevel> getLoyaltyLevels(){
        List<LoyaltyLevel> levelList = levelRepository.findAllByOrderByLevelLowerBoundAsc();
        return levelList;
    }

    public LoyaltyLevel getLevelWithId(Long id){
        return levelRepository.findByLevelId(id);
    }

    public void removeLevel(Long id){
        levelRepository.deleteByLevelId(id);
    }

    public void addLevel(String levelName, Integer lowerBound, Integer upperBound){
        removeInteriorLevels(lowerBound, upperBound);
        LoyaltyLevel lowerLevel = levelRepository.findByLevelUpperBoundBetween(lowerBound, upperBound);
        LoyaltyLevel higherLevel = levelRepository.findByLevelLowerBoundBetween(lowerBound, upperBound);
        LoyaltyLevel newLevel = new LoyaltyLevel();
        newLevel.setLevelName(levelName);
        newLevel.setLevelLowerBound(lowerBound);
        newLevel.setLevelUpperBound(upperBound);
        if(lowerLevel!=null){
            lowerLevel.setLevelUpperBound(lowerBound-1);
            levelRepository.save(lowerLevel);
        }
        if(higherLevel!=null){
            higherLevel.setLevelLowerBound(upperBound+1);
            levelRepository.save(higherLevel);
        }

        levelRepository.save(newLevel);
        adjustUserLevels();
    }

    private void removeInteriorLevels(Integer lowerBound, Integer upperBound){
        List<LoyaltyLevel> levelsToRemove = levelRepository.findAllByLevelLowerBoundGreaterThanAndAndLevelUpperBoundLessThan(lowerBound, upperBound);
        for (LoyaltyLevel level:levelsToRemove
             ) {
            levelRepository.deleteByLevelId(level.getLevelId());
        }
    }

    /*private LoyaltyLevel findLowerLevel(Integer lowerBound){
        List<LoyaltyLevel> lowerBoundList = levelRepository.findAllByLevelLowerBoundLessThanOrderByLevelLowerBoundDesc(lowerBound);
        return lowerBoundList.get(0);
    }

    private LoyaltyLevel findUpperLevel(Integer upperBound){
        List<LoyaltyLevel> higherBoundList = levelRepository.findAllByLevelUpperBoundGreaterThanOrderByLevelUpperBoundAsc(upperBound);
        return higherBoundList.get(0);
    }*/

    public List<User> findUsersWithLevel(String level){
        return userRepository.findAllByUserLevel(level);
    }

    public List<User> findUsersWithMails(List<String> mails){return userRepository.findByUserMailIn(mails);}

    public List<User> findUsersWithNameOrSurnameOrMailLike(String name){
        List<User> userList = userRepository.findAllByUserNameContainingIgnoreCase(name);
        userList.addAll(userRepository.findAllByUserSurnameContainingIgnoreCase(name));
        userList.addAll(userRepository.findAllByUserMailContainingIgnoreCase(name));
        return userList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private void adjustUserLevels(){
        List<User> userList = userRepository.findAll();
        for(User user: userList){
            Integer userTotalPoints = user.getUserTotalPoints();
            LoyaltyLevel loyaltyLevel = levelRepository.findByLevelLowerBoundLessThanEqualAndLevelUpperBoundGreaterThanEqual(userTotalPoints, userTotalPoints);
            user.setUserLevel(loyaltyLevel.getLevelName());
            userRepository.save(user);
        }
    }

    public void editLevel(LoyaltyLevel loyaltyLevel, String levelName, Integer lowerBound, Integer upperBound){
        //Integer previousLowerBound = loyaltyLevel.getLevelLowerBound();
        //Integer previousUpperBound = loyaltyLevel.getLevelUpperBound();
        //LoyaltyLevel lowerLevel = findLowerLevel(previousLowerBound);
        //LoyaltyLevel higherLevel = findUpperLevel(previousUpperBound);

        levelRepository.deleteByLevelId(loyaltyLevel.getLevelId());
        addLevel(levelName, lowerBound, upperBound);

        /*Integer median = previousLowerBound+previousUpperBound/2;
        if(lowerLevel!=null){
            LoyaltyLevel upperLevel = findUpperLevel(lowerLevel.getLevelUpperBound());
            Integer upperLevelLowerBound = 0;
            if (upperLevel!=null){
                upperLevelLowerBound = upperLevel.getLevelLowerBound();
            }
            if(upperLevelLowerBound==0 || median<upperLevelLowerBound){
                lowerLevel.setLevelUpperBound(median);
            }else{
                lowerLevel.setLevelUpperBound(upperLevelLowerBound);
            }

            levelRepository.save(lowerLevel);
        }
        if(higherLevel!=null){
            LoyaltyLevel lowLevel = findLowerLevel(higherLevel.getLevelLowerBound());
            Integer lowLevelUpperBound = 0;
            if (lowLevel!=null){
                lowLevelUpperBound = lowLevel.getLevelLowerBound();
            }
            higherLevel.setLevelLowerBound(median+1);
            levelRepository.save(higherLevel);
        }*/
    }

    public List<OfferSendingRule> getOfferSendingRules(){
        return sendingRuleRepository.findAll();
    }

    public JFrame createBonusFrame(String name){
        JFrame bonusJFrame = new JFrame(name);
        bonusJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bonusJFrame.pack();
        bonusJFrame.revalidate();
        bonusJFrame.setResizable(false);
        bonusJFrame.setVisible(true);
        return bonusJFrame;
    }
}
