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

    @Autowired
    public UserDataService(UserRepository userRepository, RatingRepository ratingRepository, ReviewRepository reviewRepository, ReferralRepository referralRepository, OfferUserConnectionRepository offerUserConnectionRepository, OfferRepository offerRepository, TransactionRepository transactionRepository, SystemSettingsRepository systemSettingsRepository, LevelRepository levelRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.reviewRepository = reviewRepository;
        this.referralRepository = referralRepository;
        this.offerUserConnectionRepository = offerUserConnectionRepository;
        this.offerRepository = offerRepository;
        this.transactionRepository = transactionRepository;
        this.systemSettingsRepository = systemSettingsRepository;
        this.levelRepository = levelRepository;
    }

    public UserData getUserDataWithId(Long user_id){
        User user = this.userRepository.findByUserId(user_id);
        List<Rating> ratingList = this.ratingRepository.findAllByRatingUserId(user_id);
        List<Review> reviewList = this.reviewRepository.findAllByReviewUserId(user_id);
        List<Referral> referralList = this.referralRepository.findAllByReferralUserId(user_id);
        List<OfferUserConnection> offerUserConnectionList = this.offerUserConnectionRepository.findAllByConnectionUserId(user_id);
        List<Offer> offerList = new ArrayList<>();
        offerUserConnectionList.forEach(offerUserConnection -> {
            offerList.add(offerRepository.findByOfferId(offerUserConnection.getConnectionOfferId()));
        });
        List<Transaction> transactionList = this.transactionRepository.findAllByTransactionUserId(user_id);
        UserData userData = new UserData(user, ratingList, reviewList, referralList, offerList, transactionList);
        return userData;
    }

    public Long checkUserAndGetId(String userMail, String password){
        User user = this.userRepository.findByUserMail(userMail);
        if(user!=null){
            if(user.checkPassword(password)){
                return user.getUserId();
            }else{
                return new Long(-1);
            }
        }
        return new Long(0);
    }

    public boolean checkIfUserAlreadyExist(String userMail){
        User user = this.userRepository.findByUserMail(userMail);
        if(user!=null){
            return true;
        }
        return false;
    }

    public Long addNewUser(String userMail, String password, String userName, String userSurname, String referralCode){
        User user = new User(userName, userSurname, userMail, password);
        List<SystemSetting> systemSettingList = systemSettingsRepository.findAll();
        SystemSetting systemSetting = systemSettingsRepository.findBySystemSettingName("CreatingAccountPoints");
        Integer pointsForCreatingAccount = systemSetting.getSystemSettingValue();
        user.setUserCurrentPoints(pointsForCreatingAccount);
        user.setUserTotalPoints(pointsForCreatingAccount);
        user = adjustUserLevel(user);
        user = userRepository.save(user);
        User referringUser = userRepository.findByUserReferralCode(referralCode);
        if(referringUser!=null){
            referringUser.changePoints(systemSettingsRepository.getOne(4).getSystemSettingValue());
            userRepository.save(referringUser);
        }

        return user.getUserId();
    }

    private User adjustUserLevel(User user){
        Integer userTotalPoints = user.getUserTotalPoints();
        LoyaltyLevel loyaltyLevel = levelRepository.findFirstByLevelLowerBoundGreaterThanEqual(userTotalPoints);
        Long levelId = loyaltyLevel.getLevelId();
        if(levelId>1){
            loyaltyLevel = levelRepository.findByLevelId(levelId-1);
        }
        user.setUserLevel(loyaltyLevel.getLevelName());
        return user;
    }

    public void addReview(Long userId){
        Review review = new Review();
        review.setReviewUserId(userId);
        Date utilDate = new Date();
        review.setReviewDate(new java.sql.Date(utilDate.getTime()));
        reviewRepository.save(review);
    }

    public void addRating(Long userId){
        Rating rating = new Rating();
        rating.setRatingUserId(userId);
        Date utilDate = new Date();
        rating.setRatingDate(new java.sql.Date(utilDate.getTime()));
        ratingRepository.save(rating);
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
        user.changePoints(pointsEarned-pointsSpent);
        user = adjustUserLevel(user);
        userRepository.save(user);
    }
}
