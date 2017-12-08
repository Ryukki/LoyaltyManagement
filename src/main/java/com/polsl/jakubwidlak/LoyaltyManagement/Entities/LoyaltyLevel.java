package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;

@Entity
@Table(name="LEVEL")
public class LoyaltyLevel {

    @Id
    @Column(name="L_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long levelId;

    @Column(name="L_NAME")
    private String levelName;

    @Column(name="L_LOWERBOUND")
    private Integer levelLowerBound;

    @Column(name="L_UPPERBOUND")
    private Integer levelUpperBound;

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelLowerBound() {
        return levelLowerBound;
    }

    public void setLevelLowerBound(Integer levelLowerBound) {
        this.levelLowerBound = levelLowerBound;
    }

    public Integer getLevelUpperBound() {
        return levelUpperBound;
    }

    public void setLevelUpperBound(Integer levelUpperBound) {
        this.levelUpperBound = levelUpperBound;
    }
}
