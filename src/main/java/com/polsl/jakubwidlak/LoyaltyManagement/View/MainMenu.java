package com.polsl.jakubwidlak.LoyaltyManagement.View;

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
    private JTextField pointsRefferingTextField;
    private JTextField pointsRatingTextField;
    private JTextField pointsReviewingTextField;
    private JTextField currency2pointsTextField;
    private JFormattedTextField points2currencyTextField;

    public MainMenu() {

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
