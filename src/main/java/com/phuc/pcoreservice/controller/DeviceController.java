package com.phuc.pcoreservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("device")
public class DeviceController {

    @PostMapping(value = "get-list")
    public ResponseEntity<?> getListDevice(){
        return null;
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> createDevice(){
        return null;
    }
}
