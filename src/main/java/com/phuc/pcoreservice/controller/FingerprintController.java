package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.service.IProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "api/public/fingerprint")
public class FingerprintController {

    private static final Logger logger = LoggerFactory.getLogger(FingerprintController.class);

    @Autowired
    private IProfileService profileService;



//    @PostMapping(value = "save")
//    public ResponseEntity<?> saveFingerprint(@RequestBody String jsonFingerprint){
//        logger.info(jsonFingerprint);
//        return null;
//    }

    @GetMapping(value = "get")
    public ResponseEntity<?> getFingerprint(){

        return profileService.getFingerprint();
    }

}
