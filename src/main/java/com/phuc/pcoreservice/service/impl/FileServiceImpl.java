package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.service.IFileService;
import com.phuc.pcoreservice.util.FileCommon;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileServiceImpl implements IFileService {

    @Override
    public ResponseEntity<?> downloadFile(String fileName) {
        FileCommon downloadUtil = new FileCommon();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
