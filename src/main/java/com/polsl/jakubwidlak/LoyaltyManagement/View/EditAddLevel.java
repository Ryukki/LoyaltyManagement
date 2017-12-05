package com.polsl.jakubwidlak.LoyaltyManagement.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ryukki on 05.12.2017.
 */
public class EditAddLevel {
    private JLabel actionLabel;
    private JTextField editedLevelNameTextField;
    private JTextField editedLevelLowerBoundTextField;
    private JTextField editedLevelUpperBoundTextField;
    private JButton saveButton;

    public EditAddLevel() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
}
