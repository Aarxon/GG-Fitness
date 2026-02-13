package com.ggfitness.gui;

import com.ggfitness.database.UserDBO;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UserForum
{

    public UserForum()
    {

    }

    public JPanel createAccount()
    {
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        createAccountPanel.add(Box.createVerticalGlue());

        JTextField firstNameField = new JTextField(20);
        createAccountPanel.add(createFieldPanel("Enter First Name: ", firstNameField));
        createAccountPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField lastNameField = new JTextField(20);
        createAccountPanel.add(createFieldPanel("Enter Last Name: ", lastNameField));
        createAccountPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField numberField = new JTextField(20);
        createAccountPanel.add(createFieldPanel("Enter Number: ", numberField));
        createAccountPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField emailField = new JTextField(20);
        createAccountPanel.add(createFieldPanel("Enter Email: ", emailField));
        createAccountPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPasswordField passwordField = new JPasswordField(20);
        createAccountPanel.add(createFieldPanel("Enter Password: ", passwordField));

        JButton createButton = new JButton("Create Account");
        createButton.setLayout(new BoxLayout(createButton, BoxLayout.Y_AXIS));
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountPanel.add(createButton);

        JButton backButton = new JButton("Back");
        createButton.setLayout(new BoxLayout(createButton, BoxLayout.Y_AXIS));
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountPanel.add(backButton);

        createButton.addActionListener(e ->
        {
            UserDBO user = new UserDBO();

            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            String phoneNumber = numberField.getText();

            user.createNewUser(firstName,lastName,email,password,phoneNumber);
        });

        backButton.addActionListener(e ->
        {


        });

        createAccountPanel.add(Box.createVerticalGlue());
        return createAccountPanel;
    }

    public JPanel existingAccount()
    {
        JPanel logInPanel = new JPanel(new MigLayout("fill"));

        JPanel formPanel = new JPanel(new MigLayout("insets 20"));

        JTextField emailField = new JTextField(20);
        JLabel emailLabel = new JLabel("Enter Email");
        formPanel.add(emailLabel, "align center, wrap");
        formPanel.add(emailField, "align center, wrap");

        JPasswordField passwordField = new JPasswordField(20);
        JLabel passwordLabel = new JLabel("Enter Password");
        formPanel.add(passwordLabel, "align center, wrap");
        formPanel.add(passwordField, "align center, wrap");

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");

        formPanel.add(loginButton, "split 2, align center");
        formPanel.add(backButton, "align center");

        logInPanel.add(formPanel, "push, align center");

        return logInPanel;
    }

    private JPanel createFieldPanel(String labelText, JTextField textField)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        textField.setMaximumSize(textField.getPreferredSize()); // important!
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        panel.add(textField);

        return panel;
    }
}
