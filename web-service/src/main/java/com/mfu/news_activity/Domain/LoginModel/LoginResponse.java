package com.mfu.news_activity.Domain.LoginModel;

public class LoginResponse {

    private Object user;
    private String role;

    public LoginResponse (Object user , String role){
        this.user = user;
        this.role = role;
    }

    public Object getUser() {
        return user;
    }
    public void setUser(Object user) {
        this.user = user;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    
}
