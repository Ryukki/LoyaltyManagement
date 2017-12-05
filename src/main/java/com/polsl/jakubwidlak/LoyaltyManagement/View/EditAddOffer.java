package com.polsl.jakubwidlak.LoyaltyManagement.View;

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

    //https://stackoverflow.com/questions/26794698/how-do-i-implement-jdatepicker
    //wybieranie daty jdatepicker

    public EditAddOffer() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
}
