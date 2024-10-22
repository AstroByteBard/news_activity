package com.mfu.news_activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController; 
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class StaffController {

    @Autowired
    private StaffRepository StaffRepo;

    @GetMapping("/Staff")
    public List<Staff> findAll() {
        return StaffRepo.findAll();
    }

    @PostMapping("/Staff")
    public Staff addstaff(@RequestBody Staff staff){
        return StaffRepo.save(staff);
    }

    @PutMapping("/Staff/{id}")
    public Staff edit(@PathVariable Long id, @RequestBody Staff staff) {
        staff.setId(id);
        return StaffRepo.save(staff);
    }
}

    
