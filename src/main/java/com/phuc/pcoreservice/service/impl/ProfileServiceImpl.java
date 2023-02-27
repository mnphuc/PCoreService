package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.dto.FingerprintDTO;
import com.phuc.pcoreservice.repository.IGmailRepo;
import com.phuc.pcoreservice.repository.IProfileRepo;
import com.phuc.pcoreservice.payload.request.ProfileRequest;
import com.phuc.pcoreservice.payload.response.ProfileTask;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.util.CommonUtil;
import com.phuc.pcoreservice.util.FileCommon;
import com.phuc.pcoreservice.util.HttpUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private IProfileRepo profileRepo;

    @Autowired
    private IGmailRepo gmailRepo;

    @Override
    public ResponseEntity<?> saveProfile(ProfileRequest request) {
        String urlFile = FileCommon.saveFileProfile(request.getFile());
        Boolean bl = gmailRepo.changeStatusProfile(request.getGmailId(), urlFile);
        if (bl){
            return ResponseEntity.ok().body("Backup profile success!");
        }else{
            return ResponseEntity.badRequest().body("Backup profile false!");
        }

    }

    @Override
    public ResponseEntity<?> saveFingerprint(String value, String type) {
        Boolean bl = profileRepo.saveFingerprint(value, type);
        if (bl){
            return ResponseEntity.ok().body("save fingerprint success!");
        }else {
            return ResponseEntity.ok().body("save fingerprint fall!");
        }
    }

    @Override
    public ResponseEntity<?> getProfile(HttpServletRequest request) {

        String ip = HttpUtils.getRequestIP(request);
        System.out.println(ip);
        ProfileTask profileTask = profileRepo.getProfileTask(ip);
        if (profileTask.getProfileId() == null){
            return ResponseEntity.badRequest().body("no profile running!");
        }
        return ResponseEntity.ok().body(profileTask);
    }

    @Override
    public ResponseEntity<?> updateStatusRunning(Integer profileId) {
        Boolean bl = profileRepo.changeStatusRunning(profileId);
        if (bl){
            return  ResponseEntity.ok().body("Update status running success!");
        }else{
            return ResponseEntity.badRequest().body("Update status running fall!");
        }
    }

    @Override
    public void saveFingerprint(List<FingerprintDTO> list) {
        profileRepo.saveFingerprintList(list);
    }

    @Override
    public ResponseEntity<?> getFingerprint() {
        FingerprintDTO fingerprintDTO = profileRepo.getFingerprint();
        Resource resource = FileCommon.getFingerprintFile(fingerprintDTO.getFile());
        String jsonResult = null;
        try {
            jsonResult = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(jsonResult);
    }
}
