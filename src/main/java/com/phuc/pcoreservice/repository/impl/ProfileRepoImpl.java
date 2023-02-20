package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.repository.IProfileRepo;
import com.phuc.pcoreservice.request.ProfileRequest;
import com.phuc.pcoreservice.response.ProfileTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class ProfileRepoImpl implements IProfileRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean saveProfileInfo(ProfileRequest request) {
        String sql = "INSERT into tbl_profile_chrome(data_gmail_id,data_proxy_id, profile_name, login_gmail_status, create_by, create_date) values (:data_gmail_id,:data_proxy_id, :profile_name, :login_gmail_status, 'admin', now())";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("data_gmail_id", request.getGmailId());
        params.addValue("data_proxy_id", request.getGmailId());
        params.addValue("profile_name", request.getProfileName());
        params.addValue("login_gmail_status", request.getStatusGmail());

        int saveStatus = namedParameterJdbcTemplate.update(sql, params);
        return saveStatus == 1;
    }

    @Override
    public boolean changeStatusRunning(Integer profileId) {
        String sql =  "UPDATE tbl_profile_chrome SET use_running = 0 WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", profileId);
        int updateStatus = namedParameterJdbcTemplate.update(sql, parameterSource);
        return updateStatus == 1;
    }

    @Override
    public boolean saveFingerprint(String value) {
        String sql = "INSERT into tbl_fingerprints_data(data_fingerprint) values (:data_fingerprint)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("data_fingerprint", value);
        int saveStatus = namedParameterJdbcTemplate.update(sql, params);
        return saveStatus == 1;
    }

    @Override
    @Transactional
    public ProfileTask getProfileTask(String ipAddress) {
        ProfileTask result = new ProfileTask();
        String sql = "SELECT tpc.id, tpc.profile_name  FROM tbl_profile_chrome tpc where tpc.use_running = 0 and tpc.login_gmail_status = 1 and tpc.device_name = :device_name FOR UPDATE";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("device_name", ipAddress);
        namedParameterJdbcTemplate.query(sql,params, rs -> {
            Integer id = rs.getInt("id");
            String sqlUpdate = "UPDATE tbl_profile_chrome SET use_running = 1, timeRunning = now() WHERE id = :id";
            MapSqlParameterSource paramsUpdate = new MapSqlParameterSource();
            paramsUpdate.addValue("id", id);
            namedParameterJdbcTemplate.update(sqlUpdate, paramsUpdate);
            result.setProfileId(id);
            result.setName(rs.getString("profile_name"));
        });
        return result;
    }
}
