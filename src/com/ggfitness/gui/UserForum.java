package com.ggfitness.gui;

import com.ggfitness.database.UserDBO;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;


public class UserForum
{
    private final MainWindow mainWindow;

    public UserForum(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
    }


    JPanel createAccount()
    {
        JPanel outer = new JPanel(new MigLayout("fill, align center center"));
        outer.setBackground(new Color(13, 13, 13));

        JPanel card = new JPanel(new MigLayout("wrap, align center, insets 50 60 50 60, gap 15"));
        card.setBackground(new Color(22, 22, 22));
        card.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        card.setMaximumSize(new Dimension(420, 700));

        JLabel title = new JLabel("GG-FITNESS");
        title.setFont(new Font("Impact", Font.PLAIN, 52));
        title.setForeground(new Color(200, 255, 0));

        JLabel subtitle = new JLabel("Register as a member");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(120, 120, 120));

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

        JTextField firstNameField = new JTextField(20);
        firstNameField.putClientProperty("JTextField.placeholderText", "First Name");
        JTextField lastNameField = new JTextField(20);
        lastNameField.putClientProperty("JTextField.placeholderText", "Last Name");
        JTextField dobField = new JTextField(20);
        dobField.putClientProperty("JTextField.placeholderText", "Date of Birth (DD/MM/YYYY)");
        JTextField emailField = new JTextField(20);
        emailField.putClientProperty("JTextField.placeholderText", "Email");
        JPasswordField passField = new JPasswordField(20);
        passField.putClientProperty("JTextField.placeholderText", "Password");
        JTextField numberField = new JTextField(20);
        numberField.putClientProperty("JTextField.placeholderText", "Mobile Number");

        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(200, 255, 0));
        registerBtn.setForeground(Color.BLACK);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 15));
        registerBtn.setFocusPainted(false);
        registerBtn.setBorderPainted(false);

        card.add(title, "align center");
        card.add(subtitle, "align center, wrap 20");
        card.add(firstNameField, "growx, wrap");
        card.add(lastNameField, "growx, wrap");
        card.add(dobField, "growx, wrap");
        card.add(emailField, "growx, wrap");
        card.add(passField, "growx, wrap");
        card.add(numberField, "growx, wrap");
        card.add(registerBtn, "growx, wrap 20, height 45!");
        card.add(alreadyAccount, "align center, wrap 20");
        outer.add(card, "push, align center center");

        registerBtn.addActionListener(e ->
        {
            try
            {
                UserDBO user = new UserDBO();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String[] parts = dobField.getText().split("/");
                LocalDate dob = LocalDate.of(
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[0])
                );

                if (LocalDate.now().minusYears(18).isBefore(dob))
                {
                    JOptionPane.showMessageDialog(null, "You must be 18 or older to register");
                    return;
                }

                String email = emailField.getText().trim();
                String password = new String(passField.getPassword());
                String phoneNumber = numberField.getText();
                user.createNewUser(firstName, lastName, dob, email, password, phoneNumber);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Invalid date format, please use DD/MM/YYYY");
            }
        });

        return outer;
    }
}