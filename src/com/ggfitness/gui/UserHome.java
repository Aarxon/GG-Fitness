package com.ggfitness.gui;

import com.ggfitness.model.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UserHome
{
    private User user;
    private MainWindow mainWindow;

    public UserHome(User user, MainWindow mainWindow)
    {
        this.user = user;
        this.mainWindow = mainWindow;
    }

    public JPanel homeScreen()
    {
        JPanel topPanel = new JPanel(new MigLayout("fill, insets 10"));
        JPanel mainPanel = new JPanel(new MigLayout("fill, insets 0"));


        JLabel welcomeLabel = new JLabel("Welcome " + user.getFirstName() );
        JButton profileButton = new JButton("Profile");
        JButton manageButton = new JButton("Manage Membership");
        JLabel nameLabel = new JLabel("Welcome" + ", " + user.getFirstName());
        JLabel ggLabel = new JLabel("GG Fitness");
        JButton classesButton = new JButton("Book a Class");

        buttonDesign(classesButton);
        buttonDesign(manageButton);
        buttonDesign(profileButton);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));


        mainPanel.add(classesButton, "align center");
        mainPanel.add(manageButton, "align center");
        mainPanel.add(profileButton, "align center");
        topPanel.setBackground(new Color(70, 80, 150));
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(topPanel, "dock north, growx, h 80!");

        topPanel.add(nameLabel, "align right, wrap, h 60!");

        profileButton.addActionListener(e ->
        {
            mainWindow.showProfileInfo(user);

        });

        classesButton.addActionListener(e ->
        {
            mainWindow.showClassesInfo(user);

        });

        manageButton.addActionListener(e ->
        {
            mainWindow.showMembershipInfo(user);

        });


        return mainPanel;
    }

    private JButton buttonDesign(JButton button)
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
