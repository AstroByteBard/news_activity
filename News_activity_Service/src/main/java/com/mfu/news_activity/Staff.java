package com.mfu.news_activity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String Staff_Id ;
    private String Staff_Username ;
    private String Staff_Password ;
    
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getStaff_Id() {
        return Staff_Id;
    }
    public void setStaff_Id(String staff_Id) {
        Staff_Id = staff_Id;
    }
    public String getStaff_Username() {
        return Staff_Username;
    }
    public void setStaff_Username(String staff_Username) {
        Staff_Username = staff_Username;
    }
    public String getStaff_Password() {
        return Staff_Password;
    }
    public void setStaff_Password(String staff_Password) {
        Staff_Password = staff_Password;
    }

    
}
