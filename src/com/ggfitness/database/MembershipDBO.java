package com.ggfitness.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.time.LocalDate;
import java.sql.ResultSet;


public class MembershipDBO
{
    DatabaseConnection dbcon = new DatabaseConnection();

    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet rs = null;

    public MembershipDBO()
    {

    }

    public void createNewMembership(int user_id, String type, int months)
    {
        try
        {
            connection = dbcon.startConnection();
            pstat = connection.prepareStatement("INSERT INTO Membership(status, startDate, expiryDate, type, user_id) VALUES (?,?,?,?,?)");
            pstat.setBoolean(1, true);
            pstat.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            pstat.setDate(3, java.sql.Date.valueOf(LocalDate.now().plusMonths(months)));
            pstat.setString(4, type);
            pstat.setInt(5, user_id);
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

    public boolean checkMembership(int user_id)
    {
        try
        {
            connection = dbcon.startConnection();
            pstat = connection.prepareStatement("Select * from Membership where user_id = ? ");
            pstat.setInt(1, user_id);

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
            e.printStackTrace();
        }
        finally
        {
            dbcon.closeConnection();
        }
        return false;
    }

}


