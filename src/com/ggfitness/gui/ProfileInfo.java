package com.ggfitness.gui;

import com.ggfitness.database.UserDBO;
import net.miginfocom.swing.MigLayout;
import com.ggfitness.model.User;

import javax.swing.*;
import java.awt.*;


public class ProfileInfo
{
    private final User user;
    private final MainWindow mainWindow;

    public ProfileInfo(User user, MainWindow mainWindow)
    {
        this.user = user;
        this.mainWindow = mainWindow;
    }

    public JPanel profileHome()
    {
        JPanel outer = new JPanel(new MigLayout("fill, insets 15, gap 15"));
        outer.setBackground(new Color(13, 13, 13));

        JLabel avatar = new JLabel("●");
        avatar.setFont(new Font("Arial", Font.PLAIN, 200));
        avatar.setForeground(new Color(0, 0, 0));

        JLabel contactInfoLabel = new JLabel("Contact Info");
        JLabel personalDetailsLabel = new JLabel("Personal Details");



        JTextField nameField = new JTextField(20);
        nameField.setText(user.getFirstName() + " " + user.getlastName());
        nameField.setEditable(false);


        JTextField emailField = new JTextField(20);
        emailField.setText(user.getEmail());
        JTextField numberField = new JTextField(20);
        numberField.setText(user.getPhone());


        JPanel profileCard = new JPanel(new MigLayout("wrap, align center, insets 5 20, gap 10"));
        profileCard.setBackground(new Color(22, 22, 22));
        profileCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        profileCard.setPreferredSize(new Dimension(350, 800));




        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");
        outer.add(profileCard, "align left");



        //Profile Picture
        profileCard.add(avatar, "pushx, align center, wrap");

        //Personal Details
        profileCard.add(personalDetailsLabel, "align center, wrap");
        profileCard.add(nameField, "align center, growy, wrap");

        //Contact Info
        profileCard.add(contactInfoLabel, "align center, wrap");
        profileCard.add(emailField, "align center, growy, wrap");
        profileCard.add(numberField, "align center, growy, wrap");

        return outer;
    }


}
