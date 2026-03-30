package com.ggfitness.gui;

import com.ggfitness.database.UserDBO;
import net.miginfocom.swing.MigLayout;
import com.ggfitness.model.User;

import javax.swing.*;
import java.awt.*;

public class BookClassesFourm
{
    private User user;
    private MainWindow mainWindow;

    public BookClassesFourm(User user, MainWindow mainWindow)
    {
        this.user = user;
        this.mainWindow = mainWindow;
    }

    public JPanel classesWindow()
    {
        JPanel outer = new JPanel(new MigLayout());
        JPanel classesPanel = new JPanel(new MigLayout());

        JButton homeButton = new JButton("Home");


        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");


        homeButton.addActionListener( e ->
        {
            mainWindow.showUserHome(user);

        });
        return outer;
    }

}
