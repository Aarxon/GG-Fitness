package com.ggfitness.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.Book;

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
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

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
        JPanel LoginPanel = new JPanel(new MigLayout("fill, insets 50, gap 30"));
        LoginPanel.setBackground(Color.GRAY);

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
                    JPanel createPanel = userForum.createAccount();

                    cardPanel.add(createPanel, "createUserAccount");
                    cardLayout.show(cardPanel, "createUserAccount");
            }
        });


        //Mouse actions if they click the trainer icon
        trainerLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {

                JPanel trainerPael = trainerFourm.trainerLogin();

                cardPanel.add(trainerPael, "trainerLogin");
                cardLayout.show(cardPanel, "trainerLogin");

            }
        });


        return LoginPanel;
    }


    protected void choiceLayout()
    {
        cardLayout.show(cardPanel, "choice");
    }

    protected void loginLayout()
    {
        JPanel createPanel = userForum.existingAccount();

        cardPanel.add(createPanel, "createExistingAccount");
        cardLayout.show(cardPanel, "createExistingAccount");

    }

    public void showUserHome(User user)
    {
        UserHome userHome = new UserHome(user, this);
        JPanel userHomePanel = userHome.homeScreen();

        cardPanel.add(userHomePanel, "userHome");
        cardLayout.show(cardPanel, "userHome");
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

        cardPanel.add(classesInfoPanel, "classesWindow");
        cardLayout.show(cardPanel, "classesWindow");
    }



}
