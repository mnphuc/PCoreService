package com.phuc.pcoreservice.service;

import org.springframework.http.ResponseEntity;

public interface IWebsiteService {
    ResponseEntity<String> getUrlWebsite();
}
