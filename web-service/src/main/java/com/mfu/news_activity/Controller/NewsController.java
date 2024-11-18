package com.mfu.news_activity.Controller;

import javax.sql.rowset.serial.SerialBlob;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;


import com.mfu.news_activity.Repository.NewsRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mfu.news_activity.Domain.News;
import com.mfu.news_activity.Domain.NewModel.Information_New;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepo;
    
    // Add News Information

    @PostMapping("/post/info")
    public ResponseEntity<?> PostNews(@RequestBody Information_New req ) {

            News news = new News();
            news.setName(req.getName());
            news.setDate(req.getDate());
            news.setTime(req.getTime());
            news.setDescription(req.getDescription());
            news.setStatus("Pending");                           

            newsRepo.save(news);
    
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Add News Picture

    @PostMapping("/post/{name}/picture")
    public ResponseEntity<?> PostActivityPicture(@PathVariable String name , @RequestPart("picture") MultipartFile File){
        
        List<News> news = newsRepo.findByName(name);
        News NewsInfo = news.get(0);
        try{

            Blob blob = new SerialBlob(File.getBytes());

            NewsInfo.setPicture(blob);

            newsRepo.save(NewsInfo);
        }catch(IOException | SQLException e){
            e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    

    // Search

    @GetMapping("/search/{name}")
    public ResponseEntity<Information_New> Search (@PathVariable String name){
        List<News> NewsShow = newsRepo.findByName(name);

        if (!NewsShow.isEmpty()){
            News newsEntity = NewsShow.get(0);
            Information_New News_information = new Information_New(newsEntity.getName(),newsEntity.getDescription(),newsEntity.getDate(),newsEntity.getTime(), newsEntity.getStatus());
            return new ResponseEntity<>(News_information, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get News information Picture

    @GetMapping("/{name}/picture")
    public ResponseEntity<byte[]> getPicture(@PathVariable String name) {
        try {

            List<News> newsList = newsRepo.findByName(name);
            if (newsList == null || newsList.get(0).getPicture() == null) {
                return ResponseEntity.notFound().build();
            }

            Blob blob = newsList.get(0).getPicture();
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

    @GetMapping
    public ResponseEntity<List<Information_New>> getNews() {
        List<News> newsList = newsRepo.findAll();
        List<Information_New> newsInfoList = new ArrayList<>();

        for (News newsEntity : newsList) {
            Information_New info = new Information_New(
                newsEntity.getName(),
                newsEntity.getDescription(),
                newsEntity.getDate(),
                newsEntity.getTime(),
                newsEntity.getStatus()
            );
            newsInfoList.add(info);
        }

        return ResponseEntity.ok(newsInfoList);
    }

    // Get information

    @GetMapping("/{name}")
    public ResponseEntity<Information_New> GetNews(@PathVariable String name){
        List<News> NewsShow = newsRepo.findByName(name);

        if (!NewsShow.isEmpty()){
            News newsEntity = NewsShow.get(0);
            Information_New News_information = new Information_New(newsEntity.getName(),newsEntity.getDescription(),newsEntity.getDate(),newsEntity.getTime(), newsEntity.getStatus());
            return new ResponseEntity<>(News_information, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Edit Information

    @PutMapping("/{name}/information")
    public ResponseEntity<News> Edit_Information(@PathVariable String name , @RequestBody Information_New req) {
        List<News> NewsInfo = newsRepo.findByName(name);
        
        newsRepo.delete(NewsInfo.get(0));

        News newsEntity = NewsInfo.get(0);
        newsEntity.setName(req.getName());
        newsEntity.setDescription(req.getDescription());
        newsEntity.setDate(req.getDate());
        newsEntity.setTime(req.getTime());
        newsEntity.setStatus("Pending"); 
        newsRepo.save(newsEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Edit Picture

    @PutMapping("/{name}/Picture")
    public ResponseEntity<?> updatePicture(@PathVariable String name, @RequestPart("picture") MultipartFile newFile) {
        try {
            List<News> newsInfo = newsRepo.findByName(name);

            News newsEntity = newsInfo.get(0);

            Blob newBlob = new SerialBlob(newFile.getBytes());
            newsEntity.setPicture(newBlob);

            newsRepo.save(newsEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Edit Status Approved

    @PutMapping("/{name}/Approved")
    public ResponseEntity<News> ChangeStatus_Approved (@PathVariable String name) {
        List<News> NewsInfo = newsRepo.findByName(name);
        News newsEntity = NewsInfo.get(0);
        newsEntity.setStatus("Approved");
        newsRepo.save(newsEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Edit Status Rejected

    @PutMapping("/{name}/Rejected")
    public ResponseEntity<News> ChangeStatus_Rejected (@PathVariable String name) {
        List<News> NewsInfo = newsRepo.findByName(name);
        News newsEntity = NewsInfo.get(0);
        newsEntity.setStatus("Rejected");
        newsRepo.save(newsEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete information by name

    @DeleteMapping("/{name}")
    public ResponseEntity<Information_New> Delete(@PathVariable String name){
        List<News> NewsShow = newsRepo.findByName(name);

        if (!NewsShow.isEmpty()){
            News newsEntity = NewsShow.get(0);
            newsRepo.delete(newsEntity);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}  
