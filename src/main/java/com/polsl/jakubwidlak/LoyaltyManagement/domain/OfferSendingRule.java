package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;

@Entity
@Table(name="SENDINGRULE")
public class OfferSendingRule {
    @Id
    @Column(name="SR_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long sr_id;

    @Column(name="SR_OFFER_ID")
    private Long sr_offer_id;

    @Column(name="ACTIONENUM_ID")
    private Long actionEnum_id;

    @Column(name="SR_LOYALTYLEVEL_ID")
    private Long ua_loyaltyLevel;

    @Column(name="SR_CURRENTPOINTS")
    private Integer ua_currentPoints_id;

    public Long getSr_id() {
        return sr_id;
    }

    public void setSr_id(Long sr_id) {
        this.sr_id = sr_id;
    }

    public Long getSr_offer_id() {
        return sr_offer_id;
    }

    public void setSr_offer_id(Long sr_offer_id) {
        this.sr_offer_id = sr_offer_id;
    }

    public Long getActionEnum_id() {
        return actionEnum_id;
    }

    public void setActionEnum_id(Long actionEnum_id) {
        this.actionEnum_id = actionEnum_id;
    }

    public Long getUa_loyaltyLevel() {
        return ua_loyaltyLevel;
    }

    public void setUa_loyaltyLevel(Long ua_loyaltyLevel) {
        this.ua_loyaltyLevel = ua_loyaltyLevel;
    }

    public Integer getUa_currentPoints_id() {
        return ua_currentPoints_id;
    }

    public void setUa_currentPoints_id(Integer ua_currentPoints_id) {
        this.ua_currentPoints_id = ua_currentPoints_id;
    }
}
