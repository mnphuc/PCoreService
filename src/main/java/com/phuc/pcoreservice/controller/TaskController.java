package com.phuc.pcoreservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phuc.pcoreservice.dto.InfoGmailDTO;
import com.phuc.pcoreservice.request.FingerprintRequest;
import com.phuc.pcoreservice.request.ProfileRequest;
import com.phuc.pcoreservice.service.IGmailService;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.service.IProxyService;
import com.phuc.pcoreservice.service.IWebsiteService;
import com.phuc.pcoreservice.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Value("${application.header-ip-candidates}")
    private String[] headerCandidates;
    @Autowired
    private IGmailService gmailService;

    @Autowired
    private IProxyService proxyService;

    @Autowired
    private IProfileService profileService;
    @Autowired
    private IWebsiteService websiteService;
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


    @PostMapping(value = "save-fingerprint")
    public ResponseEntity<?> saveFingerprint(@ModelAttribute FingerprintRequest fingerprintRequest){
        return profileService.saveFingerprint(fingerprintRequest.getData());
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



}
