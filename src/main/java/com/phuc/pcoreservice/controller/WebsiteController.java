package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.service.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/website")
public class WebsiteController {

    @Autowired
    private IWebsiteService websiteService;

    @GetMapping(value = "/random-url")
    public ResponseEntity<?> getRandomUrlWebsite(){
        return websiteService.getUrlWebsite();
    }
}
