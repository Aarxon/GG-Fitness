package com.ggfitness.gui;
import com.ggfitness.model.Schedule;
import net.miginfocom.swing.MigLayout;
import com.ggfitness.model.*;
import com.ggfitness.database.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookClassesForm
{
    private User user;
    private MainWindow mainWindow;


    public BookClassesForm(User user, MainWindow mainWindow)
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

        populateCard(mondayCard, "MONDAY");
        populateCard(tuesdayCard, "TUESDAY");
        populateCard(thursdayCard, "THURSDAY");
        populateCard(fridayCard, "FRIDAY");
        populateCard(saturdayCard, "SATURDAY");


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

    private void populateCard(JPanel card, String day)
    {
        ScheduleDBO scheduleDBO = new ScheduleDBO();
        List<Schedule> schedules = scheduleDBO.getScheduleByDay(day);
        for(Schedule s : schedules)
        {
            BookingDBO bookingDBO = new BookingDBO();

            JLabel classLabel = new JLabel(s.getClassName());
            classLabel.setForeground(Color.WHITE);
            classLabel.setFont(new Font("Arial", Font.BOLD, 14));

            JLabel timeLabel = new JLabel(s.getStartTime() + " - " + s.getEndTime());
            timeLabel.setForeground(new Color(120, 120, 120));
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            JLabel trainerLabel = new JLabel(s.getTrainerName());
            trainerLabel.setForeground(new Color(120, 120, 120));
            trainerLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            JButton bookBtn = getJButton(s, bookingDBO);

            card.add(classLabel, "align center, wrap");
            card.add(timeLabel, "align center, wrap");
            card.add(trainerLabel, "align center, wrap");
            card.add(bookBtn, "growx, wrap 15");
        }
    }

    private JButton getJButton(Schedule s, BookingDBO bookingDBO) {
        JButton bookBtn = new JButton("Book");
        bookBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bookingDBO.isAlreadyBooked(user.getUser_id(), s.getSchedule_id());

        // Set initial button state on load — disables it if already booked
        if(bookingDBO.isAlreadyBooked(user.getUser_id(), s.getSchedule_id()))
        {
            bookBtn.setText("Booked");
            bookBtn.setEnabled(false);
        }

        bookBtn.addActionListener(e ->
        {
            if(bookingDBO.isAlreadyBooked(user.getUser_id(), s.getSchedule_id()))
            {
                JOptionPane.showMessageDialog(null, "You're already booked for this class!");
                bookBtn.setText("Booked");
                bookBtn.setEnabled(false);
            }
            else
            {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to book this class?", "Book Class", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showMessageDialog(null, "Class has been booked.");
                    bookingDBO.addBooking(user.getUser_id(), s.getSchedule_id());
                    bookBtn.setText("Booked");
                    bookBtn.setEnabled(false);
                } else
                {
                    JOptionPane.showMessageDialog(null, "No booking has been made.");
                }
            }

        });

        bookBtn.setBackground(new Color(200, 255, 0));
        bookBtn.setForeground(Color.BLACK);
        bookBtn.setFocusPainted(false);
        bookBtn.setBorderPainted(false);
        return bookBtn;
    }
}