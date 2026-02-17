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
        JPanel topPanel = new JPanel(new MigLayout("fill, insets 10"));
        JPanel mainPanel = new JPanel(new MigLayout("fill, insets 0"));
        JPanel profilePanel = new JPanel(new MigLayout());

        JLabel profileDetails = new JLabel("Profile Details");

        JLabel firstName = new JLabel("First Name: ");
        JTextField firstNameField = new JTextField(10);
        firstNameField.setText(user.getFirstName());

        JLabel surname = new JLabel("Surname: ");
        JTextField surnameField = new JTextField(10);
        surnameField.setText(user.getlastName());

        JLabel contactInfo = new JLabel("Contact Info: ");

        JLabel email = new JLabel("Email: ");
        JTextField emailField = new JTextField(10);
        emailField.setText(user.getEmail());

        JLabel phone = new JLabel("Phone: ");
        JTextField phoneField = new JTextField(10);
        phoneField.setText(user.getPhone());

        JButton updateButton = new JButton("Update Details");
        JButton homeButton = new JButton("Home");

        breakDesign(profileDetails);
        labelDesign(firstName);
        labelDesign(surname);
        breakDesign(contactInfo);
        labelDesign(email);
        labelDesign(phone);

        profilePanel.add(profileDetails, "align center, wrap, gapbottom 20");

        profilePanel.add(firstName, "align center");
        profilePanel.add(firstNameField, "align center, wrap, gapbottom 10");


        profilePanel.add(surname, "align center");
        profilePanel.add(surnameField, "align center, wrap, gapbottom 10");

        profilePanel.add(contactInfo, "align center, wrap, gapbottom 20");

        profilePanel.add(email, "align center");
        profilePanel.add(emailField, "align center, wrap, gapbottom 10");


        profilePanel.add(phone, "align center");
        profilePanel.add(phoneField, "align center,wrap, gapbottom 5");

        buttonDesign(updateButton);
        buttonDesign(homeButton);
        profilePanel.add(updateButton, "align center, wrap");
        profilePanel.add(homeButton, "align center");



        topPanel.setBackground(new Color(70, 80, 150));

        mainPanel.add(topPanel, "dock north, growx, h 80!");
        mainPanel.add(profilePanel, "push, align center");

        updateButton.addActionListener(e ->
        {
            int choice = JOptionPane.showConfirmDialog(mainPanel, "Are you sure you wish to update your pofile info?", "Update Profile", JOptionPane.OK_CANCEL_OPTION);

            if(choice == JOptionPane.OK_OPTION)
            {
                UserDBO userDBO = new UserDBO();



                userDBO.updateUser(firstNameField.getText(), surnameField.getText(),  emailField.getText(), phoneField.getText(), user.getUser_id());
                System.out.println(user.getUser_id());
                System.out.println(user.getFirstName());

            }
            else if(choice == JOptionPane.CANCEL_OPTION)
            {
                JOptionPane.showMessageDialog(mainPanel, "No changes have been made");
            }
        });

        homeButton.addActionListener( e ->
        {
            mainWindow.showUserHome(user);

        });

        return mainPanel;
    }

    public JLabel labelDesign(JLabel label)
    {
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        return label;
    }

    public JLabel breakDesign(JLabel label)
    {
        label.setFont(new Font("Arial", Font.BOLD, 24));
        return label;
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
