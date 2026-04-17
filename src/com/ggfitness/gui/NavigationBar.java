package com.ggfitness.gui;

import com.ggfitness.database.BookingDBO;
import com.ggfitness.database.ScheduleDBO;
import com.ggfitness.model.Schedule;
import com.ggfitness.model.User;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NavigationBar extends JPanel
{
    public NavigationBar(User user, MainWindow mainWindow)
    {
        setLayout(new MigLayout("fill, insets 10"));
        setBackground(new Color(22, 22, 22));

        JLabel logoutLabel = new JLabel("Logout");
        logoutLabel.setForeground(new Color(120, 120, 120));
        logoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel gglabel = new JLabel("GG-Fitness");
        gglabel.setFont(new Font("Impact", Font.PLAIN, 30));
        gglabel.setForeground(new Color(200, 255, 0));
        gglabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gglabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) { mainWindow.showUserHome(user); }
        });

        JLabel nameLabel = new JLabel(user.getFirstName());
        nameLabel.setFont(new Font("Impact", Font.PLAIN, 22));
        nameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
            public void mouseClicked(MouseEvent e) { mainWindow.showProfileInfo(user); }
            public void mouseEntered(MouseEvent e) { nameLabel.setForeground(new Color(200, 255, 0)); }
            public void mouseExited(MouseEvent e)  { nameLabel.setForeground(new Color(120, 120, 120)); }
        });

        add(logoutLabel, "w 200!, align left, h 40!");
        add(gglabel, "pushx, align center");
        add(nameLabel, "align right, h 40!");
        add(avatar, "align right, h 45!");
    }


}