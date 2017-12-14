package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.ActionEnum;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.LoyaltyLevel;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.OfferSendingRule;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private JComboBox achievedLevelOfferComboBox;
    private JComboBox achievedLevelComboBox;
    private JTextField collectedPointsTextField;
    private JComboBox collectedPointsOfferComboBox;
    private JButton addAccountCreationRuleButton;
    private JButton addRefferalRuleButton;
    private JButton addLevelUpRuleButton;
    private JTable actionRulesTable;
    private JTextField offerExpirationTextField;
    private JButton saveExpirationButton;
    private JPanel mainPanel;
    private ManageOffers manageOffers;
    private List<Offer> offerList;
    private List<OfferSendingRule> offerSendingRuleList;

    private AdminDataService adminDataService;

    public ManageOffers(AdminDataService adminDataService) {
        this.adminDataService = adminDataService;
        manageOffers = this;
        offerList = adminDataService.getOffers();
        //offerTable - przyciski w tabeli: usuń, edytuj - na samej górze: Add
        //actionRulesTable - istniejące zasady plus przyciski do usuwania

        setupButtons();
        setupOfferTable();
        setupInputFields();
    }

    private void setupOfferTable(){
        String[] columnNames = {"Name", "Start Date", "End Date", "", ""};

        int numberOfOffers = offerList.size();
        Object[][] levelData = new Object[numberOfOffers][5];
        for (int i =0; i<numberOfOffers; i++) {
            levelData[i][0] = offerList.get(i).getOfferName();
            levelData[i][1] = offerList.get(i).getOfferStartDate();
            levelData[i][2] = offerList.get(i).getOfferEndDate();
            levelData[i][3] = "Delete";
            levelData[i][4] = "Edit";
        }
        DefaultTableModel tableModel = new DefaultTableModel(levelData, columnNames);
        offerTable.setModel(tableModel);
        offerTable.setAutoCreateRowSorter(true);

        Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf( e.getActionCommand() );
                Offer offer = offerList.get(modelRow);
                adminDataService.removeOffer(offer.getOfferId());
                setupOfferTable();
            }
        };
        ButtonColumn buttonColumnDelete = new ButtonColumn(offerTable, delete, 3);

        Action editOffer = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf( e.getActionCommand() );
                Offer offer = offerList.get(modelRow);
                JFrame bonusJFrame = adminDataService.createBonusFrame("Edit Offer");
                bonusJFrame.setContentPane(new EditAddOffer(adminDataService, offer, manageOffers).getMainPanel());
                bonusJFrame.pack();
                bonusJFrame.revalidate();
            }
        };
        ButtonColumn buttonColumnEdit = new ButtonColumn(offerTable, editOffer, 4);
    }

    private void setupRulesTable(){
        String[] columnNames = {"Offer Name", "Action", "Value", ""};
        offerSendingRuleList = adminDataService.getOfferSendingRules();
        int numberOfRules = offerSendingRuleList.size();
        Object[][] ruleData = new Object[numberOfRules][4];
        for (int i =0; i<numberOfRules; i++) {
            OfferSendingRule offerSendingRule = offerSendingRuleList.get(i);
            Offer offer = adminDataService.getOfferWithId(offerSendingRule.getSendingRuleOfferId());
            ruleData[i][0] = offer.getOfferName();
            ActionEnum actionEnum = adminDataService.getActionEnumWithId(offerSendingRule.getSendingRuleActionEnumId());
            ruleData[i][1] = actionEnum.getActionEnumName();
            if(actionEnum.getActionEnumId()==3){
                Long levelId = offerSendingRule.getSendingRuleLoyaltyLevelId();
                ruleData[i][2] = adminDataService.getLevelWithId(levelId).getLevelName();
            }else if(actionEnum.getActionEnumId()==4){
                ruleData[i][2] = offerSendingRule.getSendingRuleCurrentPoints();
            }
            ruleData[i][3] = "Delete";
        }
        DefaultTableModel tableModel = new DefaultTableModel(ruleData, columnNames);
        actionRulesTable.setModel(tableModel);
        actionRulesTable.setAutoCreateRowSorter(true);

        Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf( e.getActionCommand() );
                OfferSendingRule offerSendingRule = offerSendingRuleList.get(modelRow);
                adminDataService.removeSendingRule(offerSendingRule.getSendingRuleId());
                setupRulesTable();
            }
        };
        ButtonColumn buttonColumnDelete = new ButtonColumn(actionRulesTable, delete, 3);
    }

    public void refreshOffers(){
        offerList = adminDataService.getOffers();
    }

    private void setupInputFields(){
        offerExpirationTextField.setText(adminDataService.getSystemSettingValue("OfferTimeStored").toString());
        String[] offersName = new String[offerList.size()];
        for (int i = 0; i< offerList.size();i++) {
            offersName[i] = offerList.get(i).getOfferName();
        }
        accountCreationOfferComboBox.setModel(new DefaultComboBoxModel(offersName));
        refferalOfferComboBox.setModel(new DefaultComboBoxModel(offersName));
        achievedLevelOfferComboBox.setModel(new DefaultComboBoxModel(offersName));
        collectedPointsOfferComboBox.setModel(new DefaultComboBoxModel(offersName));

        List<LoyaltyLevel> levelList = adminDataService.getLoyaltyLevels();
        String[] levelsName = new String[levelList.size()];
        for (int i = 0; i< levelList.size();i++) {
            levelsName[i] = levelList.get(i).getLevelName();
        }
        achievedLevelComboBox.setModel(new DefaultComboBoxModel(levelsName));
    }

    private void setupButtons(){
        mainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = (JFrame)SwingUtilities.getWindowAncestor(mainPanel);
                setPanel(mainFrame, new MainMenu(adminDataService).getMainPanel());
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
        addOfferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame bonusJFrame = adminDataService.createBonusFrame("Edit Offer");
                bonusJFrame.setContentPane(new EditAddOffer(adminDataService, manageOffers).getMainPanel());
                bonusJFrame.pack();
                bonusJFrame.revalidate();
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
        saveExpirationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Integer offerExpiration = Integer.parseInt(offerExpirationTextField.getText());
                    if(offerExpiration>=0){
                        adminDataService.setSystemSetting("OfferTimeStored", offerExpiration);
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Value greater of equal zero required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }

                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(new JFrame(), "Integer value required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setPanel(JFrame jFrame, JPanel jPanel){
        jFrame.getContentPane().removeAll();
        jFrame.setContentPane(jPanel);
        jFrame.revalidate();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
