package com.ggfitness.gui;

import com.ggfitness.model.User;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;

public class UserHome
{
    private User user;
    private MainWindow mainWindow;

    public UserHome(User user, MainWindow mainWindow)
    {
        this.user = user;
        this.mainWindow = mainWindow;
    }

    public JPanel homeScreen()
    {
        JPanel navBar = new JPanel(new MigLayout("fill, insets 10"));
        navBar.setBackground(new Color(22, 22, 22));

        JPanel outer = new JPanel(new MigLayout("fill, insets 0"));
        outer.setBackground(new Color(13, 13, 13));


        //Classes
        JPanel classesCard = new JPanel(new MigLayout("wrap, align center, insets 20, gap 10"));
        classesCard.setBackground(new Color(22, 22, 22));
        classesCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        classesCard.setPreferredSize(new Dimension(280, 320));

        JLabel classesLabel = new JLabel("Book a Class");
        classesLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        classesLabel.setForeground(Color.WHITE);
        //Classes

        //Memberships
        JPanel membershipCard = new JPanel(new MigLayout("wrap, align center, insets 20, gap 10"));
        membershipCard.setBackground(new Color(22, 22, 22));
        membershipCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        membershipCard.setPreferredSize(new Dimension(280, 320));

        JLabel membershipLabel = new JLabel("Membership");
        membershipLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        membershipLabel.setForeground(Color.WHITE);
        //Memberships

        //News
        JPanel newsCard = new JPanel(new MigLayout("wrap, align center, insets 20, gap 10"));
        newsCard.setBackground(new Color(22, 22, 22));
        newsCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        newsCard.setPreferredSize(new Dimension(280, 320));

        JLabel newsLabel = new JLabel("News");
        newsLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        newsLabel.setForeground(Color.WHITE);
        //News

        JLabel logoutLabel = new JLabel("Logout");
        logoutLabel.setForeground(new Color(120, 120, 120));
        logoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));


        JLabel nameLabel = new JLabel(user.getFirstName());
        nameLabel.setFont(new Font("Impact", Font.PLAIN, 22));
        nameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));


        JLabel gglabel = new JLabel("GG-Fitness");
        gglabel.setFont(new Font("Impact", Font.PLAIN, 30));
        gglabel.setForeground(new Color(200, 255, 0));

        JLabel avatar = new JLabel("●");
        avatar.setFont(new Font("Arial", Font.PLAIN, 40));
        avatar.setForeground(new Color(0, 0, 0));

            logoutLabel.addMouseListener(new MouseAdapter()
            {
            public void mouseClicked(MouseEvent e)
            {
                mainWindow.choiceLayout();
            }
            public void mouseEntered(MouseEvent e)
            {
                logoutLabel.setForeground(new Color(200, 255, 0));
            }
            public void mouseExited(MouseEvent e)
            {
                logoutLabel.setForeground(new Color(120, 120, 120));
            }
        });

            nameLabel.addMouseListener(new MouseAdapter()
            {
            public void mouseClicked(MouseEvent e)
            {
                mainWindow.showProfileInfo(user);
            }
            public void mouseEntered(MouseEvent e)
            {
                nameLabel.setForeground(new Color(200, 255, 0));
            }
            public void mouseExited(MouseEvent e)
            {
                nameLabel.setForeground(new Color(120, 120, 120));
            }
        });



        //mig add section
        outer.add(navBar, "dock north, growx, h 80!");
        outer.add(membershipCard, "align right");
        outer.add(classesCard, "align center");
        outer.add(newsCard, "align left");

        classesCard.add(classesLabel, "align center");
        membershipCard.add(membershipLabel, "align center");
        newsCard.add(newsLabel, "align center");



        navBar.add(logoutLabel, "w 200!, align left, h 40!");
        navBar.add(gglabel, "pushx, align center");
        navBar.add(nameLabel, "align right, h 40!");
        navBar.add(avatar, "align right, h 45!");


        return outer;
    }

}
