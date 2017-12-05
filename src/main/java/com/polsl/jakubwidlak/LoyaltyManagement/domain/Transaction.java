package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="SHOPTRANSACTION")
public class Transaction {
    @Id
    @Column(name="T_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long t_id;

    @Column(name="T_PRICE")
    private Double t_price;

    @Column(name="T_DATE")
    private Date t_date;

    @Column(name="T_POINTSEARNED")
    private Integer t_pointsEarned;

    @Column(name="T_POINTSSPENT")
    private Integer t_pointsSpent;

    @Column(name="T_USER_ID")
    private Long t_user_id;

    public Long getT_id() {
        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    public Double getT_price() {
        return t_price;
    }

    public void setT_price(Double t_price) {
        this.t_price = t_price;
    }

    public Date getT_date() {
        return t_date;
    }

    public void setT_date(Date t_date) {
        this.t_date = t_date;
    }

    public Integer getT_pointsEarned() {
        return t_pointsEarned;
    }

    public void setT_pointsEarned(Integer t_pointsEarned) {
        this.t_pointsEarned = t_pointsEarned;
    }

    public Integer getT_pointsSpent() {
        return t_pointsSpent;
    }

    public void setT_pointsSpent(Integer t_pointsSpent) {
        this.t_pointsSpent = t_pointsSpent;
    }

    public Long getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(Long t_user_id) {
        this.t_user_id = t_user_id;
    }
}