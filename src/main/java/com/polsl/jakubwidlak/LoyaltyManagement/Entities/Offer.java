package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="OFFER")
public class Offer {

    @Id
    @Column(name="O_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long offerId;

    @Column(name="O_STARTDATE")
    private Date offerStartDate;

    @Column(name="O_ENDDATE")
    private Date offerEndDate;

    @Column(name="O_NAME")
    private String offerName;

    @Column(name="O_TEXT")
    private  String offerText;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Date getOfferStartDate() {
        return offerStartDate;
    }

    public void setOfferStartDate(Date offerStartDate) {
        this.offerStartDate = offerStartDate;
    }

    public Date getOfferEndDate() {
        return offerEndDate;
    }

    public void setOfferEndDate(Date offerEndDate) {
        this.offerEndDate = offerEndDate;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferText() {
        return offerText;
    }

    public void setOfferText(String offerText) {
        this.offerText = offerText;
    }
}
