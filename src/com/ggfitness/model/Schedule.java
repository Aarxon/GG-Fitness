package com.ggfitness.model;

import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Schedule
{
    private int schedule_id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String trainerName;
    private String className;


    public Schedule(int schedule_id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, String trainerName, String className)
    {
        this.schedule_id = schedule_id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainerName = trainerName;
        this.className = className;
    }

    public int getSchedule_id()
    {
        return schedule_id;
    }
    public String getTrainerName()
    {
        return trainerName;
    }
    public String getClassName()
    {
        return className;
    }
    public LocalTime getStartTime()
    {
        return startTime;
    }
    public LocalTime getEndTime()
    {
        return endTime;
    }

}
