package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.repository.IProfileRepo;
import com.phuc.pcoreservice.request.ProfileRequest;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.util.FileCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
