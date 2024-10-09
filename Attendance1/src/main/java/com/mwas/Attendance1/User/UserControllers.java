package com.mwas.Attendance1.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllers {
    @Autowired
    private UserServices userservices;
    @PostMapping("/register")
    public AllUsers Signup(@RequestBody AllUsers users){
        return userservices.addUsers(users);
    }
    @GetMapping("/admin/home")
    public String adminHome(){
        return "admin home";
    }
    @GetMapping("/user/home")
    public String userHome(){
        return "user home";
    }
}
