package com.ggfitness.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.Book;

import com.ggfitness.database.UserDBO;
import com.ggfitness.model.User;
import net.miginfocom.swing.MigLayout;
import com.formdev.flatlaf.*;


public class MainWindow extends JFrame
{
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private User user;
    UserForum userForum = new UserForum(this);
    TrainerFourm trainerFourm = new TrainerFourm(this);


    public MainWindow()
    {
        try
        {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(loginChoicePanel(), "choice");
        add(cardPanel);
        setSize(1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout.show(cardPanel, "choice");
        setVisible(true);
    }


    private JPanel loginChoicePanel()
    {
        JPanel outer = new JPanel(new MigLayout("fill, flowy, align center center"));
        outer.setBackground(new Color(13, 13, 13));

        //temp dev button
        JButton devBtn = new JButton("Admin Login");
        devBtn.setBackground(new Color(255, 0, 0));

        JPanel card = new JPanel(new MigLayout("wrap, align center, insets 50 60 50 60, gap 15"));
        card.setBackground(new Color(22, 22, 22));
        card.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        card.setMaximumSize(new Dimension(420, 700));

        JLabel title = new JLabel("GG-FITNESS");
        title.setFont(new Font("Impact", Font.PLAIN, 52));
        title.setForeground(new Color(200, 255, 0));

        JLabel subtitle = new JLabel("Member Login");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(120, 120, 120));

        JTextField emailField = new JTextField(20);
        emailField.putClientProperty("JTextField.placeholderText", "Username");

        JPasswordField passField = new JPasswordField(20);
        passField.putClientProperty("JTextField.placeholderText", "Password");

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBackground(new Color(200, 255, 0));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 15));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);

        JLabel trainerToggle = new JLabel("Login as Trainer instead");
        trainerToggle.setForeground(new Color(120, 120, 120));
        trainerToggle.setFont(new Font("Arial", Font.PLAIN, 13));
        trainerToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));

        trainerToggle.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                JPanel trainerPanel = trainerFourm.trainerLogin();
                cardPanel.add(trainerPanel, "trainerLogin");
                cardLayout.show(cardPanel, "trainerLogin");
            }
            public void mouseEntered(MouseEvent e)
            {
                trainerToggle.setForeground(new Color(200, 255, 0));
            }
            public void mouseExited(MouseEvent e)
            {
                trainerToggle.setForeground(new Color(120, 120, 120));
            }
        });

        JLabel register = new JLabel("Create an account");
        register.setForeground(new Color(120, 120, 120));
        register.setFont(new Font("Arial", Font.PLAIN, 13));
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                JPanel register = userForum.createAccount();
                cardPanel.add(register, "createAccount");
                cardLayout.show(cardPanel, "createAccount");
            }
            public void mouseEntered(MouseEvent e)
            {
                register.setForeground(new Color(200, 255, 0));
            }
            public void mouseExited(MouseEvent e)
            {
                register.setForeground(new Color(120, 120, 120));
            }
        });

        card.add(title, "align center");
        card.add(subtitle, "align center, wrap 20");
        card.add(emailField, "growx, wrap");
        card.add(passField, "growx, wrap 10");
        card.add(loginBtn, "growx, wrap 20, height 45!");
        card.add(trainerToggle, "align center");
        card.add(register, "align center");
        outer.add(card, "push, align center center");
        outer.add(devBtn, "align center");

        loginBtn.addActionListener(e ->
        {
            UserDBO user = new UserDBO();
            String email = emailField.getText().trim();
            String password = passField.getText().trim();

            User currentUser = user.loginUser(email, password);

            if(currentUser != null)
            {
                showUserHome(currentUser);
            }
        });

        devBtn.addActionListener(e ->
        {
            UserDBO user = new UserDBO();
            User currentUser = user.loginUser("admin@gmail.com", "admin");
            showUserHome(currentUser);
        });

        return outer;
    }


    protected void choiceLayout()
    {
        cardPanel.removeAll();
        cardPanel.add(loginChoicePanel(), "choice");
        cardLayout.show(cardPanel, "choice");
        cardPanel.revalidate();
        cardPanel.repaint();
        repaint();
    }


    public void showUserHome(User user)
    {
        UserHome userHome = new UserHome(user, this);
        JPanel userHomePanel = userHome.homeScreen();

        cardPanel.removeAll();
        cardPanel.add(userHomePanel, "userHome");
        cardLayout.show(cardPanel, "userHome");
        cardPanel.revalidate();
        cardPanel.repaint();
        repaint();
    }


    public void showProfileInfo(User user)
    {
        ProfileInfo profileInfo = new ProfileInfo(user, this);
        JPanel profileInfoPanel = profileInfo.profileHome();

        cardPanel.add(profileInfoPanel, "profileHome");
        cardLayout.show(cardPanel, "profileHome");
    }

    public void showMembershipInfo(User user)
    {
        MembershipInfo membershipInfo = new MembershipInfo(user, this);
        JPanel membershipInfoPanel = membershipInfo.membershipWindow();

        cardPanel.add(membershipInfoPanel, "membershipWindow");
        cardLayout.show(cardPanel, "membershipWindow");
    }

    public void showClassesInfo(User user)
    {
        BookClassesFourm bookClassesFourm = new BookClassesFourm(user, this);
        JPanel classesInfoPanel = bookClassesFourm.classesWindow();

        cardPanel.removeAll();
        cardPanel.add(classesInfoPanel, "classes");
        cardLayout.show(cardPanel, "classes");
        cardPanel.revalidate();
        cardPanel.repaint();
        repaint();
    }
}