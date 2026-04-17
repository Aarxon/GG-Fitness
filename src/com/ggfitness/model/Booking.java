package com.ggfitness.model;


import java.time.LocalDate;

public class Booking
{
    private int booking_id;
    private int user_id;
    private int schedule_id;
    private LocalDate bookingDate;
    private String className;
    private String day;
    private String startTime;
    private String endTime;
    private String trainerName;

    public Booking(int user_id, int schedule_id, LocalDate bookingDate) {
        this.user_id = user_id;
        this.schedule_id = schedule_id;
        this.bookingDate = bookingDate;
    }

    public Booking(int booking_id, int schedule_id, String className, String day, String startTime, String endTime, String trainerName)
    {
        this.booking_id = booking_id;
        this.schedule_id = schedule_id;
        this.className = className;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainerName = trainerName;
    }

    public int getBooking_id()     { return booking_id; }
    public int getSchedule_id()    { return schedule_id; }
    public String getClassName()   { return className; }
    public String getDay()         { return day; }
    public String getStartTime()   { return startTime; }
    public String getEndTime()     { return endTime; }
    public String getTrainerName() { return trainerName; }
}