package com.phuc.pcoreservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IGmailService {
    ResponseEntity<?> getGmailCreateProfile();

    ResponseEntity<?> getGmailByVps(HttpServletRequest request);

    ResponseEntity<?> updateStatusLogin(Integer gmailId);

    ResponseEntity<?> importGmail(MultipartFile file);

    ResponseEntity<?> getGmailFree();
}
