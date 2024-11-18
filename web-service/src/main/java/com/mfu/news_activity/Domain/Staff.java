package com.mfu.news_activity.Domain;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Staff")
public class Staff {

    @Id
    @Column(name = "Staff_id") // Primary Key
    private String staffId;

    @Column(name = "Staff_username")
    private String username;

    @Column(name = "Staff_password")
    private String password;

    @JsonIgnore
    @Column(name = "Staff_Picture")
    private Blob picture;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
