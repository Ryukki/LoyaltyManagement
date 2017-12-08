package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;

@Entity
@Table(name="SYSTEMSETTING")
public class SystemSetting {
    @Id
    @Column(name="SS_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long systemSettingId;

    @Column(name="SS_NAME")
    private String systemSettingName;

    @Column(name="SS_VALUE")
    private Integer systemSettingValue;

    public Long getSystemSettingId() {
        return systemSettingId;
    }

    public void setSystemSettingId(Long systemSettingId) {
        this.systemSettingId = systemSettingId;
    }

    public String getSystemSettingName() {
        return systemSettingName;
    }

    public void setSystemSettingName(String systemSettingName) {
        this.systemSettingName = systemSettingName;
    }

    public Integer getSystemSettingValue() {
        return systemSettingValue;
    }

    public void setSystemSettingValue(Integer systemSettingValue) {
        this.systemSettingValue = systemSettingValue;
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
