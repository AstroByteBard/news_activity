package com.mfu.news_activity.Domain;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Student_Affairs")
public class StudentAffairs {

    @Id
    @Column(name = "Student_affairs_id") // Primary Key
    private String studentAffairsId;

    @Column(name = "Student_affairs_Username")
    private String username;

    @Column(name = "Student_affairs_Password")
    private String password;

    @JsonIgnore
    @Column(name = "Student_affairs_Picture")
    private Blob picture;

    public String getStudentAffairsId() {
        return studentAffairsId;
    }

    public void setStudentAffairsId(String studentAffairsId) {
        this.studentAffairsId = studentAffairsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    
}
