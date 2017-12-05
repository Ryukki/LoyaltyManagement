package com.polsl.jakubwidlak.LoyaltyManagement.domain;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class User {
    @Id
    @Column(name="U_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long u_id;

    @Column(name="U_NAME")
    private String u_name;

    @Column(name="U_SURNAME")
    private String u_surname;

    @Column(name="U_MAIL")
    private String u_mail;

    @Column(name="U_TOTALPOINTS")
    private Long u_totalPoints;

    @Column(name="U_CURRENTPOINTS")
    private Integer u_currentPoints;

    @Column(name ="U_LEVEL")
    private String u_level;

    @Column(name ="U_REFFERALCODE")
    private String u_refferalcode;//needs random creation

    @Column(name ="U_PASSWORD")
    private String u_password;

    /*@OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionList = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratingList = new ArrayList<>();

    public User(String name, String surname, String mail) {
        this.u_name = name;
        this.u_surname = surname;
        this.u_mail = mail;
        this.u_currentPoints = 0;
        this.u_totalPoints = Long.valueOf(0);
    }*/

    public Long getU_id() {
        return u_id;
    }

    public void setU_id(Long u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_surname() {
        return u_surname;
    }

    public void setU_surname(String u_surname) {
        this.u_surname = u_surname;
    }

    public String getU_mail() {
        return u_mail;
    }

    public void setU_mail(String u_mail) {
        this.u_mail = u_mail;
    }

    public Long getU_totalPoints() {
        return u_totalPoints;
    }

    public void setU_totalPoints(Long u_totalPoints) {
        this.u_totalPoints = u_totalPoints;
    }

    public Integer getU_currentPoints() {
        return u_currentPoints;
    }

    public void setU_currentPoints(Integer u_currentPoints) {
        this.u_currentPoints = u_currentPoints;
    }

    public String getU_level() {
        return u_level;
    }

    public void setU_level(String u_level) {
        this.u_level = u_level;
    }

    public String getU_refferalcode() {
        return u_refferalcode;
    }

    public void setU_refferalcode(String u_refferalcode) {
        this.u_refferalcode = u_refferalcode;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public void changePoints(Integer points) {
        if (points>0)
            this.u_totalPoints= Long.valueOf(+points);
        this.u_currentPoints+=points;
    }
}