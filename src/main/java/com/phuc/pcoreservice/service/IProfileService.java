package com.phuc.pcoreservice.service;

import com.phuc.pcoreservice.request.ProfileRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IProfileService {
    ResponseEntity<?> saveProfile(ProfileRequest request);

    ResponseEntity<?> saveFingerprint(String value);

    ResponseEntity<?> getProfile(HttpServletRequest request);

    ResponseEntity<?> updateStatusRunning(Integer profileId);
}
