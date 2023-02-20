package com.phuc.pcoreservice.dto;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class ProfileInfoDTO {

    private Integer id;

    @Property(name = "data_gmail_id")
    private Integer gmailId;

    @Property(name = "profile_name")
    private String profileName;

    @Property(name = "login_gmail_status")
    private Integer gmailStatus;
}
