package com.polsl.jakubwidlak.LoyaltyManagement.Services;

import com.polsl.jakubwidlak.LoyaltyManagement.Repositories.*;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDataService {
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

    @Autowired
    public UserDataService(UserRepository userRepository, RatingRepository ratingRepository, ReviewRepository reviewRepository, ReferralRepository referralRepository, OfferUserConnectionRepository offerUserConnectionRepository, OfferRepository offerRepository, TransactionRepository transactionRepository, SystemSettingsRepository systemSettingsRepository, LevelRepository levelRepository, SendingRuleRepository sendingRuleRepository) {
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
    }

    public UserData getUserDataWithId(Long user_id){
        User user = this.userRepository.findByUserId(user_id);
        List<Rating> ratingList = this.ratingRepository.findAllByRatingUserId(user_id);
        List<Review> reviewList = this.reviewRepository.findAllByReviewUserId(user_id);
        List<Referral> referralList = this.referralRepository.findAllByReferralUserId(user_id);
        List<OfferUserConnection> offerUserConnectionList = this.offerUserConnectionRepository.findAllByConnectionUserId(user_id);
        List<Offer> offerList = new ArrayList<>();
        offerUserConnectionList.forEach(offerUserConnection -> {
            Offer offer = offerRepository.findByOfferId(offerUserConnection.getConnectionOfferId());
            Date utilDate = new Date();

            if(offer.getOfferEndDate().compareTo(new java.sql.Date(utilDate.getTime()))>=0){
                offerList.add(offer);
            }
        });
        List<Transaction> transactionList = this.transactionRepository.findAllByTransactionUserId(user_id);
        return new UserData(user, ratingList, reviewList, referralList, offerList, transactionList);
    }

    public Long checkUserAndGetId(String userMail, String password){
        User user = this.userRepository.findByUserMail(userMail);
        if(user!=null){
            if(user.checkPassword(password)){
                return user.getUserId();
            }else{
                return (long) -1;
            }
        }
        return 0L;
    }

    public boolean checkIfUserAlreadyExist(String userMail){
        User user = this.userRepository.findByUserMail(userMail);
        return user != null;
    }

    public Long addNewUser(String userMail, String password, String userName, String userSurname, String referralCode){
        User user = new User(userName, userSurname, userMail, password);
        SystemSetting systemSetting = systemSettingsRepository.findBySystemSettingName("CreatingAccountPoints");
        Integer pointsForCreatingAccount = systemSetting.getSystemSettingValue();
        user.setUserCurrentPoints(pointsForCreatingAccount);
        user.setUserTotalPoints(pointsForCreatingAccount);
        user = adjustUserLevel(user);
        user = userRepository.save(user);
        sendAccountCreationOffers(user.getUserId());
        User referringUser = userRepository.findByUserReferralCode(referralCode);
        if(referringUser==null){
            referringUser = userRepository.findByUserMail(referralCode);
        }
        if(referringUser!=null){
            referringUser.changePoints(systemSettingsRepository.findBySystemSettingName("ReferralPoints").getSystemSettingValue());
            userRepository.save(referringUser);
            sendReferralOffers(referringUser.getUserId());
        }

        return user.getUserId();
    }

    private void sendReferralOffers(Long userId){
        List<OfferSendingRule>offerSendingRuleList = sendingRuleRepository.findAll();
        for (OfferSendingRule offerSendingRule:offerSendingRuleList) {
            if(offerSendingRule.getSendingRuleActionEnumId()==2){
                sendOfferToUser(offerSendingRule.getSendingRuleOfferId(), userId);
            }
        }
    }

    private void sendAccountCreationOffers(Long userId){
        List<OfferSendingRule>offerSendingRuleList = sendingRuleRepository.findAll();
        for (OfferSendingRule offerSendingRule:offerSendingRuleList) {
            if(offerSendingRule.getSendingRuleActionEnumId()==1){
                sendOfferToUser(offerSendingRule.getSendingRuleOfferId(), userId);
            }
        }
    }

    private User adjustUserLevel(User user){
        Integer userTotalPoints = user.getUserTotalPoints();
        LoyaltyLevel loyaltyLevel = levelRepository.findByLevelLowerBoundLessThanEqualAndLevelUpperBoundGreaterThanEqual(userTotalPoints, userTotalPoints);
        //Long levelId = loyaltyLevel.getLevelId();
        //if(levelId>1){
       //    loyaltyLevel = levelRepository.findByLevelId(levelId-1);
        //}
        if(loyaltyLevel!=null){
            user.setUserLevel(loyaltyLevel.getLevelName());
        }else{
            user.setUserLevel("");
        }

        return user;
    }

    public void addReview(Long userId){
        Review review = new Review();
        review.setReviewUserId(userId);
        Date utilDate = new Date();
        review.setReviewDate(new java.sql.Date(utilDate.getTime()));
        reviewRepository.save(review);
        SystemSetting systemSetting = systemSettingsRepository.findBySystemSettingName("ReviewPoints");
        addPointsAndSendOffers(userId, systemSetting.getSystemSettingValue());
    }

    public void addRating(Long userId){
        Rating rating = new Rating();
        rating.setRatingUserId(userId);
        Date utilDate = new Date();
        rating.setRatingDate(new java.sql.Date(utilDate.getTime()));
        ratingRepository.save(rating);
        SystemSetting systemSetting = systemSettingsRepository.findBySystemSettingName("RatingPoints");
        addPointsAndSendOffers(userId, systemSetting.getSystemSettingValue());
    }

    public void addTransaction(Long userId, int pointsSpent, double price){
        Transaction transaction = new Transaction();
        User user = userRepository.findByUserId(userId);
        transaction.setTransactionUserId(userId);
        Date utilDate = new Date();
        transaction.setTransactionDate(new java.sql.Date(utilDate.getTime()));
        transaction.setTransactionPointsSpent(pointsSpent);
        transaction.setTransactionPrice(price);
        int currency2Points = systemSettingsRepository.findBySystemSettingName("Currency2Points").getSystemSettingValue();
        Integer points2Currency = systemSettingsRepository.findBySystemSettingName("Points2Currency").getSystemSettingValue();
        if(user.getUserCurrentPoints()<pointsSpent){
            pointsSpent=user.getUserCurrentPoints();
        }
        double moneyForPoints = (double)pointsSpent*(double)currency2Points/10;
        price -= moneyForPoints;
        int pointsEarned = (int)price*points2Currency/10;
        transaction.setTransactionPointsEarned(pointsEarned);
        transactionRepository.save(transaction);
        addPointsAndSendOffers(userId,pointsEarned-pointsSpent);
    }

    private void addPointsAndSendOffers(Long userId, Integer points){
        User user = userRepository.findByUserId(userId);
        String previousLevel = user.getUserLevel();
        Integer previousPoints = user.getUserCurrentPoints();
        user.changePoints(points);
        user = adjustUserLevel(user);
        userRepository.save(user);
        String currentLevel = user.getUserLevel();
        Integer currentPoints = user.getUserCurrentPoints();
        List<OfferSendingRule>offerSendingRuleList = sendingRuleRepository.findAll();
        for (OfferSendingRule offerSendingRule:offerSendingRuleList) {
            if(offerSendingRule.getSendingRuleActionEnumId()==3){
                if(!previousLevel.equals(currentLevel)){
                    Long targetLevel = offerSendingRule.getSendingRuleLoyaltyLevelId();
                    LoyaltyLevel loyaltyLevel = levelRepository.findByLevelId(targetLevel);
                    if(currentLevel.equals(loyaltyLevel.getLevelName())){
                        sendOfferToUser(offerSendingRule.getSendingRuleOfferId(), userId);
                    }
                }
            }else if (offerSendingRule.getSendingRuleActionEnumId()==4){
                Integer threshold = offerSendingRule.getSendingRuleCurrentPoints();
                if(previousPoints< threshold && currentPoints>= threshold){
                    sendOfferToUser(offerSendingRule.getSendingRuleOfferId(), userId);
                }
            }
        }
    }

    private void sendOfferToUser(Long offerId, Long userId){
        OfferUserConnection offerUserConnection = new OfferUserConnection();
        offerUserConnection.setConnectionOfferId(offerId);
        offerUserConnection.setConnectionUserId(userId);
        offerUserConnectionRepository.save(offerUserConnection);
    }
}
