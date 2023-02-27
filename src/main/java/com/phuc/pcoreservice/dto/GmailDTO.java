package com.phuc.pcoreservice.dto;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

@Data
public class GmailDTO {
    private Integer id;
    private String username;
    private String password;
    @Property(name = "mail_recovery")
    private String mailRecovery;
    private Boolean backupStatus;
    private String fileProfile;
}
