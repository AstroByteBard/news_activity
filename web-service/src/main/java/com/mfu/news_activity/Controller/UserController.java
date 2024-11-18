package com.mfu.news_activity.Controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mfu.news_activity.Domain.Staff;
import com.mfu.news_activity.Domain.StudentAffairs;
import com.mfu.news_activity.Domain.LoginModel.ResetPassword;
import com.mfu.news_activity.Domain.LoginModel.ResetUsername;
import com.mfu.news_activity.Repository.StaffRepository;
import com.mfu.news_activity.Repository.StudentRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private StaffRepository StaffRepo;

    @Autowired
    private StudentRepository StudentRepo;

    @GetMapping("/staff")
    public ResponseEntity<List<Staff>> GetStaff(){
        return new ResponseEntity<>(StaffRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentAffairs>> GetStudent(){
        return new ResponseEntity<>(StudentRepo.findAll(),HttpStatus.OK);
    }

    
    

    // Get Picture

    @GetMapping("/{id}/picture")
    public ResponseEntity<byte[]> getPicture(@PathVariable String id) {
        try {
            List<Staff> staffList = StaffRepo.findByStaffId(id);
            if (!staffList.isEmpty()) {
    
                Blob blob = staffList.get(0).getPicture();
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
    
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            }

            List<StudentAffairs> studentList = StudentRepo.findByStudentAffairsId(id);
            if (!studentList.isEmpty()) {
    
                Blob blob = studentList.get(0).getPicture();
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
    
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            }
    
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    
    // Post Picture
    
    @PostMapping("/{id}/picture")
    public ResponseEntity<?> postPicture(@PathVariable String id, @RequestPart("picture") MultipartFile file) {
        try {

            Blob blob = new SerialBlob(file.getBytes());
            
            List<Staff> staffList = StaffRepo.findByStaffId(id);
            if (!staffList.isEmpty()) {
                Staff staff = staffList.get(0);
                staff.setPicture(blob);
                StaffRepo.save(staff);
                return new ResponseEntity<>(staff, HttpStatus.OK);
            }

            List<StudentAffairs> studentList = StudentRepo.findByStudentAffairsId(id);
            if (!studentList.isEmpty()) {
                StudentAffairs student = studentList.get(0);
                student.setPicture(blob);
                StudentRepo.save(student);
                return new ResponseEntity<>(student, HttpStatus.OK);
            }

            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }   catch(IOException | SQLException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    
    // Edit Username

    @PutMapping("/username")
    public ResponseEntity<?>  UsernameChange(@RequestBody ResetUsername request) {
        List<Staff> staffList = StaffRepo.findByStaffId(request.getId());
        if (staffList.size() > 0){
            Staff staff = staffList.get(0);
            staff.setUsername(request.getUsername());
            StaffRepo.save(staff);
            return new ResponseEntity<>(staffList, HttpStatus.OK);
        }
        List<StudentAffairs> studentList = StudentRepo.findByStudentAffairsId(request.getId());
        if (studentList.size() > 0) {
            StudentAffairs student = studentList.get(0);
            student.setUsername(request.getUsername());
            StudentRepo.save(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Edit Password

    @PutMapping("/password")
    public ResponseEntity<?>  PasswordChange(@RequestBody ResetPassword request) {
        List<Staff> staffList = StaffRepo.findByStaffId(request.getId());
        if (staffList.size() > 0) {
            Staff staff = staffList.get(0); // get information out
            staff.setPassword(request.getPassword());
            StaffRepo.save(staff);
            return new ResponseEntity<>(staffList, HttpStatus.OK);

        }
        List<StudentAffairs> studentList = StudentRepo.findByStudentAffairsId(request.getId());
        if (studentList.size() > 0) {
            StudentAffairs student = studentList.get(0);
            student.setPassword(request.getPassword());
            StudentRepo.save(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
}
