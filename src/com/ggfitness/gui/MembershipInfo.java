package com.ggfitness.gui;


import com.ggfitness.database.MembershipDBO;
import com.ggfitness.database.UserDBO;
import net.miginfocom.swing.MigLayout;
import com.ggfitness.model.User;

import javax.swing.*;
import java.awt.*;


public class MembershipInfo
{
    private User user;
    private MainWindow mainWindow;

    public MembershipInfo(User user, MainWindow mainWindow)
    {
        this.user = user;
        this.mainWindow = mainWindow;
    }

    public JPanel membershipWindow() {
    JPanel outer = new JPanel(new MigLayout("fill, insets 15, gap 5"));
    outer.setBackground(new Color(13, 13, 13));

    JLabel title = new JLabel("Choose Your Plan");
    title.setFont(new Font("Impact", Font.PLAIN, 42));
    title.setForeground(new Color(200, 255, 0));

    JLabel subtitle = new JLabel("Flexible memberships to suit your fitness goals");
    subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
    subtitle.setForeground(new Color(120, 120, 120));

    JPanel plansPanel = new JPanel(new MigLayout("insets 10, gap 20"));
    plansPanel.setBackground(new Color(13, 13, 13));

    JPanel monthCard = makePlanCard("1 Month", "€40", "€40 / month", "Perfect for getting started", 1);
    JPanel sixMonthCard = makePlanCard("6 Months", "€210", "€35 / month", "Commit to your fitness", 6);
    JPanel yearCard = makePlanCard("12 Months", "€360", "€30 / month", "Best value for money", 12);

    outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");
    outer.add(title, "align center, wrap 2");
    outer.add(subtitle, "align center, wrap 15");
    outer.add(plansPanel, "align center, push");

    plansPanel.add(monthCard);
    plansPanel.add(sixMonthCard);
    plansPanel.add(yearCard);

    return outer;
}

    private JPanel makePlanCard(String plan, String price, String perMonth, String description, int months)  {
        JPanel card = new JPanel(new MigLayout("wrap, align center, insets 40 40 40 40, gap 10"));
        card.setBackground(new Color(22, 22, 22));
        card.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        card.setPreferredSize(new Dimension(220, 300));

        JLabel planLabel = new JLabel(plan);
        planLabel.setFont(new Font("Impact", Font.PLAIN, 28));
        planLabel.setForeground(new Color(200, 255, 0));

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 22));
        priceLabel.setForeground(Color.WHITE);

        JLabel perMonthLabel = new JLabel(perMonth);
        perMonthLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        perMonthLabel.setForeground(new Color(120, 120, 120));

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(120, 120, 120));

        JButton buyBtn = new JButton("Buy");
        buyBtn.setBackground(new Color(200, 255, 0));
        buyBtn.setForeground(Color.BLACK);
        buyBtn.setFont(new Font("Arial", Font.BOLD, 15));
        buyBtn.setFocusPainted(false);
        buyBtn.setBorderPainted(false);
        buyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buyBtn.addActionListener(e -> {
            MembershipDBO membershipDBO = new MembershipDBO();
            if(membershipDBO.checkMembership(user.getUser_id()))
            {
                JOptionPane.showMessageDialog(null, "You already have a membership!");
                return;
            }
            else {
                membershipDBO.createNewMembership(user.getUser_id(), plan, months);
                JOptionPane.showMessageDialog(null, "Membership activated!");
            }
        });

        card.add(planLabel, "align center");
        card.add(priceLabel, "align center");
        card.add(perMonthLabel, "align center");
        card.add(descLabel, "align center, wrap 20");
        card.add(buyBtn, "growx, height 45!");

        return card;
    }
}
