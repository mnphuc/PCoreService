package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.dto.ERole;
import com.phuc.pcoreservice.model.Role;
import com.phuc.pcoreservice.repository.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepoImpl implements IRoleRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Role> findListByName(List<String> roleName) {

        List<Role> result = new ArrayList<>();
        String sql = "SELECT id, name FROM tbl_role where name in (:roleList)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("roleList", roleName);
        namedParameterJdbcTemplate.query(sql, parameterSource, rs -> {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(ERole.valueOf(rs.getString("name")));
            result.add(role);
        });

        return result;
    }

    @Override
    public Boolean saveRole(List<Role> roles, Integer userId) {
        String sql = "INSERT INTO tbl_user_role values (:id_user,:id_role)";
        List<SqlParameterSource> sourceList = new ArrayList<>();
        roles.forEach(item -> {
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("id_user", userId);
            source.addValue("id_role", item.getId());
            sourceList.add(source);
        });
        SqlParameterSource[] sqlParameterSources = sourceList.toArray(new SqlParameterSource[0]);
        namedParameterJdbcTemplate.batchUpdate(sql, sqlParameterSources);
        return true;
    }
}
