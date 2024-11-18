package com.mfu.news_activity.Domain;

import java.sql.Blob;
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
public class News {

    @Id
    @Column(name = "News_Name") // Primary key
    private String name;

    @Column(name = "News_Description")
    private String description;

    @Column(name = "News_Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date")
    private LocalDate Date;

    @Column(name = "News_Time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time")
    private LocalTime Time;

    @Column(name = "News_Status")
    private String status;

    @Column(name = "News_Picture")
    private Blob picture;

    @ManyToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "Staff_Username") 
    private Staff staff;

    @ManyToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "Student_affairs_Username")
    private StudentAffairs studentAffairs;

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

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
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
