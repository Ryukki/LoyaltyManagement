package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.LoyaltyLevel;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ChooseOfferToSend {
    private JButton sendOfferButton;
    private JComboBox offerComboBox;
    private JPanel mainPanel;

    public ChooseOfferToSend(List<Offer> offers, AdminDataService adminDataService, LoyaltyLevel loyaltyLevel) {
        sendOfferButton.addActionListener(e -> {
            String selectedOffer = offerComboBox.getSelectedItem().toString();
            adminDataService.sendOfferToLevel(selectedOffer, loyaltyLevel);
            JFrame mainFrame = (JFrame)mainPanel.getTopLevelAncestor();
            mainFrame.dispose();
        });
        String[] offersName = new String[offers.size()];
        for (int i = 0; i< offers.size();i++) {
            offersName[i] = offers.get(i).getOfferName();
        }
        offerComboBox.setModel(new DefaultComboBoxModel(offersName));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
