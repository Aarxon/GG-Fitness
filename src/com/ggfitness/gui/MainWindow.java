package com.ggfitness.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JPanel LoginPanel = new JPanel();
        LoginPanel.setLayout(new BoxLayout(LoginPanel, BoxLayout.Y_AXIS ));
        LoginPanel.setOpaque(false);

        //Added my images for the main login screen
        ImageIcon user = new ImageIcon(getClass().getResource("/images/user.png"));
        ImageIcon trainer = new ImageIcon(getClass().getResource("/images/trainer.png"));
        Image scaled = user.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        Image scaled2 = trainer.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);

        user = new ImageIcon(scaled);
        trainer = new ImageIcon(scaled2);

        //Labels with cursor set to hand cursor when they hover
        JLabel userLabel = new JLabel(user);
        userLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel trainerLabel = new JLabel(trainer);
        trainerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));


        LoginPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        LoginPanel.add(userLabel);
        LoginPanel.add(Box.createRigidArea(new Dimension(400, 50)));
        LoginPanel.add(trainerLabel);

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
