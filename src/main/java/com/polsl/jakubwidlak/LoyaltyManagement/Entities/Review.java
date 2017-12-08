package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="REVIEW")
public class Review {
    @Id
    @Column(name="RW_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long reviewId;

    @Column(name="RW_DATE")
    private Date reviewDate;

    @Column(name="RW_USER_ID")
    private Long reviewUserId;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }
}