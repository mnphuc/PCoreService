package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.dto.DeviceInfoDTO;

public interface IDeviceInfoRepo {
    Boolean saveDeviceInfo(DeviceInfoDTO deviceInfo);
}
