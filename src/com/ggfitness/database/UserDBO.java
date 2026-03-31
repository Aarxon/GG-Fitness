package com.ggfitness.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ggfitness.model.User;
import org.apache.commons.validator.routines.EmailValidator;
import at.favre.lib.crypto.bcrypt.BCrypt;
import javax.swing.*;
import java.time.LocalDate;

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

    public void createNewUser(String firstName, String lastName, LocalDate dob, String email, String password, String phoneNumber)
    {
        boolean isAddValid;

        isAddValid = emailValidator.isValid(email);
        if (!isAddValid)
        {
            throw new IllegalArgumentException("Invalid Email Address");
        }
        else
        {
            try
            {
                connection = dbcon.startConnection();
                pstat = connection.prepareStatement("INSERT INTO Users (first_name, last_name, date_of_birth, email, password, phone_number) VALUES (?,?,?, ?,?,?) ");

                pstat.setString(1, firstName);
                pstat.setString(2, lastName);
                pstat.setDate(3, java.sql.Date.valueOf(dob));
                pstat.setString(4, email);
                pstat.setString(5, passwordHash(password));
                pstat.setString(6, phoneNumber);

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


    public User loginUser(String email, String password)
    {
        connection = dbcon.startConnection();

        try
        {
            String retrieve = "SELECT user_id, first_name, last_name, email, password, phone_number FROM Users WHERE email = ?";

            pstat = connection.prepareStatement(retrieve);

            pstat.setString(1, email);
            resultSet = pstat.executeQuery();

            if(resultSet.next())
            {
                String passwordHash = resultSet.getString("password");

                if(verifyPassword(password, passwordHash))
                {
                    User user = new User
                            (
                            resultSet.getInt("user_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("phone_number")
                    );
                    return user;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
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
        return null;
    }

    public void updateUser(String firstName, String lastName, String email, String phoneNumber, int user_id)
    {
        connection = dbcon.startConnection();

        try {

            boolean isAddValid;

            isAddValid = emailValidator.isValid(email);
            if (!isAddValid)
            {
                throw new IllegalArgumentException("Invalid Email Address");
            }
            else
            {
                String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_number = ? WHERE user_id = ?";

                pstat = connection.prepareStatement(query);

                pstat.setString(1, firstName);
                pstat.setString(2, lastName);
                pstat.setString(3, email);
                pstat.setString(4, phoneNumber);
                pstat.setInt(5, user_id);
                pstat.executeUpdate();
            }
        }
        catch (SQLException e)
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
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }


    public Boolean verifyPassword(String password, String hashPassword)
    {
       BCrypt.Result verify =  BCrypt.verifyer().verify(password.toCharArray(), hashPassword);
       return verify.verified;
    }

}
