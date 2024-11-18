package com.mfu.news_activity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mfu.news_activity.Domain.News;
import java.util.List;

public interface NewsRepository extends JpaRepository<News,String>{
    List<News> findByName(String name);
    List<News> findAll();
}
