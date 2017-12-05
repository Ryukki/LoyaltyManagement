package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="REVIEW")
public class Review {
    @Id
    @Column(name="RW_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long rw_id;

    @Column(name="RW_DATE")
    private Date rw_date;

    @Column(name="RW_USER_ID")
    private Long rw_user_id;

    public Long getRw_id() {
        return rw_id;
    }

    public void setRw_id(Long rw_id) {
        this.rw_id = rw_id;
    }

    public Date getRw_date() {
        return rw_date;
    }

    public void setRw_date(Date rw_date) {
        this.rw_date = rw_date;
    }

    public Long getRw_user_id() {
        return rw_user_id;
    }

    public void setRw_user_id(Long rw_user_id) {
        this.rw_user_id = rw_user_id;
    }
}