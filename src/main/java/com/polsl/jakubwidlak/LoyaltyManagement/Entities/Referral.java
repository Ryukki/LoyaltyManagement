package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="REFERRAL")
public class Referral {
    @Id
    @Column(name="RL_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long referralId;

    @Column(name="RL_USER_ID")
    private Long referralUserId;

    @Column(name="RL_NEWUSER_MAIL")
    private String referralNewUserMail;

    @Column(name="RL_DATE")
    private Date referralDate;

    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    public Long getReferralUserId() {
        return referralUserId;
    }

    public void setReferralUserId(Long referralUserId) {
        this.referralUserId = referralUserId;
    }

    public String getReferralNewUserMail() {
        return referralNewUserMail;
    }

    public void setReferralNewUserMail(String referralNewuserMail) {
        this.referralNewUserMail = referralNewuserMail;
    }

    public Date getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(Date referralDate) {
        this.referralDate = referralDate;
    }
}
