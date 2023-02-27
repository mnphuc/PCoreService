package com.phuc.pcoreservice.service;

import com.phuc.pcoreservice.payload.request.ConfigRunningVPSRequest;
import org.springframework.http.ResponseEntity;

public interface ITaskService {
    ResponseEntity<?> configVPSRunning(ConfigRunningVPSRequest request);
}
