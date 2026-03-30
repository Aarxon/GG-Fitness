package com.ggfitness.gui;
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
        JPanel outer = new JPanel(new MigLayout("fill, insets 10"));
        outer.setBackground(new Color(13, 13, 13));

        JPanel center = new JPanel(new MigLayout("insets 10, gap 30"));
        center.setBackground(new Color(13, 13, 13));

        JPanel mondayCard = makeCard("Monday");
        JPanel tuesdayCard = makeCard("Tuesday");
        JPanel thursdayCard = makeCard("Thursday");
        JPanel fridayCard = makeCard("Friday");
        JPanel saturdayCard = makeCard("Saturday");

        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");
        outer.add(center, "align center, gap 10");
        center.add(mondayCard);
        center.add(tuesdayCard);
        center.add(thursdayCard);
        center.add(fridayCard);
        center.add(saturdayCard);
        return outer;
    }
    private JPanel makeCard(String day)
    {
        JPanel card = new JPanel(new MigLayout("wrap, align center, insets 15, gap 5"));
        card.setBackground(new Color(22, 22, 22));
        card.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        card.setPreferredSize(new Dimension(200, 500));

        JLabel label = new JLabel(day);
        label.setFont(new Font("Impact", Font.PLAIN, 24));
        label.setForeground(new Color(200, 255, 0));

        card.add(label, "align center");
        return card;
    }
}