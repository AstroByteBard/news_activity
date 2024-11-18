package com.mfu.news_activity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfu.news_activity.Domain.StudentAffairs;
import java.util.List;


public interface StudentRepository extends JpaRepository<StudentAffairs,String> {
    List<StudentAffairs> findByStudentAffairsId(String studentAffairsId);
    List<StudentAffairs> findByUsername(String username);
    List<StudentAffairs> findAll();
}
