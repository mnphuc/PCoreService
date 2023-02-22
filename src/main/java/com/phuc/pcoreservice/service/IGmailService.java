package com.phuc.pcoreservice.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IGmailService {
    ResponseEntity<?> getGmailCreateProfile();

    ResponseEntity<?> getGmailByVps(HttpServletRequest request);

    ResponseEntity<?> updateStatusLogin(Integer gmailId);
}
