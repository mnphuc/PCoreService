package com.phuc.pcoreservice.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IWebsiteService {
    ResponseEntity<String> getUrlWebsite();

    ResponseEntity<String> getRandomWebsite(HttpServletRequest request);
}
