package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.repository.IGmailRepo;
import com.phuc.pcoreservice.service.IGmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GmailServiceImpl implements IGmailService {

    @Autowired
    private IGmailRepo gmailRepo;


    @Override
    public ResponseEntity<?> getGmailCreateProfile() {
        return ResponseEntity.ok().body(gmailRepo.getGmail());
    }
}
