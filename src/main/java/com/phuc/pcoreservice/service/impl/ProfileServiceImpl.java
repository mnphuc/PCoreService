package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.repository.IProfileRepo;
import com.phuc.pcoreservice.request.ProfileRequest;
import com.phuc.pcoreservice.response.ProfileTask;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.util.FileCommon;
import com.phuc.pcoreservice.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private IProfileRepo profileRepo;

    @Override
    public ResponseEntity<?> saveProfile(ProfileRequest request) {
        FileCommon.saveFile(request.getFile());
        Boolean bl = profileRepo.saveProfileInfo(request);
        if (bl){
            return ResponseEntity.ok().body("Save profile success!");
        }else{
            return ResponseEntity.badRequest().body("Save profile false!");
        }

    }

    @Override
    public ResponseEntity<?> saveFingerprint(String value) {
        Boolean bl = profileRepo.saveFingerprint(value);
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
}
