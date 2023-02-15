package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.dto.InfoGmailDTO;
import com.phuc.pcoreservice.repository.IGmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;

import org.springframework.jdbc.core.SqlOutParameter;

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

    //    @PersistenceContext
//    private EntityManager entityManager;
    @Autowired
    private DataSource dataSource;

    @Override
    @Transactional
    public InfoGmailDTO getGmail() {
        InfoGmailDTO result = new InfoGmailDTO();
//        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_get_gmail");
//        query.registerStoredProcedureParameter("p_uuid", String.class, ParameterMode.OUT);
//        query.registerStoredProcedureParameter("p_user_gmail", String.class, ParameterMode.OUT);
//        query.registerStoredProcedureParameter("p_password_gmail", String.class, ParameterMode.OUT);
//        query.registerStoredProcedureParameter("p_email_recovery", String.class, ParameterMode.OUT);
//        query.execute();
//
//
//        result.setUuid((String) query.getOutputParameterValue("p_uuid"));
//        result.setUsername((String) query.getOutputParameterValue("p_user_gmail"));
//        result.setPassword((String) query.getOutputParameterValue("p_password_gmail"));
//        result.setEmailRecovery((String) query.getOutputParameterValue("p_email_recovery"));

//        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
//                .withProcedureName("sp_get_gmail")
//                .declareParameters(new SqlOutParameter("p_uuid", Types.VARCHAR))
//                .declareParameters(new SqlOutParameter("p_user_gmail", Types.VARCHAR))
//                .declareParameters(new SqlOutParameter("p_password_gmail", Types.VARCHAR))
//                .declareParameters(new SqlOutParameter("p_email_recovery", Types.VARCHAR));
//
//
//
//        Map<String, Object> outParams = jdbcCall.execute();
//        result.setUuid((String) outParams.get("p_uuid"));
//        result.setUsername((String) outParams.get("p_user_gmail"));
//        result.setPassword((String) outParams.get("p_password_gmail"));
//        result.setEmailRecovery((String) outParams.get("p_email_recovery"));
        String sql = "CALL sp_get_gmail(@p_uuid, @p_user_gmail, @p_password_gmail, @p_email_recovery)";
//        namedParameterJdbcTemplate
        return result;
    }
}
