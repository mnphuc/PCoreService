package com.phuc.pcoreservice.service;

import com.phuc.pcoreservice.request.ProfileRequest;
import org.springframework.http.ResponseEntity;

public interface IProfileService {
    ResponseEntity<?> saveProfile(ProfileRequest request);
}
