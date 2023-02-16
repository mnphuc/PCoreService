package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.dto.ProxyDTO;
import com.phuc.pcoreservice.repository.IProxyRepo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class ProxyRepoImpl implements IProxyRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional
    public ProxyDTO getProxyV6() {
        ProxyDTO result = new ProxyDTO();
        String sql = "SELECT tdp.id, tdp.proxy FROM tbl_data_proxy tdp where tdp.proxy_type = 2 and tdp.status = 1 limit 1 FOR UPDATE";

        namedParameterJdbcTemplate.query(sql, rs -> {
            Integer id = rs.getInt("id");
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);
            namedParameterJdbcTemplate.update("UPDATE tbl_data_proxy set status = 2 where id = :id", params);
            String proxy = rs.getString("proxy");
            String[] arrProxy = proxy.split(":");
            result.setId(id);
            result.setIp(arrProxy[0] + ":" + arrProxy[1]);
            result.setUsername(arrProxy[2]);
            result.setPassword(arrProxy[3]);
        });
        return result;
    }
}
