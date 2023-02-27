package com.phuc.pcoreservice.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class ConfigRunningVPSRequest {

    private String ipAddress;

    private Integer totalData;

    private List<Integer> websites;
}
