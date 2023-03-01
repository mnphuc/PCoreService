package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.service.IGmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/public/gmail")
public class GmailController {

    @Autowired
    private IGmailService gmailService;

    @PostMapping(value = "import-gmail")
    public ResponseEntity<?> importGmail(@RequestParam("file") MultipartFile file){
        return gmailService.importGmail(file);
    }

    @GetMapping(value = "get-total-gmail-active")
    public ResponseEntity<?> getTotalGmail(){
        return null;
    }

}
