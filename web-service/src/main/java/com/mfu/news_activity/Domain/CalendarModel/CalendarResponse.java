package com.mfu.news_activity.Domain.CalendarModel;

import java.util.List;

import com.mfu.news_activity.Domain.Calendar;

public class CalendarResponse {
    
    private List<Calendar> calender;
    private List<Calendar_new> news;
    private List<Calender_activities> activities;

    public List<Calendar> getCalender() {
        return calender;
    }
    public void setCalender(List<Calendar> calender) {
        this.calender = calender;
    }
    public List<Calendar_new> getNews() {
        return news;
    }
    public void setNews(List<Calendar_new> news) {
        this.news = news;
    }
    public List<Calender_activities> getActivities() {
        return activities;
    }
    public void setActivities(List<Calender_activities> activities) {
        this.activities = activities;
    }

}
