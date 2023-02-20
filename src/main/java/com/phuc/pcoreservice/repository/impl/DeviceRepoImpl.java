package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.dto.DeviceInfoDTO;
import com.phuc.pcoreservice.repository.IDeviceInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceRepoImpl implements IDeviceInfoRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Boolean saveDeviceInfo(DeviceInfoDTO deviceInfo) {
        String sql = "INSERT INTO tbl_device_info(name, ip_address, description, status, last_ping_connect) values (:name, :ip_address, :description, :status, :last_ping_connect )";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", deviceInfo.getName());
        params.addValue("ip_address", deviceInfo.getIpAddress());
        params.addValue("description", deviceInfo.getDescription());
        params.addValue("status", deviceInfo.getStatus());
        params.addValue("last_ping_connect", deviceInfo.getLastPingConnect());
        int statusSave = namedParameterJdbcTemplate.update(sql, params);
        return statusSave == 1;

    }
}
