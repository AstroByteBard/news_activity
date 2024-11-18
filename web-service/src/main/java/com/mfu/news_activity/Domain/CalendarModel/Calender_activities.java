package com.mfu.news_activity.Domain.CalendarModel;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Calender_activities {
    
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date")
    private LocalDate Date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time_start")
    private LocalTime Time_start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time_end")
    private LocalTime Time_end;

    public Calender_activities (String name ,LocalDate Date, LocalTime Time_start , LocalTime Time_end){
        this.name = name;
        this.Date = Date;
        this.Time_start = Time_start;
        this.Time_end = Time_end;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalTime getTime_start() {
        return Time_start;
    }

    public void setTime_start(LocalTime time_start) {
        Time_start = time_start;
    }

    public LocalTime getTime_end() {
        return Time_end;
    }

    public void setTime_end(LocalTime time_end) {
        Time_end = time_end;
    }
}
