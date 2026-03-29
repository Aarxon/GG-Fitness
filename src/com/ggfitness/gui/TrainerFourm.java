package com.ggfitness.gui;

import com.ggfitness.database.TrainerDBO;
import com.ggfitness.database.UserDBO;
import com.ggfitness.model.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrainerFourm
{
    private final MainWindow mainWindow;

    public TrainerFourm(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
    }


    public JPanel trainerLogin()
    {
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

        JLabel subtitle = new JLabel("Trainer Login");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(120, 120, 120));

        // Fields
        JTextField emailField = new JTextField(20);
        emailField.putClientProperty("JTextField.placeholderText", "Username");

        JPasswordField passField = new JPasswordField(20);
        passField.putClientProperty("JTextField.placeholderText", "Password");

        // Login button
        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBackground(new Color(200, 255, 0));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 15));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);

        // Trainer toggle link
        JLabel userToggle = new JLabel("Login as user");
        userToggle.setForeground(new Color(120, 120, 120));
        userToggle.setFont(new Font("Arial", Font.PLAIN, 13));
        userToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));

        userToggle.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                mainWindow.choiceLayout();
            }
            public void mouseEntered(MouseEvent e)
            {
                userToggle.setForeground(new Color(200, 255, 0));
            }
            public void mouseExited(MouseEvent e)
            {
                userToggle.setForeground(new Color(120, 120, 120));
            }
        });


        card.add(title, "align center");
        card.add(subtitle, "align center, wrap 20");
        card.add(emailField, "growx, wrap");
        card.add(passField, "growx, wrap 10");
        card.add(loginBtn, "growx, wrap 20, height 45!");
        card.add(userToggle, "align center");
        outer.add(card, "push, align center center");


        return outer;

    }
}
