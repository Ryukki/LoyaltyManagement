package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;

@Entity
@Table(name="ACTIONENUM")
public class ActionEnum {
    @Id
    @Column(name="AE_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long ae_id;

    @Column(name="AE_NAME")
    private String ae_name;

    public Long getAe_id() {
        return ae_id;
    }

    public void setAe_id(Long ae_id) {
        this.ae_id = ae_id;
    }

    public String getAe_name() {
        return ae_name;
    }

    public void setAe_name(String ae_name) {
        this.ae_name = ae_name;
    }

    /*ACCOUNTCREATION,
    REFFERAL,
    LEVELACHIEVED,
    POINTSCOLLECTED*/
}
