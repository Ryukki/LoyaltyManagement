package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.LoyaltyLevel;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryukki on 10.12.2017.
 */
public class ChooseOfferToSend {
    private JButton sendOfferButton;
    private JComboBox offerComboBox;
    private JPanel mainPanel;
    private List<Offer> offerList;
    private AdminDataService adminDataService;
    private LoyaltyLevel loyaltyLevel;

    public ChooseOfferToSend(List<Offer> offers, AdminDataService adminDataService, LoyaltyLevel loyaltyLevel) {
        offerList = offers;
        this.adminDataService = adminDataService;
        this.loyaltyLevel = loyaltyLevel;
        sendOfferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOffer = offerComboBox.getSelectedItem().toString();
                adminDataService.sendOffer(selectedOffer, loyaltyLevel);
                JFrame mainFrame = (JFrame)mainPanel.getTopLevelAncestor();
                mainFrame.dispose();
            }
        });
        String[] offersName = new String[offerList.size()];
        for (int i = 0; i< offerList.size();i++) {
            offersName[i] = offerList.get(i).getOfferName();
        }
        offerComboBox.setModel(new DefaultComboBoxModel(offersName));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
