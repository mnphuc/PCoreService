package com.phuc.pcoreservice.request;

import lombok.Data;

import java.util.List;

@Data
public class ConfigRunningVPSRequest {
    private String ipAddress;
    private Integer totalGmail;
    private Integer totalProxy;

    private List<Integer> websites;
}
