package com.phuc.pcoreservice.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileRequest {
    private Integer gmailId;
    private Integer proxyId;
    private String profileName;
    private Integer statusGmail;

    private MultipartFile file;
}
