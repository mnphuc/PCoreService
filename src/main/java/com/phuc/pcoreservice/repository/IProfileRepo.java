package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.request.ProfileRequest;
import com.phuc.pcoreservice.response.ProfileTask;

public interface IProfileRepo {
    boolean saveProfileInfo(ProfileRequest request);
    boolean saveFingerprint(String value);

    ProfileTask getProfileTask(String ipAddress);

    boolean changeStatusRunning(Integer profileId);
}
