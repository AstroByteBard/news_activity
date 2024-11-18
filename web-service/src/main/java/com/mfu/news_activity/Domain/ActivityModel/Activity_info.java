package com.mfu.news_activity.Domain.ActivityModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalTime;

public class Activity_info {

    private String name;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date")
    private LocalDate Date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time_start")
    private LocalTime Time_start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time_end")
    private LocalTime Time_end;

    private String location;

    private String type;

    private String school;

    private String score_character;

    private String score_scholarship;

    private String score_studentloan;

    private String status;

    public Activity_info(String name, String description , LocalDate Date , LocalTime Time_start , LocalTime Time_end ,String location ,String type, String school, String score_character, String score_scholarship,String score_studentloan , String status ){
        this.name = name;
        this.description = description;
        this.location = location;
        this.Date = Date;
        this.Time_start = Time_start;
        this.Time_end = Time_end;
        this.type = type;
        this.school = school;
        this.score_character = score_character;
        this.score_scholarship = score_scholarship;
        this.score_studentloan = score_studentloan;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getScore_character() {
        return score_character;
    }

    public void setScore_character(String score_character) {
        this.score_character = score_character;
    }

    public String getScore_scholarship() {
        return score_scholarship;
    }

    public void setScore_scholarship(String score_scholarship) {
        this.score_scholarship = score_scholarship;
    }

    public String getScore_studentloan() {
        return score_studentloan;
    }

    public void setScore_studentloan(String score_studentloan) {
        this.score_studentloan = score_studentloan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
