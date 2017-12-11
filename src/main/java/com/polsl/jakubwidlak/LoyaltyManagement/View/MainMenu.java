package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.LoyaltyLevel;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.User;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 * Created by Ryukki on 01.12.2017.
 */
public class MainMenu {
    private JButton manageOffersButton;
    private JButton viewUsersButton;
    private JButton changeConvertionButton;
    private JTextField pointsCreatingAccountTextField;
    private JButton changePointsGivenButton;
    private JTextField pointsReferringTextField;
    private JTextField pointsRatingTextField;
    private JTextField pointsReviewingTextField;
    private JTextField currency2pointsTextField;
    private JFormattedTextField points2currencyTextField;
    private JTable levelsList;
    private JPanel mainPanel;
    private JScrollPane listPane;
    private JButton addNewLevelButton;
    private MainMenu mainMenu;
    private AdminDataService adminDataService;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainMenu(AdminDataService adminDataService) {
        this.adminDataService = adminDataService;
        mainMenu = this;
        //przyciski w tabeli: usuń, edytuj, wyślij ofertę - na samej górze: Add
        //usuwanie, edycja i dodwanie - zmiany leweli użytkowników
        setupTextFields();
        setupButtons();
        setupLevelList();
    }

    public void setupLevelList(){
        String[] columnNames = {"Name", "Lower Bound", "Upper Bound", "", "", ""};

        List<LoyaltyLevel> levelList = adminDataService.getLoyaltyLevels();
        int numberOfLevels = levelList.size();
        Object[][] levelData = new Object[numberOfLevels][6];
        for (int i =0; i<numberOfLevels; i++) {
            levelData[i][0] = levelList.get(i).getLevelName();
            levelData[i][1] = levelList.get(i).getLevelLowerBound();
            levelData[i][2] = levelList.get(i).getLevelUpperBound();
            levelData[i][3] = "Delete Level";
            levelData[i][4] = "Edit Level";
            levelData[i][5] = "Send Offer";
        }
        DefaultTableModel tableModel = new DefaultTableModel(levelData, columnNames);
        levelsList.setModel(tableModel);// = new JTable(tableModel);

        Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf( e.getActionCommand() );
                LoyaltyLevel loyaltyLevel = levelList.get(modelRow);
                adminDataService.removeLevel(loyaltyLevel.getLevelId());
                setupLevelList();
            }
        };
        ButtonColumn buttonColumnDelete = new ButtonColumn(levelsList, delete, 3);

        Action editOffer = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf( e.getActionCommand() );
                LoyaltyLevel loyaltyLevel = levelList.get(modelRow);
                JFrame bonusJFrame = adminDataService.createBonusFrame("Edit Level");
                bonusJFrame.setContentPane(new EditAddLevel(adminDataService, loyaltyLevel, mainMenu).getMainPanel());
                bonusJFrame.pack();
                bonusJFrame.revalidate();
            }
        };
        ButtonColumn buttonColumnEdit = new ButtonColumn(levelsList, editOffer, 4);

        Action sendOffer = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf( e.getActionCommand() );
                LoyaltyLevel loyaltyLevel = levelList.get(modelRow);

                List<Offer> offerList = adminDataService.getOffers();
                JFrame bonusJFrame = adminDataService.createBonusFrame("Send Offer");
                bonusJFrame.setContentPane(new ChooseOfferToSend(offerList, adminDataService, loyaltyLevel).getMainPanel());
                bonusJFrame.pack();
                bonusJFrame.revalidate();
            }
        };
        ButtonColumn buttonColumnSendOffer = new ButtonColumn(levelsList, sendOffer, 5);
    }

    private void setupTextFields(){
        currency2pointsTextField.setText(adminDataService.getSystemSettingValue("Currency2Points").toString());
        points2currencyTextField.setText(adminDataService.getSystemSettingValue("Points2Currency").toString());
        pointsCreatingAccountTextField.setText(adminDataService.getSystemSettingValue("CreatingAccountPoints").toString());
        pointsReferringTextField.setText(adminDataService.getSystemSettingValue("ReferralPoints").toString());
        pointsRatingTextField.setText(adminDataService.getSystemSettingValue("RatingPoints").toString());
        pointsReviewingTextField.setText(adminDataService.getSystemSettingValue("ReviewPoints").toString());
    }

    private void setupButtons(){
        addNewLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame bonusJFrame = adminDataService.createBonusFrame("Add Level");
                bonusJFrame.setContentPane(new EditAddLevel(adminDataService, mainMenu).getMainPanel());
                bonusJFrame.pack();
                bonusJFrame.revalidate();
            }
        });

        changeConvertionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Integer currency2points = Integer.parseInt(currency2pointsTextField.getText());
                    adminDataService.setSystemSetting("Currency2Points", currency2points);
                    Integer points2currency = Integer.parseInt(points2currencyTextField.getText());
                    adminDataService.setSystemSetting("Points2Currency", points2currency);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(new JFrame(), "Integer value required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        changePointsGivenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Integer creatingAccountPoints = Integer.parseInt(pointsCreatingAccountTextField.getText());
                    adminDataService.setSystemSetting("CreatingAccountPoints", creatingAccountPoints);
                    Integer referringUserPoints = Integer.parseInt(pointsReferringTextField.getText());
                    adminDataService.setSystemSetting("ReferralPoints", referringUserPoints);
                    Integer ratingPoints = Integer.parseInt(pointsRatingTextField.getText());
                    adminDataService.setSystemSetting("RatingPoints", ratingPoints);
                    Integer reviewPoints = Integer.parseInt(pointsReviewingTextField.getText());
                    adminDataService.setSystemSetting("ReviewPoints", reviewPoints);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(new JFrame(), "Integer value required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        manageOffersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = (JFrame)SwingUtilities.getWindowAncestor(mainPanel);
                setPanel(mainFrame, new ManageOffers(adminDataService).getMainPanel());
                mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = (JFrame)SwingUtilities.getWindowAncestor(mainPanel);
                setPanel(mainFrame, new ViewUsers(adminDataService).getMainPanel());
                mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }

    private void setPanel(JFrame jFrame, JPanel jPanel){
        jFrame.getContentPane().removeAll();
        jFrame.setContentPane(jPanel);
        jFrame.pack();
        jFrame.revalidate();
    }
}
