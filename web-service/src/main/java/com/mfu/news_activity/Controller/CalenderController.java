package com.mfu.news_activity.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mfu.news_activity.Domain.Calendar;
import com.mfu.news_activity.Repository.CalendarRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CalenderController {

    @Autowired
    private CalendarRepository CalendarRepo;

    // Get Information

    @GetMapping("/calender")
    public ResponseEntity<List<Calendar>> getCalendar() {
        return new ResponseEntity<>(CalendarRepo.findAll(), HttpStatus.OK);
    }

    // Add Calendar information

    @PostMapping("/calender")
    public ResponseEntity<List<Calendar>> postCalendar(@RequestBody Calendar Post) {
        CalendarRepo.save(Post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete information 

    @DeleteMapping("/calender/{topic}")
    public ResponseEntity<Calendar> DeleteCalendar(@PathVariable String topic){
        List<Calendar> Calendar = CalendarRepo.findByTopic(topic);

        if (!Calendar.isEmpty()){
            Calendar CalendarInfo = Calendar.get(0);
            CalendarRepo.delete(CalendarInfo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}