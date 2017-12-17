package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.LoyaltyLevel;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;

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
    private JPanel mainPanel;


    public EditAddLevel(AdminDataService adminDataService, LoyaltyLevel loyaltyLevel, MainMenu mainMenu) {
        actionLabel.setText("Edit Level");
        editedLevelNameTextField.setText(loyaltyLevel.getLevelName());
        editedLevelLowerBoundTextField.setText(loyaltyLevel.getLevelLowerBound().toString());
        editedLevelUpperBoundTextField.setText(loyaltyLevel.getLevelUpperBound().toString());
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String levelName = editedLevelNameTextField.getText();
                if(levelName!=""){
                    try{
                        Integer lowerBound = Integer.parseInt(editedLevelLowerBoundTextField.getText());
                        Integer upperBound = Integer.parseInt(editedLevelUpperBoundTextField.getText());
                        if(checkIfHigherThanZero(lowerBound, upperBound)){
                            if(upperBound>lowerBound){
                                adminDataService.editLevel(loyaltyLevel, levelName, lowerBound, upperBound);
                                mainMenu.setupLevelList();
                                JFrame mainFrame = (JFrame)mainPanel.getTopLevelAncestor();
                                mainFrame.dispose();
                            }else {
                                JOptionPane.showMessageDialog(new JFrame(), "Upper Bound neeeds to be greater than Lower Bound.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(new JFrame(), "Integer value required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(new JFrame(), "Level Name is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public EditAddLevel(AdminDataService adminDataService, MainMenu mainMenu) {
        //this.adminDataService = adminDataService;
        actionLabel.setText("Add Level");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String levelName = editedLevelNameTextField.getText();
                if(levelName!=""){
                    try{
                        Integer lowerBound = Integer.parseInt(editedLevelLowerBoundTextField.getText());
                        Integer upperBound = Integer.parseInt(editedLevelUpperBoundTextField.getText());
                        if(checkIfHigherThanZero(lowerBound, upperBound)){
                            if(upperBound>lowerBound){
                                adminDataService.addLevel(levelName, lowerBound, upperBound);
                                mainMenu.setupLevelList();
                                JFrame mainFrame = (JFrame)mainPanel.getTopLevelAncestor();
                                mainFrame.dispose();
                            }else {
                                JOptionPane.showMessageDialog(new JFrame(), "Upper Bound neeeds to be greater than Lower Bound.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(new JFrame(), "Integer value required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(new JFrame(), "Level Name is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean checkIfHigherThanZero(Integer lower, Integer upper){
        if(lower < 0 || upper < 0){
            JOptionPane.showMessageDialog(new JFrame(), "Value needs to be greater or equal zero", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
