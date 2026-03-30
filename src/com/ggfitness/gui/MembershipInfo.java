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

    public JPanel membershipWindow() {
        JPanel mainPanel = new JPanel(new MigLayout());
        JPanel membershipPanel = new JPanel(new MigLayout());

        JButton homeButton = new JButton("Home");


        membershipPanel.add(homeButton);
        mainPanel.add(membershipPanel, "Push, align center");

        return mainPanel;
    }
}
