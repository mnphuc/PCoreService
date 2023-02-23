package com.phuc.pcoreservice.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

import java.util.List;

@Data
public class ConfigRunningVPSRequest {

    private String ipAddress;

    private Integer totalGmail;

    private Integer totalProxy;

    private List<Integer> websites;
}
