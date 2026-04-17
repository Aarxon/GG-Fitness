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
        JPanel outer = new JPanel(new MigLayout("fill, insets 15, gap 20"));
        outer.setBackground(new Color(13, 13, 13));

        JPanel plansPanel = new JPanel(new MigLayout("insets 10, gap 20"));
        plansPanel.setBackground(new Color(13, 13, 13));

        JPanel monthCard = makePlanCard("1 Month", "€40", 1);
        JPanel sixMonthCard = makePlanCard("6 Months", "€240", 6);
        JPanel yearCard = makePlanCard("12 Months", "€480", 12);

        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");
        outer.add(plansPanel, "align center");

        plansPanel.add(monthCard);
        plansPanel.add(sixMonthCard);
        plansPanel.add(yearCard);

        return outer;
    }

        private JPanel makePlanCard(String plan, String price, int months) {
            JPanel card = new JPanel(new MigLayout("wrap, align center, insets 50 60 50 60, gap 15"));
            card.setBackground(new Color(22, 22, 22));
            card.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
            card.setPreferredSize(new Dimension(200, 500));

            JLabel planLabel = new JLabel(plan);
            planLabel.setFont(new Font("Impact", Font.PLAIN, 28));
            planLabel.setForeground(new Color(200, 255, 0));

            JLabel priceLabel = new JLabel(price);
            priceLabel.setFont(new Font("Arial", Font.BOLD, 22));
            priceLabel.setForeground(Color.WHITE);

            JButton buyBtn = new JButton("Buy");
            buyBtn.setBackground(new Color(200, 255, 0));
            buyBtn.setForeground(Color.BLACK);
            buyBtn.setFont(new Font("Arial", Font.BOLD, 15));
            buyBtn.setFocusPainted(false);
            buyBtn.setBorderPainted(false);
            buyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            buyBtn.addActionListener(e ->
            {
                MembershipDBO membershipDBO = new MembershipDBO();

                if(membershipDBO.checkMembership(user.getUser_id()) == true)
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
            card.add(priceLabel, "align center, wrap 20");
            card.add(buyBtn, "growx, height 45!");

            return card;
        }
}
