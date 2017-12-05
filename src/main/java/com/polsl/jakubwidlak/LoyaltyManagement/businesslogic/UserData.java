package com.polsl.jakubwidlak.LoyaltyManagement.businesslogic;

import com.polsl.jakubwidlak.LoyaltyManagement.domain.*;

import java.util.List;

/**
 * Created by Ryukki on 05.12.2017.
 */
public class UserData {
    private User user;
    private List<Rating> ratingList;
    private List<Review> reviewList;
    private List<Referral> referralList;
    private List<Offer> offerList;

    public UserData() {
    }

    public UserData(User user, List<Rating> ratingList, List<Review> reviewList, List<Referral> referralList, List<Offer> offerList) {
        this.user = user;
        this.ratingList = ratingList;
        this.reviewList = reviewList;
        this.referralList = referralList;
        this.offerList = offerList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Referral> getReferralList() {
        return referralList;
    }

    public void setReferralList(List<Referral> referralList) {
        this.referralList = referralList;
    }

    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }
}
