package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.repository.IProfileRepo;
import com.phuc.pcoreservice.request.ProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
