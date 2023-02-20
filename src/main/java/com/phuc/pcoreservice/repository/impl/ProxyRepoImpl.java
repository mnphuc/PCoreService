package com.phuc.pcoreservice.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phuc.pcoreservice.dto.ProxyDTO;
import com.phuc.pcoreservice.repository.IProxyRepo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getListProxy(String ipAddress) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT proxy FROM tbl_proxy p inner join tbl_vps_info v on p.vps_use = v.id where ip_type = 2 and v.ip_address = :ip_address";
        ObjectMapper objectMapper = new ObjectMapper();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("ip_address", ipAddress);
        namedParameterJdbcTemplate.query(sql, parameterSource, rs -> {
            ProxyDTO proxyDTO = new ProxyDTO();
            String proxy = rs.getString("proxy");
            String[] array = proxy.split(":");
            proxyDTO.setIp(array[0]+ ":"+ array[1]);
            proxyDTO.setUsername(array[2]);
            proxyDTO.setPassword(array[3]);
            try {
                result.add(objectMapper.writeValueAsString(proxyDTO));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}
