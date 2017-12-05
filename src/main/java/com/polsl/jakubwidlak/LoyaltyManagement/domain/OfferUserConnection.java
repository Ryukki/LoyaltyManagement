package com.polsl.jakubwidlak.LoyaltyManagement.domain;

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
    private Long C_id;

    @Column(name="U_ID")
    private Long u_id;

    @Column(name="O_ID")
    private Long o_id;

    public Long getU_id() {
        return u_id;
    }

    public void setU_id(Long u_id) {
        this.u_id = u_id;
    }

    public Long getO_id() {
        return o_id;
    }

    public void setO_id(Long o_id) {
        this.o_id = o_id;
    }
}
