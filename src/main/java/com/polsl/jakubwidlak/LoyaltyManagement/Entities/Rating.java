package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="RATING")
public class Rating {
    @Id
    @Column(name="RG_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long ratingId;

    @Column(name="RG_DATE")
    private Date ratingDate;

    @Column(name="RG_USER_ID")
    private Long ratingUserId;

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    public Long getRatingUserId() {
        return ratingUserId;
    }

    public void setRatingUserId(Long ratingUserId) {
        this.ratingUserId = ratingUserId;
    }
}