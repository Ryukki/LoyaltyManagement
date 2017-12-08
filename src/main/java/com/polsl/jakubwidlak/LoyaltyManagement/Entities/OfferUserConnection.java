package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;

/**
 * Created by Ryukki on 04.12.2017.
 */
@Entity
@Table(name="OFFERUSERCONNECTION")
public class OfferUserConnection {
    @Id
    @Column(name="C_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long offerUserConnectionId;

    @Column(name="U_ID")
    private Long connectionUserId;

    @Column(name="O_ID")
    private Long connectionOfferId;

    public Long getOfferUserConnectionId() {
        return offerUserConnectionId;
    }

    public void setOfferUserConnectionId(Long offerUserConnectionId) {
        this.offerUserConnectionId = offerUserConnectionId;
    }

    public Long getConnectionUserId() {
        return connectionUserId;
    }

    public void setConnectionUserId(Long connectionUserId) {
        this.connectionUserId = connectionUserId;
    }

    public Long getConnectionOfferId() {
        return connectionOfferId;
    }

    public void setConnectionOfferId(Long connectionOfferId) {
        this.connectionOfferId = connectionOfferId;
    }
}
