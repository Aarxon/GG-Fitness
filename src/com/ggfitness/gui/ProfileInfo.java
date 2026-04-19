package com.ggfitness.gui;

import com.ggfitness.database.BookingDBO;
import com.ggfitness.database.MembershipDBO;
import com.ggfitness.model.Booking;
import com.ggfitness.model.Schedule;
import net.miginfocom.swing.MigLayout;
import com.ggfitness.model.User;
import com.ggfitness.database.UserDBO;

import javax.swing.*;
import java.awt.*;
import java.util.List;



public class ProfileInfo
{
    private final User user;
    private final MainWindow mainWindow;
    private JLabel bookingsLabel;

    public ProfileInfo(User user, MainWindow mainWindow)
    {
        this.user = user;
        this.mainWindow = mainWindow;
    }

    public JPanel profileHome()
    {
        MembershipDBO membershipDBO = new MembershipDBO();

        JPanel outer = new JPanel(new MigLayout("fill, insets 15, gap 10"));
        outer.setBackground(new Color(13, 13, 13));

        JLabel avatar = new JLabel("●");
        avatar.setFont(new Font("Arial", Font.PLAIN, 200));
        avatar.setForeground(new Color(0, 0, 0));

        JLabel profileLabel = new JLabel("Profile");
        profileLabel.setFont(new Font("Impact", Font.BOLD, 24));

        JLabel bookingsLabel = new JLabel("Manage Your Bookings");
        bookingsLabel.setFont(new Font("Impact", Font.BOLD, 24));

        JLabel contactInfoLabel = new JLabel("Contact Info");
        JLabel personalDetailsLabel = new JLabel("Personal Details");
        JLabel membershipLabel = new JLabel("Membership status:");

        JLabel membershipActive = new JLabel("Active");
        membershipActive.setForeground(new Color(200, 255, 0));

        JLabel membershipInactive= new JLabel("Inactive");
        membershipInactive.setForeground(new Color(255, 0, 0));


        JTextField nameField = new JTextField(20);
        nameField.setText(user.getFirstName() + " " + user.getlastName());
        nameField.setEditable(false);


        JTextField emailField = new JTextField(20);
        emailField.setText(user.getEmail());
        emailField.setEditable(false);

        JTextField numberField = new JTextField(20);
        numberField.setText(user.getPhone());
        numberField.setEditable(false);

        JButton cancelEditBtn = new JButton("Cancel");
        cancelEditBtn.setBackground(new Color(80, 80, 80));
        cancelEditBtn.setForeground(Color.WHITE);
        cancelEditBtn.setFocusPainted(false);
        cancelEditBtn.setBorderPainted(false);
        cancelEditBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelEditBtn.setVisible(false);

        JButton editBtn = new JButton("Edit");
        editBtn.setBackground(new Color(200, 255, 0));
        editBtn.setForeground(Color.BLACK);
        editBtn.setFocusPainted(false);
        editBtn.setBorderPainted(false);
        editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(200, 255, 0));
        saveBtn.setForeground(Color.BLACK);
        saveBtn.setFocusPainted(false);
        saveBtn.setBorderPainted(false);
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveBtn.setVisible(false);



        editBtn.addActionListener(e -> {
            nameField.setEditable(true);
            emailField.setEditable(true);
            numberField.setEditable(true);
            editBtn.setVisible(false);
            saveBtn.setVisible(true);
            cancelEditBtn.setVisible(true);
        });

        saveBtn.addActionListener(e ->
        {
            String[] nameParts = nameField.getText().split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            UserDBO userDBO = new UserDBO();
            try
            {
                System.out.println("Updating: " + firstName + " " + lastName + " " + emailField.getText() + " " + numberField.getText() + " " + user.getUser_id());
                userDBO.updateUser(firstName, lastName, emailField.getText(), numberField.getText(), user.getUser_id());
                JOptionPane.showMessageDialog(null, "Profile updated successfully!");
                nameField.setEditable(false);
                emailField.setEditable(false);
                numberField.setEditable(false);
                editBtn.setVisible(true);
                saveBtn.setVisible(false);
                cancelEditBtn.setVisible(false);
            } catch (IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        cancelEditBtn.addActionListener(e -> {
            nameField.setText(user.getFirstName() + " " + user.getlastName());
            emailField.setText(user.getEmail());
            numberField.setText(user.getPhone());
            nameField.setEditable(false);
            emailField.setEditable(false);
            numberField.setEditable(false);
            editBtn.setVisible(true);
            saveBtn.setVisible(false);
            cancelEditBtn.setVisible(false);
        });


        JPanel profileCard = new JPanel(new MigLayout("wrap, align center, insets 5 20, gap 10"));
        profileCard.setBackground(new Color(22, 22, 22));
        profileCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        profileCard.setPreferredSize(new Dimension(350, 800));

        JPanel bookingsCard = new JPanel(new MigLayout("wrap, align center, insets 5 20, gap 10"));
        bookingsCard.setBackground(new Color(22, 22, 22));
        bookingsCard.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));


        outer.add(new NavigationBar(user, mainWindow), "dock north, growx, h 80!");
        outer.add(profileCard, "aligny top");

        //Bookings
        JScrollPane scrollPane = new JScrollPane(bookingsCard);
        scrollPane.setPreferredSize(new Dimension(600, 800));
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(22, 22, 22));
        bookingsCard.add(bookingsLabel, "align center, wrap 20");
        populateBookings(bookingsCard, bookingsLabel);

        outer.add(scrollPane, "aligny top, growx, pushx");

        //Profile
        profileCard.add(profileLabel, "align center, wrap 20");

        //Profile Picture
        profileCard.add(avatar, "pushx, align center, wrap");


        //Membership Status
        profileCard.add(membershipLabel, "align center, wrap");

        if(membershipDBO.checkMembership(user.getUser_id()) == true)
        {
            profileCard.add(membershipActive, "align center, wrap 20");
        }
        else
        {
            profileCard.add(membershipInactive, "align center, wrap 20");
        }

        //Personal Details
        profileCard.add(personalDetailsLabel, "align center, wrap");
        profileCard.add(nameField, "align center, growy, wrap");

        //Contact Info
        profileCard.add(contactInfoLabel, "align center, wrap");
        profileCard.add(emailField, "align center, growy, wrap");
        profileCard.add(numberField, "align center, growy, wrap");

        //Buttons
        profileCard.add(editBtn, "align center, growx, wrap");
        profileCard.add(saveBtn, "align center, growx, wrap");
        profileCard.add(cancelEditBtn, "align center, growx, wrap");

        return outer;
    }

    private void populateBookings(JPanel card, JLabel bookingsLabel)
    {
        BookingDBO bookingDBO = new BookingDBO();
        List<Booking> bookings = bookingDBO.getBookingsByUser(user.getUser_id());
        for(Booking b : bookings)
        {
            JLabel classLabel = new JLabel(b.getClassName());
            classLabel.setForeground(Color.WHITE);
            classLabel.setFont(new Font("Arial", Font.BOLD, 14));

            JLabel dayLabel = new JLabel(b.getDay());
            dayLabel.setForeground(new Color(120, 120, 120));
            dayLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            JLabel timeLabel = new JLabel(b.getStartTime() + " - " + b.getEndTime());
            timeLabel.setForeground(new Color(120, 120, 120));
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            JButton cancelBtn = getCancelButton(b, bookingDBO, card, bookingsLabel);

            card.add(classLabel, "align center, wrap");
            card.add(dayLabel, "align center, wrap");
            card.add(timeLabel, "align center, wrap");
            card.add(cancelBtn, "w 150!, align center, wrap 15");

            JSeparator separator = new JSeparator();
            separator.setForeground(new Color(50, 50, 50));
            card.add(separator, "growx, wrap 10");
        }
    }

    private JButton getCancelButton(Booking b, BookingDBO bookingDBO, JPanel card, JLabel bookingsLabel) {
        JButton bookBtn = new JButton("Cancel");
        bookBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));


        bookBtn.addActionListener(e ->
        {

                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this class?", "Cancel Class", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION)
                {
                    System.out.println("Cancelling: user_id=" + user.getUser_id() + " schedule_id=" + b.getSchedule_id());
                    JOptionPane.showMessageDialog(null, "Class has been cancelled.");
                    bookingDBO.manageBooking(user.getUser_id(), b.getBooking_id());
                    // Clear and repopulate the card to reflect the cancelled booking
                    card.removeAll();
                    card.add(bookingsLabel, "align center, wrap 20");
                    populateBookings(card, bookingsLabel);
                    card.revalidate();
                    card.repaint();
                } else
                {
                    JOptionPane.showMessageDialog(null, "No changes have been made.");
                }

        });

        bookBtn.setBackground(new Color(255, 0, 0));
        bookBtn.setForeground(Color.BLACK);
        bookBtn.setFocusPainted(false);
        bookBtn.setBorderPainted(false);
        return bookBtn;
    }


}
