package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;

@Entity
@Table(name="SENDINGRULE")
public class OfferSendingRule {
    @Id
    @Column(name="SR_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long sendingRuleId;

    @Column(name="SR_OFFER_ID")
    private Long sendingRuleOfferId;

    @Column(name="ACTIONENUM_ID")
    private Long sendingRuleActionEnumId;

    @Column(name="SR_LOYALTYLEVEL_ID")
    private Long sendingRuleLoyaltyLevelId;

    @Column(name="SR_CURRENTPOINTS")
    private Integer sendingRuleCurrentPoints;

    public Long getSendingRuleId() {
        return sendingRuleId;
    }

    public void setSendingRuleId(Long sendingRuleId) {
        this.sendingRuleId = sendingRuleId;
    }

    public Long getSendingRuleOfferId() {
        return sendingRuleOfferId;
    }

    public void setSendingRuleOfferId(Long sendingRuleOfferId) {
        this.sendingRuleOfferId = sendingRuleOfferId;
    }

    public Long getSendingRuleActionEnumId() {
        return sendingRuleActionEnumId;
    }

    public void setSendingRuleActionEnumId(Long sendingRuleActionEnumId) {
        this.sendingRuleActionEnumId = sendingRuleActionEnumId;
    }

    public Long getSendingRuleLoyaltyLevelId() {
        return sendingRuleLoyaltyLevelId;
    }

    public void setSendingRuleLoyaltyLevelId(Long sendingRuleLoyaltyLevelId) {
        this.sendingRuleLoyaltyLevelId = sendingRuleLoyaltyLevelId;
    }

    public Integer getSendingRuleCurrentPoints() {
        return sendingRuleCurrentPoints;
    }

    public void setSendingRuleCurrentPoints(Integer sendingRuleCurrentPoints) {
        this.sendingRuleCurrentPoints = sendingRuleCurrentPoints;
    }
}
