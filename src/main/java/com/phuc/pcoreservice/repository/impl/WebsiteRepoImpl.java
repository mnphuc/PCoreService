package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.repository.IWebsiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
}
