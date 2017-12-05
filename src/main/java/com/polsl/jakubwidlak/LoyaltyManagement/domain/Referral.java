package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="REFERRAL")
public class Referral {
    @Id
    @Column(name="RL_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long rl_id;

    @Column(name="RL_USER_ID")
    private Long rl_user_id;

    @Column(name="RL_NEWUSER_MAIL")
    private String u_newuser_mail;

    @Column(name="RL_DATE")
    private Date RL_date;

    public Long getRl_id() {
        return rl_id;
    }

    public void setRl_id(Long rl_id) {
        this.rl_id = rl_id;
    }

    public Long getRl_user_id() {
        return rl_user_id;
    }

    public void setRl_user_id(Long rl_user_id) {
        this.rl_user_id = rl_user_id;
    }

    public String getU_newuser_mail() {
        return u_newuser_mail;
    }

    public void setU_newuser_mail(String u_newuser_mail) {
        this.u_newuser_mail = u_newuser_mail;
    }

    public Date getRL_date() {
        return RL_date;
    }

    public void setRL_date(Date RL_date) {
        this.RL_date = RL_date;
    }
}
