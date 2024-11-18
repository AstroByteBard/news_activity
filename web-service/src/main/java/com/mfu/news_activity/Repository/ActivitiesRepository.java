package com.mfu.news_activity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.mfu.news_activity.Domain.Activities;

public interface ActivitiesRepository extends JpaRepository <Activities,String> {
    List<Activities> findByName(String name);
    List<Activities> findAll();
} 