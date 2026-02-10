package com.ggfitness.database;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ScheduleDBO
{
    Scanner input = new Scanner(System.in);
    DatabaseConnection dbcon = new DatabaseConnection();

    Connection connection = null;
    PreparedStatement pstat = null;

    public void createNewUser()
    {
        int i = 0;
        DayOfWeek day;
        LocalTime startTime;
        LocalTime endTime;
        String trainerName = " ";
        String className = " ";

        System.out.println("Pick a day of the week: "
                + "\n 1. Monday"
                + "\n 2. Tuesday"
                + "\n 3. Wednesday"
                + "\n 4. Thursday"
                + "\n 5. Friday"
                + "\n 6. Saturday"
                + "\n 7. Sunday");
        int choice = input.nextInt();
        day = DayOfWeek.of(choice);

        System.out.println("Pick a class start time: "
                + "\n 1. 13:00 (1 PM)"
                + "\n 2. 14:00 (2 PM)"
                + "\n 3. 15:00 (3 PM)"
                + "\n 4. 16:00 (4 PM)"
                + "\n 5. 17:00 (5 PM)"
                + "\n 6. 18:00 (6 PM)"
                + "\n 7. 19:00 (7 PM)");
        choice = input.nextInt();
        startTime = LocalTime.of(12 + choice, 0);
        endTime = startTime.plusHours(1);

        System.out.println("Pick a trainer "
                + "\n 1. Marcus Steele");
        choice = input.nextInt();

        switch(choice)
        {
            case 1:
                trainerName = "Marcus Steele";
                break;
        }

        System.out.println("Pick a Class "
                        + "\n 1. Strength Training"
                        + "\n 2. Spinning/Cycling"
                        + "\n 3. HITT"
                        + "\n 4. Pilates"
                        + "\n 5. Yoga" );
                choice = input.nextInt();

        switch(choice)
        {
            case 1:
                className = "Strength Training";
                break;

            case 2:
                className = "Spinning/Cycling";
                break;

            case 3:
                className = "HITT";
                break;

            case 4:
                className = "Pilates";
                break;

            case 5:
                className = "Yog";
                break;
        }

            try {
                connection = dbcon.startConnection();
                pstat = connection.prepareStatement("INSERT INTO Schedule (day, start_time, end_time, trainer_name, class_name) VALUES (?,?,?,?,?)");

                pstat.setString(1, day.name());
                pstat.setString(2, startTime.toString());
                pstat.setString(3, endTime.toString());
                pstat.setString(4, trainerName);
                pstat.setString(5, className);

                i = pstat.executeUpdate();
                System.out.println(i + " Record created");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                dbcon.closeConnection();

            }

    }
}
