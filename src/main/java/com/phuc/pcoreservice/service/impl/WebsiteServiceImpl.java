package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.repository.IWebsiteRepo;
import com.phuc.pcoreservice.service.IWebsiteService;
import com.phuc.pcoreservice.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class WebsiteServiceImpl implements IWebsiteService {
    @Autowired
    private IWebsiteRepo websiteRepo;

    @Override
    public ResponseEntity<String> getUrlWebsite() {
        return ResponseEntity.ok().body(websiteRepo.getRandomUrl());
    }

    @Override
    public ResponseEntity<String> getRandomWebsite(HttpServletRequest request) {
        String ip = HttpUtils.getRequestIP(request);
        return ResponseEntity.ok().body(websiteRepo.getRandomWebsite(ip));
    }
}
