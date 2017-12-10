package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ryukki on 01.12.2017.
 */
public class MainMenu {
    private JButton manageOffersButton;
    private JButton viewUsersButton;
    private JButton changeConvertionButton;
    private JTextField pointsCreatingAccountTextField;
    private JButton changePointsGivenButton;
    private JTextField pointsReferringTextField;
    private JTextField pointsRatingTextField;
    private JTextField pointsReviewingTextField;
    private JTextField currency2pointsTextField;
    private JFormattedTextField points2currencyTextField;
    private JTable levelsList;
    private JPanel mainPanel;

    @Autowired
    AdminDataService adminDataService;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainMenu() {
        Integer temp = adminDataService.getSystemSettingValue("Currency2Points");
        String string = temp.toString();
        currency2pointsTextField.setText(string);
        points2currencyTextField.setText(adminDataService.getSystemSettingValue("Points2Currency").toString());
        pointsCreatingAccountTextField.setText(adminDataService.getSystemSettingValue("CreatingAccountPoints").toString());
        pointsReferringTextField.setText(adminDataService.getSystemSettingValue("ReferralPoints").toString());
        pointsRatingTextField.setText(adminDataService.getSystemSettingValue("RatingPoints").toString());
        pointsReviewingTextField.setText(adminDataService.getSystemSettingValue("ReviewPoints").toString());



        //przyciski w tabeli: usuń, edytuj, wyślij ofertę - na samej górze: Add

        changeConvertionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        changePointsGivenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        manageOffersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
