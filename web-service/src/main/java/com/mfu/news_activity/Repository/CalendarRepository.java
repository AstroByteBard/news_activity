package com.mfu.news_activity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.mfu.news_activity.Domain.Calendar;

public interface CalendarRepository extends JpaRepository <Calendar,String> {
    List<Calendar> findByTopic(String topic);
    List<Calendar> findAll();
    
} 