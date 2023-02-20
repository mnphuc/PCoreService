package com.phuc.pcoreservice.service;

import org.springframework.http.ResponseEntity;

public interface IFileService {
    ResponseEntity<?> downloadFile(String fileName);
}
