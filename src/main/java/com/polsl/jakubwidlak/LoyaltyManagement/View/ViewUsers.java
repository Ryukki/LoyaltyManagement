package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ryukki on 05.12.2017.
 */
public class ViewUsers {
    private JButton mainPageButton;
    private JButton manageOffersButton;
    private JTextField userOrLevelSearchTextField;
    private JButton searchButton;
    private JTable usersTable;
    private JButton sendOfferButton;
    private JComboBox offersComboBox;
    private JPanel mainPanel;
    private AdminDataService adminDataService;
    //https://stackoverflow.com/questions/28823670/how-to-sort-jtable-in-shortest-way

    public ViewUsers(AdminDataService adminDataService) {
        this.adminDataService = adminDataService;
        sendOfferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        manageOffersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
