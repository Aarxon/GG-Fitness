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
        JPanel trainerCard = new JPanel(new MigLayout("wrap, align center, insets 20, gap 10"));
        trainerCard.setBackground(new Color(22, 22, 22));
        trainerCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        trainerCard.setPreferredSize(new Dimension(280, 320));

        JLabel classesIcon = new JLabel("◉");
        classesIcon.setFont(new Font("Arial", Font.PLAIN, 60));
        classesIcon.setForeground(new Color(200, 255, 0));

        JLabel trainerIcon = new JLabel("◉");
        trainerIcon.setFont(new Font("Arial", Font.PLAIN, 60));
        trainerIcon.setForeground(new Color(200, 255, 0));

        JLabel newsIcon = new JLabel("◉");
        newsIcon.setFont(new Font("Arial", Font.PLAIN, 60));
        newsIcon.setForeground(new Color(200, 255, 0));

        JLabel trainersLabel = new JLabel("Meet Our Trainers");
        trainersLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        trainersLabel.setForeground(Color.WHITE);
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


        outer.add(trainerCard, "align right");
        outer.add(classesCard, "align center");
        outer.add(newsCard, "align left");
        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");

        classesCard.add(classesIcon, "align center, wrap");
        classesCard.add(classesLabel, "align center");
        trainerCard.add(trainerIcon, "align center, wrap");
        trainerCard.add(trainersLabel, "align center");
        newsCard.add(newsIcon, "align center, wrap");
        newsCard.add(newsLabel, "align center");



        return outer;
    }

}
