package com.phuc.pcoreservice.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileRequest {
    private Integer gmailId;
    private MultipartFile file;
}
