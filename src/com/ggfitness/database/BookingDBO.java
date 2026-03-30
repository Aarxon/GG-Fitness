package com.ggfitness.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import com.ggfitness.model.*;
import java.time.LocalDate;

public class BookingDBO
{

    DatabaseConnection dbcon = new DatabaseConnection();

    Connection connection = null;
    PreparedStatement pstat = null;
    User user;
    Schedule schedule;

    public void addBooking(int user_id, int schedule_id)
    {
        try
        {
            connection = dbcon.startConnection();
            pstat = connection.prepareStatement ("INSERT INTO Bookings (user_id, schedule_id, booking_date) VALUES (?, ?, ?) ");
            pstat.setInt(1, user_id);
            pstat.setInt(2, schedule_id);
            pstat.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            pstat.executeUpdate();
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }
        finally
        {
            dbcon.closeConnection();
        }
    }


}
