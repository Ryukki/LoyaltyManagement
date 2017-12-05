package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="OFFER")
public class Offer {

    @Id
    @Column(name="O_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long o_id;

    @Column(name="O_STARTDATE")
    private Date o_startDate;

    @Column(name="O_ENDDATE")
    private Date o_endDate;

    @Column(name="O_NAME")
    private String o_name;

    @Column(name="O_TEXT")
    private  String o_text;

    public Long getO_id() {
        return o_id;
    }

    public void setO_id(Long o_id) {
        this.o_id = o_id;
    }

    public Date getO_startDate() {
        return o_startDate;
    }

    public void setO_startDate(Date o_startDate) {
        this.o_startDate = o_startDate;
    }

    public Date getO_endDate() {
        return o_endDate;
    }

    public void setO_endDate(Date o_endDate) {
        this.o_endDate = o_endDate;
    }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public String getO_text() {
        return o_text;
    }

    public void setO_text(String o_text) {
        this.o_text = o_text;
    }
}
