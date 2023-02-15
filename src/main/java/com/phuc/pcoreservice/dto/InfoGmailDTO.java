package com.phuc.pcoreservice.dto;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

@Data
public class InfoGmailDTO {
    private String uuid;
    private String username;
    private String password;

    @Property(name = "email_recovery")
    private String emailRecovery;
}
