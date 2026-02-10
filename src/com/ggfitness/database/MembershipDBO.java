package com.ggfitness.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ggfitness.model.User;
import org.apache.commons.validator.routines.EmailValidator;
import at.favre.lib.crypto.bcrypt.BCrypt;
import javax.swing.*;


public class MembershipDBO
{
    DatabaseConnection dbcon = new DatabaseConnection();

    Connection connection = null;
    PreparedStatement pstat = null;

    public MembershipDBO()
    {

    }

    public void createNewMembership(boolean status, String startDate, String expiryDate, String type)
    {
        int i = 0;

            try
            {
                connection = dbcon.startConnection();
                pstat = connection.prepareStatement("INSERT INTO Membership(status, startDate, expiryData, type) VALUES (?,?,?,?,?) ");

                pstat.setBoolean(1, status);
                pstat.setString(2, startDate);
                pstat.setString(3, expiryDate);
                pstat.setString(4, type);

                i = pstat.executeUpdate();
                JOptionPane.showMessageDialog(null, i + " Membership Added Successfully");
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


