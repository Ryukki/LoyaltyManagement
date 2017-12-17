package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created by Ryukki on 05.12.2017.
 */
public class EditAddOffer {
    private JLabel actionLabel;
    private JButton saveButton;
    private JTextArea offerTextArea;
    private JTextField offerNameTextField;
    private JPanel mainPanel;
    private JPanel startDatePanel;
    private JPanel endDatePanel;

    //https://stackoverflow.com/questions/26794698/how-do-i-implement-jdatepicker
    //wybieranie daty jdatepicker

    public EditAddOffer(AdminDataService adminDataService, Offer offer, ManageOffers manageOffers) {
        actionLabel.setText("Edit Offer");
        UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl startDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        startDatePanel.add(startDatePicker);
        
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
