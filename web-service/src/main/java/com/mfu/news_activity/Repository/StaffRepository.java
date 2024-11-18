package com.mfu.news_activity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfu.news_activity.Domain.Staff;
import java.util.List;


public interface StaffRepository extends JpaRepository<Staff,String> {
    List<Staff> findByStaffId(String staffId);
    List<Staff> findByUsername(String username);
    List<Staff> findAll();
}