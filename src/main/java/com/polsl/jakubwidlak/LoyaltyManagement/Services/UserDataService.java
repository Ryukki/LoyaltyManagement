package com.polsl.jakubwidlak.LoyaltyManagement.Services;

import com.polsl.jakubwidlak.LoyaltyManagement.Repositories.*;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    public UserDataService(UserRepository userRepository, RatingRepository ratingRepository, ReviewRepository reviewRepository, ReferralRepository referralRepository, OfferUserConnectionRepository offerUserConnectionRepository, OfferRepository offerRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.reviewRepository = reviewRepository;
        this.referralRepository = referralRepository;
        this.offerUserConnectionRepository = offerUserConnectionRepository;
        this.offerRepository = offerRepository;
        this.transactionRepository = transactionRepository;
    }

    public UserData getUserDataWithId(Long user_id){
        User user = this.userRepository.findByUserId(user_id);
        List<Rating> ratingList = this.ratingRepository.findAllByRatingUserId(user_id);
        List<Review> reviewList = this.reviewRepository.findAllByReviewUserId(user_id);
        List<Referral> referralList = this.referralRepository.findAllByReferralUserId(user_id);
        List<OfferUserConnection> offerUserConnectionList = this.offerUserConnectionRepository.findAllByConnectionUserId(user_id);
        List<Offer> offerList = new ArrayList<>();
        offerUserConnectionList.forEach(offerUserConnection -> {
            offerList.add(offerRepository.findByOfferId(offerUserConnection.getOfferUserConnectionId()));
        });
        List<Transaction> transactionList = this.transactionRepository.findAllByTransactionUserId(user_id);
        UserData userData = new UserData(user, ratingList, reviewList, referralList, offerList, transactionList);
        return userData;
    }

    public Long checkUserAndGetId(String user_mail, String password){
        User user = this.userRepository.findByUserMail(user_mail);
        if(user!=null){
            if(user.checkPassword(password)){
                return user.getUserId();
            }else{
                return new Long(-1);
            }
        }
        return new Long(0);
    }
}
