package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="SHOPTRANSACTION")
public class Transaction {
    @Id
    @Column(name="T_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long transactionId;

    @Column(name="T_PRICE")
    private Double transactionPrice;

    @Column(name="T_DATE")
    private Date transactionDate;

    @Column(name="T_POINTSEARNED")
    private Integer transactionPointsEarned;

    @Column(name="T_POINTSSPENT")
    private Integer transactionPointsSpent;

    @Column(name="T_USER_ID")
    private Long transactionUserId;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(Double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionPointsEarned() {
        return transactionPointsEarned;
    }

    public void setTransactionPointsEarned(Integer transactionPointsEarned) {
        this.transactionPointsEarned = transactionPointsEarned;
    }

    public Integer getTransactionPointsSpent() {
        return transactionPointsSpent;
    }

    public void setTransactionPointsSpent(Integer transactionPointsSpent) {
        this.transactionPointsSpent = transactionPointsSpent;
    }

    public Long getTransactionUserId() {
        return transactionUserId;
    }

    public void setTransactionUserId(Long transactionUserId) {
        this.transactionUserId = transactionUserId;
    }
}