package com.example.ebeautycentar.dto;

import java.time.LocalDate;

public class DailyStatsDto {
    private LocalDate date;
    private int pageviews;
    private int bookings;

    public DailyStatsDto(LocalDate date, int pageviews, int bookings) {
        this.date = date;
        this.pageviews = pageviews;
        this.bookings = bookings;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPageviews() {
        return pageviews;
    }

    public int getBookings() {
        return bookings;
    }
}
