package com.ggfitness.model;

import java.time.LocalDate;

public class Booking
{
    private int booking_id;
    private int user_id;
    private int schedule_id;
    private LocalDate bookingDate;

    public Booking(int user_id, int schedule_id, LocalDate bookingDate) {
        this.user_id = user_id;
        this.schedule_id = schedule_id;
        this.bookingDate = bookingDate;
    }
}