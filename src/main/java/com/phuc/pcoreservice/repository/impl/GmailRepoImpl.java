package com.phuc.pcoreservice.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phuc.pcoreservice.dto.GmailDTO;
import com.phuc.pcoreservice.dto.InfoGmailDTO;
import com.phuc.pcoreservice.model.GmailModel;
import com.phuc.pcoreservice.repository.IGmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import java.sql.Types;
import java.util.*;

@Repository
public class GmailRepoImpl implements IGmailRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @PersistenceContext
//    private EntityManager entityManager;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public InfoGmailDTO getGmail() {
        InfoGmailDTO result = new InfoGmailDTO();

        String sqlQuery = "SELECT tdg.id as id, tdg.user_gmail as user_gmail, tdg.password_gmail as password_gmail, tdg.email_recovery as email_recovery " +
                "FROM tbl_data_gmail tdg where tdg.status = 1 LIMIT 1 for update";
        namedParameterJdbcTemplate.query(sqlQuery, rs -> {
            int id = rs.getInt("id");
            String username = rs.getString("user_gmail");
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);
            namedParameterJdbcTemplate.update("UPDATE tbl_data_gmail set status = 2 where id = :id", params);
            result.setId(id);
            result.setUuid(id + username.substring(0, username.indexOf("@")));
            result.setUsername(username);
            result.setPassword(rs.getString("password_gmail"));
            result.setEmailRecovery(rs.getString("email_recovery"));
        });
        return result;
    }

    @Override
    public List<String> getListGmailByVps(String ipAddress) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT g.id, g.username, g.password, g.mail_recovery, g.backup_status, g.file_profile FROM tbl_gmail g inner join tbl_vps_info v on g.vps_use = v.id where v.ip_address = :ip_address and g.status = 1";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ip_address", ipAddress);
        ObjectMapper objectMapper = new ObjectMapper();
        namedParameterJdbcTemplate.query(sql, params, rs -> {
            GmailDTO gmailDTO = new GmailDTO();
            gmailDTO.setId(rs.getInt("id"));
            gmailDTO.setUsername(rs.getString("username"));
            gmailDTO.setPassword(rs.getString("password"));
            gmailDTO.setMailRecovery(rs.getString("mail_recovery"));
            gmailDTO.setBackupStatus(rs.getBoolean("backup_status"));
            gmailDTO.setFileProfile(rs.getString("file_profile"));
            try {
                result.add(objectMapper.writeValueAsString(gmailDTO));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    @Override
    @Transactional
    public boolean updateStatusLoginGmail(Integer gmailId) {
        String sql = "SELECT id, login_count_fall FROM tbl_gmail tg where id = :id FOR UPDATE";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", gmailId);
        namedParameterJdbcTemplate.query(sql, parameterSource, rs -> {
            Integer countFall = rs.getInt("login_count_fall");
            String sqlUpdate = "";
            if (countFall > 1){
                sqlUpdate = "update tbl_gmail set login_count_fall = login_count_fall + 1 where id = :id";
            }else {
                sqlUpdate = "update tbl_gmail set login_count_fall = login_count_fall + 1, status = 2 where id = :id";
            }
            MapSqlParameterSource paramUpdate = new MapSqlParameterSource();
            paramUpdate.addValue("id", gmailId);
            namedParameterJdbcTemplate.update(sqlUpdate, paramUpdate);
        });
        return true;
    }

    @Override
    @Transactional
    public Boolean importGmail(List<GmailModel> list) {
        List<SqlParameterSource> sourceList = new ArrayList<>();
        String sql = "INSERT INTO tbl_gmail (username, password, mail_recovery) VALUES(:username, :password, :mail_recovery)";
        list.forEach(p -> {
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("username", p.getUsername());
            source.addValue("password", p.getPassword());
            source.addValue("mail_recovery", p.getMailRecovery());
            sourceList.add(source);
        });

        SqlParameterSource[] sqlParameterSources = sourceList.toArray(new SqlParameterSource[0]);
        namedParameterJdbcTemplate.batchUpdate(sql, sqlParameterSources);
        return true;
    }

    @Override
    public Boolean changeStatusProfile(Integer gmailId, String urlFile) {
        String sql = "UPDATE tbl_gmail set backup_status = 1, file_profile = :url_file where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("url_file", urlFile);
        parameterSource.addValue("id", gmailId);
        int status = namedParameterJdbcTemplate.update(sql, parameterSource);
        return status == 1;
    }

    @Override
    public Long getGmailFree() {
        String sql = "SELECT COUNT(id) from tbl_gmail tg where vps_use is null";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        Long value = namedParameterJdbcTemplate.queryForObject(sql,namedParameters, Long.class);
        return value;
    }
}
