package com.polsl.jakubwidlak.LoyaltyManagement.View;

import com.polsl.jakubwidlak.LoyaltyManagement.Services.AdminDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ryukki on 10.12.2017.
 */
@Controller
public class AdminPanel {
    private JFrame jFrame;

    @Autowired
    private AdminDataService adminDataService;

    /*public AdminPanel(JFrame frame) {
        jFrame = frame;
    }*/

    public void setupAdminGui(){
        jFrame = new JFrame("Admin Panel");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MainMenu mainMenu = new MainMenu(adminDataService);
        JPanel jPanel = mainMenu.getMainPanel();
        setPanel(jPanel);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    private void setPanel(JPanel jPanel){
        Container container = jFrame.getContentPane();
        if(container!=null){
            jFrame.getContentPane().removeAll();
        }
        jFrame.setContentPane(jPanel);
        jFrame.pack();
        jFrame.revalidate();
    }
}
