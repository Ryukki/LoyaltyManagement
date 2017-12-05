package com.polsl.jakubwidlak.LoyaltyManagement.businesslogic;

import com.polsl.jakubwidlak.LoyaltyManagement.Repositories.*;
import com.polsl.jakubwidlak.LoyaltyManagement.domain.*;
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

    @Autowired
    public UserDataService(UserRepository userRepository, RatingRepository ratingRepository, ReviewRepository reviewRepository, ReferralRepository referralRepository, OfferUserConnectionRepository offerUserConnectionRepository, OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.reviewRepository = reviewRepository;
        this.referralRepository = referralRepository;
        this.offerUserConnectionRepository = offerUserConnectionRepository;
        this.offerRepository = offerRepository;
    }

    public UserData getUserDataWithId(Long id){
        User user = this.userRepository.findById(id);
        List<Rating> ratingList = this.ratingRepository.findAllById(id);
        List<Review> reviewList = this.reviewRepository.findAllById(id);
        List<Referral> referralList = this.referralRepository.findAllById(id);
        List<OfferUserConnection> offerUserConnectionList = this.offerUserConnectionRepository.findAllByUid(id);
        List<Offer> offerList = new ArrayList<>();
        offerUserConnectionList.forEach(offerUserConnection -> {
            offerList.add(offerRepository.findById(offerUserConnection.getO_id()));
        });
        UserData userData = new UserData(user, ratingList, reviewList, referralList, offerList);
        return userData;
    }
}
