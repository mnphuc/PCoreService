package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.model.TaskConfigVPS;
import com.phuc.pcoreservice.repository.ITaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepoImpl implements ITaskRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional
    public Boolean saveConfigVPS(TaskConfigVPS taskConfigVPS) {
        String sql = "INSERT INTO tbl_vps_info(ip_address) values (:ipAddress)";
        MapSqlParameterSource parameterSourceVPS = new MapSqlParameterSource();
        parameterSourceVPS.addValue("ipAddress", taskConfigVPS.getIpVPS());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int insertVPS = namedParameterJdbcTemplate.update(sql, parameterSourceVPS, keyHolder);

        Integer vpsId = keyHolder.getKey().intValue();

        MapSqlParameterSource parameterSourceGmail = new MapSqlParameterSource();
        parameterSourceGmail.addValue("limit", taskConfigVPS.getTotalGmail());
        parameterSourceGmail.addValue("vpsID", vpsId);
        String sqlUpdateGmail = "update tbl_gmail set status = 1, vps_use = :vpsID where vps_use is null limit :limit";
        int updateGmail = namedParameterJdbcTemplate.update(sqlUpdateGmail, parameterSourceGmail);

        MapSqlParameterSource parameterUpdateProxy = new MapSqlParameterSource();
        parameterUpdateProxy.addValue("vpsID", vpsId);
        parameterUpdateProxy.addValue("limit", taskConfigVPS.getTotalProxy());
        String sqlUpdateProxy = "update tbl_proxy set vps_use = :vpsID where vps_use is null limit :limit";
        int statusUpdateGmail = namedParameterJdbcTemplate.update(sqlUpdateProxy, parameterUpdateProxy);

        String sqlInsertWebsiteUse = "INSERT INTO tbl_vps_use_website (id_vps, id_website) values (:id_vps, :id_website)";
        MapSqlParameterSource parameterWebsite = new MapSqlParameterSource();
        parameterWebsite.addValue("id_vps", vpsId);
        parameterWebsite.addValue("id_website", 1);
        int statusInsertWebsite = namedParameterJdbcTemplate.update(sqlInsertWebsiteUse, parameterWebsite);
        return true;
    }
}
