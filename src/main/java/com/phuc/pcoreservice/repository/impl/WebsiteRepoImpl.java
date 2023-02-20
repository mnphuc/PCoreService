package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.repository.IWebsiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicReference;

@Repository
public class WebsiteRepoImpl implements IWebsiteRepo {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String getRandomUrl() {
        String sql = "SELECT url as url FROM tbl_website ORDER BY RAND() LIMIT 1";
        AtomicReference<String> result = new AtomicReference<>();
        namedParameterJdbcTemplate.query(sql, rs -> {
            result.set(rs.getString("url"));
        });
        return result.get();
    }

    @Override
    public String getRandomWebsite(String ipAddress) {
        AtomicReference<String> result = new AtomicReference<>();
        String sql = "SELECT s.website FROM tbl_vps_use_website vw inner join tbl_site_remote s on vw.id_website = s.id inner join tbl_vps_info vi on vi.id = vw.id_vps where vi.ip_address = :ip_address order by rand() limit 1";
        MapSqlParameterSource parameterSource =  new MapSqlParameterSource();
        parameterSource.addValue("ip_address", ipAddress);
        namedParameterJdbcTemplate.query(sql, parameterSource, rs -> {
            result.set(rs.getString("website"));
        });
        return result.get();
    }
}
