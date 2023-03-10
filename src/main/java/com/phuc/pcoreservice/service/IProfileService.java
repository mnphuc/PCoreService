package com.phuc.pcoreservice.service;

import com.phuc.pcoreservice.dto.FingerprintDTO;
import com.phuc.pcoreservice.payload.request.ProfileRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IProfileService {
    ResponseEntity<?> saveProfile(ProfileRequest request);

    ResponseEntity<?> saveFingerprint(String value, String type);

    ResponseEntity<?> getProfile(HttpServletRequest request);

    ResponseEntity<?> updateStatusRunning(Integer profileId);

    void saveFingerprint(List<FingerprintDTO> list);

    ResponseEntity<?> getFingerprint();

    ResponseEntity<?> getFingerprintsFree();


}
