package com.ggfitness.gui;

import com.ggfitness.database.UserDBO;
import com.ggfitness.model.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserForum
{
    private final MainWindow mainWindow;

    public UserForum(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
    }


       JPanel createAccount() {
        // Dark background
        JPanel outer = new JPanel(new MigLayout("fill, align center center"));
        outer.setBackground(new Color(13, 13, 13));

        // Center card
        JPanel card = new JPanel(new MigLayout("wrap, align center, insets 50 60 50 60, gap 15"));
        card.setBackground(new Color(22, 22, 22));
        card.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        card.setMaximumSize(new Dimension(420, 700));

        // Title
        JLabel title = new JLabel("GG-FITNESS");
        title.setFont(new Font("Impact", Font.PLAIN, 52));
        title.setForeground(new Color(200, 255, 0));

        //Subtitles
        JLabel subtitle = new JLabel("Register as a member");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel alreadyAccount = new JLabel("Already have an account?");
        alreadyAccount.setForeground(new Color(120, 120, 120));
        alreadyAccount.setFont(new Font("Arial", Font.PLAIN, 13));
        alreadyAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));

           alreadyAccount.addMouseListener(new MouseAdapter()
           {
               public void mouseClicked(MouseEvent e)
               {
                   mainWindow.choiceLayout();

               }

           });


           //Fields
        subtitle.setForeground(new Color(120, 120, 120));
        JTextField firstNameField = new JTextField(20);
        firstNameField.putClientProperty("JTextField.placeholderText", "First Name");
        JTextField lastNameField = new JTextField(20);
        lastNameField.putClientProperty("JTextField.placeholderText", "Last Name");
        JTextField emailField = new JTextField(20);
        emailField.putClientProperty("JTextField.placeholderText", "Email");
        JPasswordField passField = new JPasswordField(20);
        passField.putClientProperty("JTextField.placeholderText", "Password");
        JTextField numberField = new JTextField(20);
        numberField.putClientProperty("JTextField.placeholderText", "Mobile Number");


        // Login button
        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(200, 255, 0));
        registerBtn.setForeground(Color.BLACK);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 15));
        registerBtn.setFocusPainted(false);
        registerBtn.setBorderPainted(false);


        card.add(title, "align center");
        card.add(subtitle, "align center, wrap 20");
        card.add(firstNameField,"growx, wrap");
        card.add(lastNameField, "growx, wrap");
        card.add(emailField, "growx, wrap");
        card.add(passField, "growx, wrap ");
        card.add(numberField, "growx, wrap ");
        card.add(registerBtn, "growx, wrap 20, height 45!");
        card.add(alreadyAccount, "align center, wrap 20");
        outer.add(card, "push, align center center");

           registerBtn.addActionListener(e ->
           {
               UserDBO user = new UserDBO();

               String firstName = firstNameField.getText();
               String lastName = lastNameField.getText();
               String email = emailField.getText().trim();
               String password = new String(passField.getPassword());
               String phoneNumber = numberField.getText();

               user.createNewUser(firstName,lastName,email,password,phoneNumber);
           });

        return outer;
    }


}
