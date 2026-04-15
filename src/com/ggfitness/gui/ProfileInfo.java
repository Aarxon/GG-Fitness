package com.ggfitness.gui;

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
        JPanel outer = new JPanel(new MigLayout("fill, insets 15, gap 10"));
        outer.setBackground(new Color(13, 13, 13));

        JLabel avatar = new JLabel("●");
        avatar.setFont(new Font("Arial", Font.PLAIN, 200));
        avatar.setForeground(new Color(0, 0, 0));

        JLabel profileLabel = new JLabel("Profile");
        profileLabel.setFont(new Font("Impact", Font.BOLD, 24));

        JLabel bookingsLabel = new JLabel("Manage Your Bookings");
        bookingsLabel.setFont(new Font("Impact", Font.BOLD, 24));


        JLabel contactInfoLabel = new JLabel("Contact Info");
        JLabel personalDetailsLabel = new JLabel("Personal Details");
        JLabel membershipLabel = new JLabel("Membership status:");

        JLabel membershipActive = new JLabel("Active");
        membershipActive.setForeground(new Color(200, 255, 0));

        JLabel membershipInactive= new JLabel("Inactive");
        membershipInactive.setForeground(new Color(255, 0, 0));



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

        JPanel bookingsCard = new JPanel(new MigLayout("wrap, align center, insets 5 20, gap 10"));
        bookingsCard.setBackground(new Color(22, 22, 22));
        bookingsCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        bookingsCard.setPreferredSize(new Dimension(600, 800));




        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");
        outer.add(profileCard, "aligny top");
        outer.add(bookingsCard, "aligny top, growx, pushx");



        //Bookings
        bookingsCard.add(bookingsLabel, "align center, wrap 20");

        //Profile
        profileCard.add(profileLabel, "align center, wrap 20");

        //Profile Picture
        profileCard.add(avatar, "pushx, align center, wrap");


        //Membership Status
        profileCard.add(membershipLabel, "align center, wrap");
        profileCard.add(membershipActive, "align center, wrap 20");

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
