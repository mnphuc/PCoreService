package com.phuc.pcoreservice.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class ConfigRunningVPSRequest {

    private String ipAddress;

    private Integer totalGmail;

    private Integer totalProxy;

    private List<Integer> websites;
}
