package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.sql.Date;


public class EditAddOffer {
    private JLabel actionLabel;
    private JButton saveButton;
    private JTextArea offerTextArea;
    private JTextField offerNameTextField;
    private JPanel mainPanel;
    private JPanel startDatePanel;
    private JPanel endDatePanel;
    private JXDatePicker startDatePicker;
    private JXDatePicker endDatePicker;

    public EditAddOffer(AdminDataService adminDataService, Offer offer, ManageOffers manageOffers) {
        actionLabel.setText("Edit Offer");
        offerNameTextField.setText(offer.getOfferName());
        startDatePicker.setDate(offer.getOfferStartDate());
        endDatePicker.setDate(offer.getOfferEndDate());
        offerTextArea.setText(offer.getOfferText());
        saveButton.addActionListener(e -> {
            String offerName = offerNameTextField.getText();
            if(!offerName.equals("")){
                Date startDate = (Date) startDatePicker.getDate();
                Date endDate = (Date) endDatePicker.getDate();
                if(endDate.compareTo(startDate)>0){
                    String offerText = offerTextArea.getText();
                    adminDataService.editOffer(offer, offerName, startDate, endDate, offerText);
                    manageOffers.refreshOffers();
                    JFrame mainFrame = (JFrame)mainPanel.getTopLevelAncestor();
                    mainFrame.dispose();
                }else {
                    JOptionPane.showMessageDialog(new JFrame(), "End Date needs to be after Start Date.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(new JFrame(), "Offer Name is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public EditAddOffer(AdminDataService adminDataService, ManageOffers manageOffers) {
        actionLabel.setText("Add Offer");

        saveButton.addActionListener(e -> {
            String offerName = offerNameTextField.getText();
            if(!offerName.equals("")){
                Date startDate = (Date) startDatePicker.getDate();
                Date endDate = (Date) endDatePicker.getDate();
                if(endDate.compareTo(startDate)>0){
                    String offerText = offerTextArea.getText();
                    adminDataService.addOffer(offerName, startDate, endDate, offerText);
                    manageOffers.refreshOffers();
                    JFrame mainFrame = (JFrame)mainPanel.getTopLevelAncestor();
                    mainFrame.dispose();
                }else {
                    JOptionPane.showMessageDialog(new JFrame(), "End Date needs to be after Start Date.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(new JFrame(), "Offer Name is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
