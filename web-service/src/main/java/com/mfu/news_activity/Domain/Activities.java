package com.mfu.news_activity.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Activities {

    @Id
    @Column(name = "Activities_Name") // Primary key
    private String name;

    @Column(name = "Activities_Description")
    private String description;

    @Column(name = "Activities_Date") 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date")
    private LocalDate Date;

    @Column(name = "Activities_Time_Start") 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time_start")
    private LocalTime Time_start;

    @Column(name = "Activities_Time_End") 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonProperty("time_end")
    private LocalTime Time_end;

    @Column(name = "Activities_Location")
    private String location;

    @Column(name = "Activities_Type")
    private String type;

    @Column(name = "Activities_School")
    private String school;

    @Column(name = "MFU_Character") 
    private String score_character;

    @Column(name = "ScholarShip_Hour")
    private String score_scholarship;

    @Column(name = "StudentLoan_Hour")
    private String score_studentloan;

    @Column(name = "Activities_Status")
    private String status;

    @Column(name = "Activities_Picture")
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
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
