package com.ggfitness.gui;


import net.miginfocom.swing.MigLayout;
import com.ggfitness.model.User;

import javax.swing.*;
import java.awt.*;

public class MeetTrainers
{
    private User user;
    private MainWindow mainWindow;

    public MeetTrainers(User user, MainWindow mainWindow)
    {
        this.user = user;
        this.mainWindow = mainWindow;
    }

    public JPanel meetTheTrainers()
    {
        // All arrays are indexed together — keep them in the same order
        String[] trainers = {"Sarah", "Emma", "James"};
        String[] educations =
                {
                "BSc Sports Science, Certified Yoga & Pilates Instructor",
                "Certified Spinning Instructor, Diploma in Sports Nutrition",
                "BSc Physical Education, Certified Personal Trainer"
        };
        String[][] specialties =
                {
                {"Yoga", "Pilates", "Flexibility & Mindfulness"},
                {"Spinning", "Endurance Training", "Cardiovascular Fitness"},
                {"HIIT", "Strength Training", "Sports Conditioning"}
        };
        String[] bios =
                {
                "Sarah is a certified yoga and pilates instructor with 8 years of experience, helping clients build strength, flexibility and mindfulness.",
                "Emma is a high-energy spinning coach passionate about endurance and pushing clients to their limits on the bike.",
                "James is a former athlete turned personal trainer, specialising in HIIT and strength training to help clients build power and burn fat."
        };

        JPanel outer = new JPanel(new MigLayout("fill, insets 15, gap 10"));
        outer.setBackground(new Color(13, 13, 13));

        JPanel center = new JPanel(new MigLayout("insets 10, gap 30"));
        center.setBackground(new Color(13, 13, 13));


        for (int i = 0; i < trainers.length; i++)
        {
            JPanel card = makeCard(trainers[i]);
            populateCard(card, trainers[i], educations[i], specialties[i], bios[i]);
            center.add(card);
        }


        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");
        outer.add(center, "align center, gap 10");

        return outer;
    }

    private JPanel makeCard(String name)
    {
        JPanel card = new JPanel(new MigLayout("wrap, align center, insets 15, gap 5"));
        card.setBackground(new Color(22, 22, 22));
        card.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        card.setPreferredSize(new Dimension(400, 600));
        JLabel label = new JLabel();
        label.setFont(new Font("Impact", Font.PLAIN, 24));
        label.setForeground(new Color(200, 255, 0));


        card.add(label, "align center");
        return card;
    }

    private void populateCard(JPanel card, String name, String education, String[] specialties, String bio)
    {
        // Name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Impact", Font.PLAIN, 28));
        nameLabel.setForeground(new Color(200, 255, 0));
        card.add(nameLabel, "align center, wrap 10");

        // Specialty tag (green pill at top)
        JLabel specTag = new JLabel(specialties[0]);
        specTag.setFont(new Font("Arial", Font.BOLD, 12));
        specTag.setForeground(new Color(13, 13, 13));
        specTag.setBackground(new Color(200, 255, 0));
        specTag.setOpaque(true);
        specTag.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        card.add(specTag, "align center, wrap 25");

        // Education Section
        JLabel eduTitle = new JLabel("Education");
        eduTitle.setFont(new Font("Arial", Font.BOLD, 13));
        eduTitle.setForeground(new Color(200, 255, 0));
        card.add(eduTitle, "align center, wrap 10");

        JLabel eduLabel = new JLabel("<html><div style='width:280px;text-align:center'>" + education + "</div></html>");
        eduLabel.setForeground(new Color(160, 160, 160));
        eduLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(eduLabel, "align center, wrap 25");

        // Specialisations bullet points
        JLabel specTitle = new JLabel("Specialisations");
        specTitle.setFont(new Font("Arial", Font.BOLD, 13));
        specTitle.setForeground(new Color(200, 255, 0));
        card.add(specTitle, "align center, wrap 10");

        for (String s : specialties)
        {
            JLabel bullet = new JLabel("• " + s);
            bullet.setForeground(new Color(160, 160, 160));
            bullet.setFont(new Font("Arial", Font.PLAIN, 12));
            card.add(bullet, "align center, wrap 3");
        }

        // Spacer
        card.add(Box.createVerticalStrut(15), "wrap");

        // About Section
        JLabel aboutTitle = new JLabel("About");
        aboutTitle.setFont(new Font("Arial", Font.BOLD, 13));
        aboutTitle.setForeground(new Color(200, 255, 0));
        card.add(aboutTitle, "align center, wrap 5");

        JLabel bioLabel = new JLabel("<html><div style='width:280px;text-align:center'>" + bio + "</div></html>");
        bioLabel.setForeground(new Color(160, 160, 160));
        bioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(bioLabel, "align center, wrap");
    }


}
