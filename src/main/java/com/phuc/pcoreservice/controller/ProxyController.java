package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.service.IProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "api/public/proxy")
public class ProxyController {

    @Autowired
    private IProxyService proxyService;

    @PostMapping(value = "/import-proxy", consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> importProxy(@RequestParam("file") MultipartFile file){
        return proxyService.importProxy(file);
    }

}
