package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;

@Entity
@Table(name="LEVEL")
public class LoyaltyLevel {

    @Id
    @Column(name="L_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long l_id;

    @Column(name="L_NAME")
    private String l_name;

    @Column(name="L_LOWERBOUND")
    private Integer l_lowerBound;

    @Column(name="L_UPPERBOUND")
    private Integer l_upperBound;

    public Long getL_id() {
        return l_id;
    }

    public void setL_id(Long l_id) {
        this.l_id = l_id;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public Integer getL_lowerBound() {
        return l_lowerBound;
    }

    public void setL_lowerBound(Integer l_lowerBound) {
        this.l_lowerBound = l_lowerBound;
    }

    public Integer getL_upperBound() {
        return l_upperBound;
    }

    public void setL_upperBound(Integer l_upperBound) {
        this.l_upperBound = l_upperBound;
    }
}
