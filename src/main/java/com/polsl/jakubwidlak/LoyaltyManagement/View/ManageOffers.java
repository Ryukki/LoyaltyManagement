package com.polsl.jakubwidlak.LoyaltyManagement.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ryukki on 01.12.2017.
 */
public class ManageOffers {
    private JButton mainPageButton;
    private JButton viewUsersButton;
    private JTable offerTable;
    private JButton addOfferButton;
    private JComboBox accountCreationOfferComboBox;
    private JComboBox refferalOfferComboBox;
    private JComboBox achevedLevelComboBox;
    private JComboBox achievelLevelOfferComboBox;
    private JTextField collectedPointsTextField;
    private JComboBox collectedPointsOfferComboBox;
    private JButton addAccountCreationRuleButton;
    private JButton addRefferalRuleButton;
    private JButton addLevelUpRuleButton;
    private JTable actionRulesTable;

    public ManageOffers() {
        //offerTable - przyciski w tabeli: usuń, edytuj - na samej górze: Add
        //actionRulesTable - istniejące zasady plus przyciski do usuwania
        mainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addOfferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addAccountCreationRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addRefferalRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addLevelUpRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addLevelUpRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
