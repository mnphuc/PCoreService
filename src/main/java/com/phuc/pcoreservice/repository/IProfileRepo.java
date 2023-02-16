package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.request.ProfileRequest;

public interface IProfileRepo {
    boolean saveProfileInfo(ProfileRequest request);
}
