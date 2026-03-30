package com.ggfitness.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ggfitness.model.*;
import java.time.LocalDate;

public class BookingDBO
{

    DatabaseConnection dbcon = new DatabaseConnection();

    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet rs = null;
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

    public boolean isAlreadyBooked(int user_id, int schedule_id)
    {

        try
        {
            connection = dbcon.startConnection();
            pstat = connection.prepareStatement("SELECT * FROM Bookings WHERE user_id = ? AND schedule_id = ?");
            pstat.setInt(1, user_id);
            pstat.setInt(2, schedule_id);

            rs = pstat.executeQuery();

            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }



}
