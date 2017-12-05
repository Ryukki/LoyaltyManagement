package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;

@Entity
@Table(name="SYSTEMSETTING")
public class SystemSetting {
    @Id
    @Column(name="SS_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long rl_id;

    @Column(name="SS_NAME")
    private String ss_name;

    @Column(name="SS_VALUE")
    private Integer ss_value;

    public Long getRl_id() {
        return rl_id;
    }

    public void setRl_id(Long rl_id) {
        this.rl_id = rl_id;
    }

    public String getSs_name() {
        return ss_name;
    }

    public void setSs_name(String ss_name) {
        this.ss_name = ss_name;
    }

    public Integer getSs_value() {
        return ss_value;
    }

    public void setSs_value(Integer ss_value) {
        this.ss_value = ss_value;
    }

    /*
    * Currency2Points
    * Points2Currency
    * CreatingAccountPoints
    * RefferalPoints
    * RatingPoints
    * ReviewPoints
    * OfferTimeStored
    * */
}
