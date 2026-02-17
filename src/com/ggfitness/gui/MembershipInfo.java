package com.ggfitness.gui;


import com.ggfitness.database.UserDBO;
import net.miginfocom.swing.MigLayout;
import com.ggfitness.model.User;

import javax.swing.*;
import java.awt.*;


public class MembershipInfo
{
    private User user;
    private MainWindow mainWindow;

    public MembershipInfo(User user, MainWindow mainWindow)
    {
        this.user = user;
        this.mainWindow = mainWindow;
    }

    public JPanel membershipWindow()
    {
        JPanel mainPanel = new JPanel(new MigLayout());
        JPanel membershipPanel = new JPanel(new MigLayout());

        JButton homeButton = new JButton("Home");


        buttonDesign(homeButton);


        membershipPanel.add(homeButton);
        mainPanel.add(membershipPanel, "Push, align center");


        homeButton.addActionListener( e ->
        {
            mainWindow.showUserHome(user);

        });

        return mainPanel;
    }

    public JButton buttonDesign(JButton button)
    {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(102, 126, 234));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(250, 100));

        return button;
    }


}
