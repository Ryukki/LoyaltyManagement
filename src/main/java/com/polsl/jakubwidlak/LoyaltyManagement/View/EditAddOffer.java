package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ryukki on 05.12.2017.
 */
public class EditAddOffer {
    private JLabel actionLabel;
    private JButton saveButton;
    private JTextArea offerTextArea;
    private JTextField offerNameTextField;
    private JPanel mainPanel;

    //https://stackoverflow.com/questions/26794698/how-do-i-implement-jdatepicker
    //wybieranie daty jdatepicker

    public EditAddOffer(AdminDataService adminDataService, Offer offer, ManageOffers manageOffers) {
        actionLabel.setText("Edit Offer");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                manageOffers.refreshOffers();
            }
        });
    }

    public EditAddOffer(AdminDataService adminDataService, ManageOffers manageOffers) {
        actionLabel.setText("Add Offer");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                manageOffers.refreshOffers();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
