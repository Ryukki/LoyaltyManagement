package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * Created by Ryukki on 10.12.2017.
 */
@Service
public class AdminPanel {
    private JFrame jFrame;

    @Autowired
    private AdminDataService adminDataService;

    /*public AdminPanel(JFrame frame) {
        jFrame = frame;
    }*/

    public void setupAdminGui(){

        JFrame jFrame = new JFrame("Admin Panel");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPanel(new MainMenu().getMainPanel());
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public void setPanel(JPanel jPanel){
        jFrame.getContentPane().removeAll();
        jFrame.setContentPane(jPanel);
        jFrame.pack();
        jFrame.revalidate();
    }
}
