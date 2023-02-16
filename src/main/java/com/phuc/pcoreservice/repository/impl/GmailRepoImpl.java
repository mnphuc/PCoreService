package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.dto.InfoGmailDTO;
import com.phuc.pcoreservice.repository.IGmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            result.setUuid(id + username.substring(0, username.indexOf("@")));
            result.setUsername(username);
            result.setPassword(rs.getString("password_gmail"));
            result.setEmailRecovery(rs.getString("email_recovery"));
        });
        return result;
    }
}
