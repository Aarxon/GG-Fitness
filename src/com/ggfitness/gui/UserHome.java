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

        JPanel classesCard = new JPanel(new MigLayout("wrap, align center, insets 20, gap 10"));
        classesCard.setBackground(new Color(22, 22, 22));
        classesCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        classesCard.setPreferredSize(new Dimension(280, 320));

        JLabel classesLabel = new JLabel("Book a Class");
        classesLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        classesLabel.setForeground(Color.WHITE);
        classesLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        JPanel membershipCard = new JPanel(new MigLayout("wrap, align center, insets 20, gap 10"));
        membershipCard.setBackground(new Color(22, 22, 22));
        membershipCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        membershipCard.setPreferredSize(new Dimension(280, 320));

        JLabel membershipLabel = new JLabel("Manage Membership");
        membershipLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        membershipLabel.setForeground(Color.WHITE);

        classesLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                mainWindow.showClassesInfo(user);
            }
            public void mouseEntered(MouseEvent e)
            {
                classesLabel.setForeground(new Color(200, 255, 0));
            }
            public void mouseExited(MouseEvent e)
            {
                classesLabel.setForeground(new Color(255, 255, 255));
            }
        });

        trainersLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                mainWindow.showMeetTrainers(user);
            }
            public void mouseEntered(MouseEvent e)
            {
                trainersLabel.setForeground(new Color(200, 255, 0));
            }
            public void mouseExited(MouseEvent e)
            {
                trainersLabel.setForeground(new Color(255, 255, 255));
            }
        });

        membershipLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                mainWindow.showMembershipInfo(user);
            }
            public void mouseEntered(MouseEvent e)
            {
                membershipLabel.setForeground(new Color(200, 255, 0));
            }
            public void mouseExited(MouseEvent e)
            {
                membershipLabel.setForeground(new Color(255, 255, 255));
            }
        });





        outer.add(trainerCard, "align right");
        outer.add(classesCard, "align center");
        outer.add(membershipCard, "align left");
        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");
        classesCard.add(classesIcon, "align center, wrap");
        classesCard.add(classesLabel, "align center");
        trainerCard.add(trainerIcon, "align center, wrap");
        trainerCard.add(trainersLabel, "align center");
        membershipCard.add(newsIcon, "align center, wrap");
        membershipCard.add(membershipLabel, "align center");
        return outer;
    }
}