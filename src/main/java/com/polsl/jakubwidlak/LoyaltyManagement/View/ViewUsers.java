package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.User;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryukki on 05.12.2017.
 */
public class ViewUsers {
    private JButton mainPageButton;
    private JButton manageOffersButton;
    private JTextField userOrLevelSearchTextField;
    private JButton searchButton;
    private JTable usersTable;
    private JButton sendOfferButton;
    private JComboBox offersComboBox;
    private JPanel mainPanel;
    private JCheckBox selectAllCheckBox;
    private AdminDataService adminDataService;

    public ViewUsers(AdminDataService adminDataService) {
        this.adminDataService = adminDataService;
        List<Offer> offerList = adminDataService.getOffers();
        String[] offersName = new String[offerList.size()];
        for (int i = 0; i< offerList.size();i++) {
            offersName[i] = offerList.get(i).getOfferName();
        }
        offersComboBox.setModel(new DefaultComboBoxModel(offersName));

        setupButtons();
        setupTable();

    }

    private void setupTable(){
        List<User> userList = adminDataService.getUsers();
        putUsers(userList);
        usersTable.setAutoCreateRowSorter(true);

        List<Offer> offerList = adminDataService.getOffers();
        String[] offersName = new String[offerList.size()];
        for (int i = 0; i< offerList.size();i++) {
            offersName[i] = offerList.get(i).getOfferName();
        }
        offersComboBox.setModel(new DefaultComboBoxModel(offersName));
    }

    private void putUsers(List<User> userList){
        String[] columnNames = {"Name", "Surname", "Level", "Mail", "Current Points", "Total Points", "Select"};
        int numberOfUsers = userList.size();
        Object[][] userData = new Object[numberOfUsers][7];
        for (int i =0; i<numberOfUsers; i++) {
            userData[i][0] = userList.get(i).getUserName();
            userData[i][1] = userList.get(i).getUserSurname();
            userData[i][2] = userList.get(i).getUserLevel();
            userData[i][3] = userList.get(i).getUserMail();
            userData[i][4] = userList.get(i).getUserCurrentPoints();
            userData[i][5] = userList.get(i).getUserTotalPoints();
            userData[i][6] = false;
        }
        DefaultTableModel tableModel = new DefaultTableModel(userData, columnNames){
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Integer.class;
                    case 5:
                        return Integer.class;
                    case 6:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        usersTable.setModel(tableModel);
    }

    private List<String> getUsers(){
        List<String> mails = new ArrayList<>();
        for(int i = 0; i < usersTable.getRowCount(); i++){
            if((Boolean)usersTable.getModel().getValueAt(i,6)){
                mails.add((String)usersTable.getModel().getValueAt(i,3));
            }
        }
        return mails;
    }

    private void setCheckboxes(boolean val){
        for(int i = 0; i < usersTable.getRowCount(); i++){
            usersTable.setValueAt(val, i, 6);
        }
    }

    private void setupButtons(){
        selectAllCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectAllCheckBox.isSelected()){
                    setCheckboxes(true);
                }else{
                    setCheckboxes(false);
                }
            }
        });

        sendOfferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOffer = offersComboBox.getSelectedItem().toString();
                List<String> mails = getUsers();
                List<User> userList = adminDataService.findUsersWithMails(mails);
                adminDataService.sendOfferToUsers(selectedOffer, userList);

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = userOrLevelSearchTextField.getText();
                if(searchTerm!=""){
                    List<User> userList;
                    userList = adminDataService.findUsersWithNameOrSurnameOrMailLike(searchTerm);
                    userList.addAll(adminDataService.findUsersWithLevel(searchTerm));
                    putUsers(userList);
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
        mainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = (JFrame)SwingUtilities.getWindowAncestor(mainPanel);
                setPanel(mainFrame, new MainMenu(adminDataService).getMainPanel());
                mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
