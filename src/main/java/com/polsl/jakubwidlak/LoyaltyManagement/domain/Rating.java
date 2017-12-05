package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="RATING")
public class Rating {
    @Id
    @Column(name="RG_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long rg_id;

    @Column(name="RG_DATE")
    private Date rg_date;

    @Column(name="RG_USER_ID")
    private Long rg_user_id;

    public Long getRg_id() {
        return rg_id;
    }

    public void setRg_id(Long rg_id) {
        this.rg_id = rg_id;
    }

    public Date getRg_date() {
        return rg_date;
    }

    public void setRg_date(Date rg_date) {
        this.rg_date = rg_date;
    }

    public Long getRg_user_id() {
        return rg_user_id;
    }

    public void setRg_user_id(Long rg_user_id) {
        this.rg_user_id = rg_user_id;
    }
}