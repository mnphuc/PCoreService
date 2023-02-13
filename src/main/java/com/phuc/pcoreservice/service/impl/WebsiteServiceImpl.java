package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.repository.IWebsiteRepo;
import com.phuc.pcoreservice.service.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class WebsiteServiceImpl implements IWebsiteService {
    @Autowired
    private IWebsiteRepo websiteRepo;

    @Override
    public ResponseEntity<String> getUrlWebsite() {
        return ResponseEntity.ok().body(websiteRepo.getRandomUrl());
    }
}
