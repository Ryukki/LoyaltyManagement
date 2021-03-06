package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;

@Entity
@Table(name="ACTIONENUM")
public class ActionEnum {
    @Id
    @Column(name="AE_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long actionEnumId;

    @Column(name="AE_NAME")
    private String actionEnumName;

    public Long getActionEnumId() {
        return actionEnumId;
    }

    public String getActionEnumName() {
        return actionEnumName;
    }

    /*ACCOUNTCREATION,
    REFFERAL,
    LEVELACHIEVED,
    POINTSCOLLECTED*/
}
