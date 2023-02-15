package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.service.IGmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private IGmailService gmailService;

    @GetMapping("/get-gmail")
    public ResponseEntity<?> getGmail(){
        return gmailService.getGmailCreateProfile();
    }
}
