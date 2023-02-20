package com.phuc.pcoreservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceInfoDTO {
    private Integer id;
    private String name;
    private String ipAddress;
    private String description;
    private Integer status;
    private Date lastPingConnect;
}
