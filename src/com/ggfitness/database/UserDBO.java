package com.ggfitness.database;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ggfitness.gui.MainWindow;
import com.ggfitness.model.User;
import org.apache.commons.validator.routines.EmailValidator;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserDBO
{
    Scanner input = new Scanner(System.in);
    DatabaseConnection dbcon = new DatabaseConnection();
    EmailValidator emailValidator = EmailValidator.getInstance();

    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet resultSet = null;

    public UserDBO()
    {

    }

    public void createNewUser()
    {
        int i = 0;
        String firstName;
        String lastName;
        String email;
        String password;
        boolean isAddValid;
        int phone;

        System.out.println("Enter your first name: ");
        firstName = input.next();
        System.out.println("Enter your last name: ");
        lastName = input.next();
        System.out.println("Enter your email: ");
        email = input.next();
        System.out.println("Enter your password: ");
        password = input.next();
        System.out.println("Enter your phone number: ");
        phone = input.nextInt();

        isAddValid = emailValidator.isValid(email);
        if (!isAddValid)
        {
            System.out.println("Email is not in a valid format");
            return;
        }
        else
        {
            User user = new User(firstName, lastName, email, password, phone);

            try
            {
                connection = dbcon.startConnection();
                pstat = connection.prepareStatement("INSERT INTO Users (first_name, last_name, email, password, phone_number) VALUES (?,?,?,?,?) ");

                pstat.setString(1, firstName);
                pstat.setString(2, lastName);
                pstat.setString(3, email);
                pstat.setString(4, passwordHash(password));
                pstat.setInt(5, phone);

                i = pstat.executeUpdate();
                System.out.println(i + " Record created");
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


    public void loginUser()
    {
        connection = dbcon.startConnection();
        MainWindow mw = new MainWindow();

        String email;
        String password;

        System.out.println("Enter your email: ");
        email = input.next();
        System.out.println("Enter your password: ");
        password = input.next();

        try
        {
            String retrieve = "SELECT * FROM  Users WHERE email = ? AND password = ?";

            pstat = connection.prepareStatement(retrieve);
            pstat.setString(1, email);
            pstat.setString(2, password);

            resultSet = pstat.executeQuery();

            if(resultSet.next())
            {
                System.out.println("Welcome " + " " + resultSet.getString("first_name"));
                mw.userScreen();
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
        connection = dbcon.closeConnection();
    }

    //Method to hash password
    public String passwordHash(String password)
    {
        final String hashPassword =  BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return hashPassword;
    }
}
