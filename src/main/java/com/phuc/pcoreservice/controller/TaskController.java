package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.request.ProfileRequest;
import com.phuc.pcoreservice.service.IGmailService;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.service.IProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private IGmailService gmailService;

    @Autowired
    private IProxyService proxyService;

    @Autowired
    private IProfileService profileService;

    @GetMapping("/get-gmail")
    public ResponseEntity<?> getGmail(){
        return gmailService.getGmailCreateProfile();
    }

    @GetMapping(value = "/get-proxy")
    public ResponseEntity<?> getProxy(){
        return proxyService.getProxyV6();
    }

    @PostMapping(value = "/save-profile")
    public ResponseEntity<?> saveProfile(@ModelAttribute ProfileRequest request){
        return profileService.saveProfile(request);
    }
}
