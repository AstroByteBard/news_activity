package com.mfu.news_activity.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfu.news_activity.Domain.Activities;
import com.mfu.news_activity.Domain.News;
import com.mfu.news_activity.Domain.ActivityModel.Activity_info;
import com.mfu.news_activity.Repository.ActivitiesRepository;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ActivitiesController {
    
    @Autowired
    private ActivitiesRepository ActivityRepo;

    // Add Activity Information

    @PostMapping("/activity/post/info")
    public ResponseEntity<?> PostActivityInfo(@RequestBody Activity_info req){

        Activities ActivityInfo = new Activities();
        ActivityInfo.setName(req.getName());
        ActivityInfo.setDescription(req.getDescription());
        ActivityInfo.setDate(req.getDate());
        ActivityInfo.setTime_start(req.getTime_start());
        ActivityInfo.setTime_end(req.getTime_end());
        ActivityInfo.setLocation(req.getLocation());
        ActivityInfo.setType(req.getType());
        ActivityInfo.setSchool(req.getSchool());
        ActivityInfo.setScore_character(req.getScore_character());
        ActivityInfo.setScore_scholarship(req.getScore_scholarship());
        ActivityInfo.setScore_studentloan(req.getScore_studentloan());
        ActivityInfo.setStatus(req.getStatus());
        ActivityRepo.save(ActivityInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Add Activity Picture

    @PostMapping("/activity/post/{name}/picture")
    public ResponseEntity<?> PostActivityPicture(@PathVariable String name , @RequestPart("picture") MultipartFile File){

        List<Activities> activity = ActivityRepo.findByName(name);
        Activities ActivityInfo = activity.get(0);
        try{

            Blob blob = new SerialBlob(File.getBytes());

            ActivityInfo.setPicture(blob); 

            ActivityRepo.save(ActivityInfo);
        } catch(IOException | SQLException e){
            e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Search

    @GetMapping("/activity/search/{name}")
    public ResponseEntity<Activity_info> Search (@PathVariable String name){
        List<Activities> activity = ActivityRepo.findByName(name);

        if (!activity.isEmpty()){
            Activities ActivityInfo = activity.get(0);
            Activity_info Info_Activity = new Activity_info(
                ActivityInfo.getName(),
                ActivityInfo.getDescription(),
                ActivityInfo.getDate(),
                ActivityInfo.getTime_start(),
                ActivityInfo.getTime_end(),
                ActivityInfo.getLocation(),
                ActivityInfo.getType(),
                ActivityInfo.getSchool(), 
                ActivityInfo.getScore_character(),
                ActivityInfo.getScore_scholarship(),
                ActivityInfo.getScore_studentloan(),
                ActivityInfo.getStatus()
                );
            return new ResponseEntity<>(Info_Activity, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get News information Picture
    
    @GetMapping("/activity/{name}/picture")
    public ResponseEntity<byte[]> getPicture(@PathVariable String name) {
        try {

            List<Activities> activity = ActivityRepo.findByName(name);
            if (activity == null || activity.get(0).getPicture() == null) {
                return ResponseEntity.notFound().build();
            }

            Blob blob = activity.get(0).getPicture();
            byte[] imageBytes = blob.getBytes(1, (int) blob.length());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // GetAll information

    @GetMapping("/activity")
     public ResponseEntity<List<Activity_info>> getNews() {
        List<Activities> activitiesList = ActivityRepo.findAll();
        List<Activity_info> activity_Entity = new ArrayList<>();

        for (Activities ActivityInfo : activitiesList) {
            Activity_info Entity = new Activity_info(
                ActivityInfo.getName(),
                ActivityInfo.getDescription(),
                ActivityInfo.getDate(),
                ActivityInfo.getTime_start(),
                ActivityInfo.getTime_end(),
                ActivityInfo.getLocation(),
                ActivityInfo.getType(),
                ActivityInfo.getSchool(), 
                ActivityInfo.getScore_character(),
                ActivityInfo.getScore_scholarship(),
                ActivityInfo.getScore_studentloan(),
                ActivityInfo.getStatus()
            );
            activity_Entity.add(Entity);
        }

        return ResponseEntity.ok(activity_Entity);
    }

    // Get information

    @GetMapping("/activity/{name}")
    public ResponseEntity<Activity_info> GetActivity(@PathVariable String name){
        List<Activities> Activity = ActivityRepo.findByName(name);

        if (!Activity.isEmpty()){
            Activities ActivityEntity = Activity.get(0);
            Activity_info Activity_Info = new Activity_info(
                ActivityEntity.getName(),
                ActivityEntity.getDescription(),
                ActivityEntity.getDate(),
                ActivityEntity.getTime_start(),
                ActivityEntity.getTime_end(),
                ActivityEntity.getLocation(),
                ActivityEntity.getType(),
                ActivityEntity.getSchool(), 
                ActivityEntity.getScore_character(),
                ActivityEntity.getScore_scholarship(),
                ActivityEntity.getScore_studentloan(),
                ActivityEntity.getStatus()
                );

            return new ResponseEntity<>(Activity_Info,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }  

    // Edit Information

    @PutMapping("/activity/{name}/information")
    public ResponseEntity<Activities> Edit_Information(@PathVariable String name , @RequestBody Activity_info req) {
        List<Activities> ActivitiesInfo = ActivityRepo.findByName(name);
        
        ActivityRepo.delete(ActivitiesInfo.get(0));

        Activities ActivitiesEntity = ActivitiesInfo.get(0);
        ActivitiesEntity.setName(req.getName());
        ActivitiesEntity.setDescription(req.getDescription());
        ActivitiesEntity.setDate(req.getDate());
        ActivitiesEntity.setTime_start(req.getTime_start());
        ActivitiesEntity.setTime_end(req.getTime_end());
        ActivitiesEntity.setLocation(req.getLocation());
        ActivitiesEntity.setType(req.getType());
        ActivitiesEntity.setSchool(req.getSchool());
        ActivitiesEntity.setScore_character(req.getScore_character());
        ActivitiesEntity.setScore_scholarship(req.getScore_scholarship());
        ActivitiesEntity.setScore_studentloan(req.getScore_studentloan());
        ActivitiesEntity.setStatus("Pending"); 
        ActivityRepo.save(ActivitiesEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Edit Picture

    @PutMapping("/activity/{name}/Picture")
    public ResponseEntity<?> updatePicture(@PathVariable String name, @RequestPart("picture") MultipartFile newFile) {
        try {
            List<Activities> ActivityInfo = ActivityRepo.findByName(name);

            Activities ActivityEntity = ActivityInfo.get(0);

            Blob ActivityBlob = new SerialBlob(newFile.getBytes());
            ActivityEntity.setPicture(ActivityBlob);

            ActivityRepo.save(ActivityEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Edit Status Approved

    @PutMapping("/activity/{name}/Approved")
    public ResponseEntity<News> ChangeStatus_Approved (@PathVariable String name) {
        List<Activities> ActivityInfo = ActivityRepo.findByName(name);
        Activities ActivityEntity = ActivityInfo.get(0);
        ActivityEntity.setStatus("Approved");
        ActivityRepo.save(ActivityEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Edit Status Rejected

    @PutMapping("/activity/{name}/Rejected")
    public ResponseEntity<News> ChangeStatus_Rejected (@PathVariable String name) {
        List<Activities> ActivityInfo = ActivityRepo.findByName(name);
        Activities ActivityEntity = ActivityInfo.get(0);
        ActivityEntity.setStatus("Rejected");
        ActivityRepo.save(ActivityEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // Delete information by name

    @DeleteMapping("/activity/{name}")
    public ResponseEntity<Activity_info> Delete(@PathVariable String name){
        List<Activities> Activity = ActivityRepo.findByName(name);

        if (!Activity.isEmpty()){
            Activities activityEntity = Activity.get(0);
            ActivityRepo.delete(activityEntity);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
