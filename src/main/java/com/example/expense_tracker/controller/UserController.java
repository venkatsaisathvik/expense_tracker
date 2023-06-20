package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.User;
import com.example.expense_tracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo ur;

    @PostMapping("/adduser")
    public ResponseEntity<Object> adduser(@RequestBody User u){
        ur.save(u);
        Map<String,String> m = new HashMap<String,String>();
        m.put("Success","User successfully created");
        return new ResponseEntity<Object>(m,HttpStatus.OK);
    }
}
