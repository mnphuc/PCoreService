package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.dto.FingerprintDTO;
import com.phuc.pcoreservice.payload.request.ProfileRequest;
import com.phuc.pcoreservice.payload.response.ProfileTask;

import java.util.List;

public interface IProfileRepo {

    boolean saveFingerprint(String value, String type);

    ProfileTask getProfileTask(String ipAddress);

    boolean changeStatusRunning(Integer profileId);

    void saveFingerprintList(List<FingerprintDTO> list);

    FingerprintDTO getFingerprint();

    Long getFingerprintsFree();

}
