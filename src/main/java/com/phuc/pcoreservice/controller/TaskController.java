package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.payload.request.ConfigRunningVPSRequest;
import com.phuc.pcoreservice.payload.request.FingerprintRequest;
import com.phuc.pcoreservice.payload.request.ProfileRequest;
import com.phuc.pcoreservice.service.*;
import com.phuc.pcoreservice.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/public/task")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private IGmailService gmailService;
    @Autowired
    private IProxyService proxyService;

    @Autowired
    private IProfileService profileService;
    @Autowired
    private IWebsiteService websiteService;
    @Autowired
    private ITaskService taskService;

    @PostMapping(value = "/save-profile")
    public ResponseEntity<?> saveProfile(@ModelAttribute ProfileRequest request){
        return profileService.saveProfile(request);
    }

    @GetMapping(value = "get-profile")
    public ResponseEntity<?> getProfile(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return profileService.getProfile(request);
    }


    @GetMapping("/get-ip")
    public ResponseEntity<?> getIpClient(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return ResponseEntity.ok().body(HttpUtils.getRequestIP(request));
    }

    @GetMapping(value = "change-status-running/{id}")
    public ResponseEntity<?> changeStatusRunning(@PathVariable("id")Integer id){
        return profileService.updateStatusRunning(id);
    }

    @GetMapping(value = "get-gmail-txt")
    public ResponseEntity<?> getRandomText(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return gmailService.getGmailByVps(request);
    }

    @GetMapping(value = "get-random-website")
    public ResponseEntity<?> getRandomWebsite(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return websiteService.getRandomWebsite(request);
    }

    @GetMapping(value = "get-proxy-txt")
    public ResponseEntity<?> getRandomProxy(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return proxyService.getStringProxyV6(request);
    }

    @GetMapping(value = "update-status-gmail/{id}")
    public ResponseEntity<?> updateStatusGmail(@PathVariable("id")Integer gmailId) {
        return gmailService.updateStatusLogin(gmailId);
    }


    @PostMapping(value = "config-start-vps")
    public ResponseEntity<?> configRunningVPS(@RequestBody ConfigRunningVPSRequest request){
        return taskService.configVPSRunning(request);
    }


}
