package com.phuc.pcoreservice.controller;


import com.phuc.pcoreservice.service.IFileService;
import com.phuc.pcoreservice.util.FileCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/public")
public class FileController {

    @Autowired
    private IFileService fileService;

    @GetMapping("/download-file/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        return fileService.downloadFile(fileCode);
    }
}
