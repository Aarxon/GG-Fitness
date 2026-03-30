package com.ggfitness.database;

import com.ggfitness.model.Schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;


public class ScheduleDBO
{
    DatabaseConnection dbcon = new DatabaseConnection();

    Connection connection = null;
    PreparedStatement pstat = null;

    public List<Schedule> getScheduleByDay(String day)
    {
        List<Schedule> schedules = new ArrayList<>();

        try
        {
            connection = dbcon.startConnection();
            pstat = connection.prepareStatement("SELECT s.schedule_id, s.day, s.start_time, s.end_time, \n" +
                                                   "t.first_name, t.last_name, c.name as class_name\n" +
                                                   "FROM Schedule s\n" +
                                                   "JOIN Trainers t ON s.trainer_id = t.trainer_id\n" +
                                                   "JOIN Classes c ON s.class_id = c.class_id\n" +
                                                   "WHERE s.day = ?");
            pstat.setString(1, day);
            ResultSet rs = pstat.executeQuery();

            while(rs.next())
            {
                Schedule s = new Schedule(
                        rs.getInt("schedule_id"),
                        DayOfWeek.valueOf(rs.getString("day")),
                        LocalTime.parse(rs.getString("start_time")),
                        LocalTime.parse(rs.getString("end_time")),
                        rs.getString("first_name") + " " + rs.getString("last_name"),
                        rs.getString("class_name")
                );
                schedules.add(s);
            }


        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            dbcon.closeConnection();

        }

        return schedules;
    }

}
