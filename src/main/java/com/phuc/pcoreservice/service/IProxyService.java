package com.phuc.pcoreservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IProxyService {
    ResponseEntity<?> getProxyV6();

    ResponseEntity<?> getStringProxyV6(HttpServletRequest request);
    ResponseEntity<?> importProxy(MultipartFile file);
}
