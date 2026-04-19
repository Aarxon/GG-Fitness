package com.ggfitness.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ggfitness.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDBO
{

    DatabaseConnection dbcon = new DatabaseConnection();

    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet rs = null;

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

    public List<Booking> getBookingsByUser(int user_id)
    {
        List<Booking> bookings = new ArrayList<>();
        try
        {
            connection = dbcon.startConnection();
            pstat = connection.prepareStatement(
                    "SELECT b.booking_id, b.schedule_id, c.name, s.day, s.start_time, s.end_time, t.first_name, t.last_name " +
                            "FROM Bookings b " +
                            "JOIN Schedule s ON b.schedule_id = s.schedule_id " +
                            "JOIN Classes c ON s.class_id = c.class_id " +
                            "JOIN Trainers t ON s.trainer_id = t.trainer_id " +
                            "WHERE b.user_id = ?"
            );
            pstat.setInt(1, user_id);
            rs = pstat.executeQuery();
            while(rs.next())
            {
                Booking booking = new Booking(
                        rs.getInt("schedule_id"),
                        rs.getInt("booking_id"),
                        rs.getString("name"),
                        rs.getString("day"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("first_name") + " " + rs.getString("last_name")
                );
                bookings.add(booking);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            dbcon.closeConnection();
        }
        return bookings;
    }

    // Cancels a booking by deleting the record
    public void manageBooking(int user_id, int schedule_id)
    {
        try
        {
            connection = dbcon.startConnection();
            pstat = connection.prepareStatement("DELETE FROM Bookings WHERE user_id = ? AND schedule_id = ?");
            pstat.setInt(1, user_id);
            pstat.setInt(2, schedule_id);
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
