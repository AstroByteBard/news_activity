package com.mfu.news_activity.Domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Calendar {

    @Id
    @Column(name = "Calendar_Topic") // Primary key
    private String topic;

    @Column(name = "Calendar_Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date")
    private LocalDate Date;
    
    @Column(name = "Calendar_Time_Start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time_start")
    private LocalTime Time_start;
    
    @Column(name = "Calendar_Time_End")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time_end")
    private LocalTime Time_end;

    @Column(name = "Calendar_Location")
    private String Location;

    @ManyToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "Staff_Username") 
    private Staff staff;

    @ManyToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "Student_affairs_Username")
    private StudentAffairs studentAffairs;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public StudentAffairs getStudentAffairs() {
        return studentAffairs;
    }

    public void setStudentAffairs(StudentAffairs studentAffairs) {
        this.studentAffairs = studentAffairs;
    }
    
    
}