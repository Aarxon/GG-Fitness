package com.ggfitness.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame
{
    UserForum userForum = new UserForum();
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public MainWindow()
    {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(loginChoicePanel(), "choice");

        add(cardPanel);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout.show(cardPanel, "choice");
        setVisible(true);
    }

    private JPanel loginChoicePanel()
    {
        JPanel LoginPanel = new JPanel(new MigLayout("fill, insets 50, gap 40"));

        //Added my images for the main login screen
        ImageIcon user = new ImageIcon(getClass().getResource("/images/user.png"));
        ImageIcon trainer = new ImageIcon(getClass().getResource("/images/trainer.png"));
        Image scaled = user.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        Image scaled2 = trainer.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);

        user = new ImageIcon(scaled);
        trainer = new ImageIcon(scaled2);

        JLabel userLabel = new JLabel(user);
        userLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel trainerLabel = new JLabel(trainer);
        trainerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel welcometitle = new JLabel("Welcome to GG-Fitness");
        welcometitle.setFont(new Font("Arial", Font.BOLD, 60));
        LoginPanel.add(welcometitle, "span, align center");

        JLabel title = new JLabel("Choose your Login Type");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        LoginPanel.add(title, "span, align center");


        LoginPanel.add(userLabel, "push, align center");
        LoginPanel.add(trainerLabel, "push, align center");

        //Mouse actions if they click the user icon
        userLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                int choice = JOptionPane.showConfirmDialog(null, "Do you already have an account?", "User", JOptionPane.YES_NO_OPTION);

                if(choice == JOptionPane.YES_OPTION)
                {
                    JPanel createPanel = userForum.existingAccount();

                    cardPanel.add(createPanel, "loginUserAccount");
                    cardLayout.show(cardPanel, "loginUserAccount");
                }
                else if(choice == JOptionPane.NO_OPTION)
                {
                    JPanel createPanel = userForum.createAccount();

                    cardPanel.add(createPanel, "createUserAccount");
                    cardLayout.show(cardPanel, "createUserAccount");
                }

            }
        });


        //Mouse actions if they click the trainer icon
        trainerLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {

            }
        });


        return LoginPanel;
    }

}
