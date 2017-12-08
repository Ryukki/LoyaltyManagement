package com.polsl.jakubwidlak.LoyaltyManagement.Entities;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class User {
    @Id
    @Column(name="U_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;

    @Column(name="U_NAME")
    private String userName;

    @Column(name="U_SURNAME")
    private String userSurname;

    @Column(name="U_MAIL")
    private String userMail;

    @Column(name="U_TOTALPOINTS")
    private Long userTotalPoints;

    @Column(name="U_CURRENTPOINTS")
    private Integer userCurrentPoints;

    @Column(name ="U_LEVEL")
    private String userLevel;

    @Column(name ="U_REFERRALCODE")
    private String userReferralCode;//needs random creation

    @Column(name ="U_PASSWORD")
    private String userPassword;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Long getUserTotalPoints() {
        return userTotalPoints;
    }

    public void setUserTotalPoints(Long userTotalPoints) {
        this.userTotalPoints = userTotalPoints;
    }

    public Integer getUserCurrentPoints() {
        return userCurrentPoints;
    }

    public void setUserCurrentPoints(Integer userCurrentPoints) {
        this.userCurrentPoints = userCurrentPoints;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserReferralCode() {
        return userReferralCode;
    }

    public void setUserReferralCode(String userRefferalcode) {
        this.userReferralCode = userRefferalcode;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean checkPassword(String checkedPassword){
        if(checkedPassword.equals(this.userPassword))
            return true;
        return false;
    }

    public void changePoints(Integer points) {
        if (points>0)
            this.userTotalPoints= Long.valueOf(+points);
        this.userCurrentPoints+=points;
    }
}