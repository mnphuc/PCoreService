package com.phuc.pcoreservice.payload.response;

import lombok.Data;

@Data
public class ProfileTask {
    private Integer profileId;
    private String name;
    private Integer timeRun;
}
