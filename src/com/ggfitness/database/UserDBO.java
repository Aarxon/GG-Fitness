package com.ggfitness.database;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ggfitness.model.User;
import org.apache.commons.validator.routines.EmailValidator;
import at.favre.lib.crypto.bcrypt.BCrypt;
import javax.swing.*;

public class UserDBO
{
    DatabaseConnection dbcon = new DatabaseConnection();
    EmailValidator emailValidator = EmailValidator.getInstance();

    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet resultSet = null;

    public UserDBO()
    {

    }

    public void createNewUser(String firstName, String lastName, String email, String password, String phoneNumber)
    {
        int i = 0;
        boolean isAddValid;

        isAddValid = emailValidator.isValid(email);
        if (!isAddValid)
        {
            JOptionPane.showMessageDialog(null, "Invalid Email Address");
        }
        else
        {
            User user = new User(firstName, lastName, email, password, phoneNumber);

            try
            {
                connection = dbcon.startConnection();
                pstat = connection.prepareStatement("INSERT INTO Users (first_name, last_name, email, password, phone_number) VALUES (?,?,?,?,?) ");

                pstat.setString(1, firstName);
                pstat.setString(2, lastName);
                pstat.setString(3, email);
                pstat.setString(4, passwordHash(password));
                pstat.setString(5, phoneNumber);

                i = pstat.executeUpdate();
                JOptionPane.showMessageDialog(null, i + " Record created");
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


    public boolean loginUser(String email, String password)
    {
        connection = dbcon.startConnection();

        try
        {
            String retrieve = "SELECT password FROM Users WHERE email = ?";

            pstat = connection.prepareStatement(retrieve);

            pstat.setString(1, email);
            resultSet = pstat.executeQuery();

            if(resultSet.next())
            {
                String passwordHash = resultSet.getString("password");

                if(verifyPassword(password, passwordHash))
                {
                    JOptionPane.showMessageDialog(null, "Welcome " + email);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Password");
                }
            }
            else
            {
                System.out.println("Invalid email or password");
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
           dbcon.closeConnection();
        }
    }

    //Method to hash password
    public String passwordHash(String password)
    {
        final String hashPassword =  BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return hashPassword;
    }


    public Boolean verifyPassword(String password, String hashPassword)
    {
       BCrypt.Result verify =  BCrypt.verifyer().verify(password.toCharArray(), hashPassword);
       return verify.verified;
    }

}
