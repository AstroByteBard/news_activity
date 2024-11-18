package com.mfu.news_activity.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mfu.news_activity.Domain.Staff;
import com.mfu.news_activity.Domain.StudentAffairs;
import com.mfu.news_activity.Domain.LoginModel.ResetPassword;
import com.mfu.news_activity.Domain.LoginModel.Login;
import com.mfu.news_activity.Domain.LoginModel.LoginResponse;
import com.mfu.news_activity.Repository.StaffRepository;
import com.mfu.news_activity.Repository.StudentRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class LoginController {

    @Autowired
    private StaffRepository StaffRepo;

    @Autowired
    private StudentRepository StudentRepo;
    
    // Login 

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody Login request) {
        List<Staff> staff = StaffRepo.findByStaffId(request.getId());
        if (staff.size() > 0) {
            Staff staffMember = staff.get(0);
            if (staffMember.getPassword().equals(request.getPassword())) {
                LoginResponse response = new LoginResponse(staffMember,"Staff");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<StudentAffairs> student = StudentRepo.findByStudentAffairsId(request.getId());
        if (student.size() > 0) {
            StudentAffairs studentMember = student.get(0);
            if (studentMember.getPassword().equals(request.getPassword())) {
                LoginResponse response = new LoginResponse(studentMember,"SDAD");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ResetPassword

    @PutMapping("/resetpassword")
    public ResponseEntity<?> ResetPassword(@RequestBody ResetPassword request) {
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
