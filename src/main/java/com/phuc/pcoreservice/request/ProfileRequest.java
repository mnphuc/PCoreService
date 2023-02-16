package com.phuc.pcoreservice.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileRequest {
    private Integer gmailId;
    private String profileName;
    private Integer statusGmail;

    private MultipartFile file;
}
