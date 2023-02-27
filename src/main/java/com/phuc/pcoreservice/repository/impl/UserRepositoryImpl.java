package com.phuc.pcoreservice.repository.impl;

import com.phuc.pcoreservice.dto.ERole;
import com.phuc.pcoreservice.model.Role;
import com.phuc.pcoreservice.model.User;
import com.phuc.pcoreservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer saveUser(User user) {
        String sql = "INSERT into tbl_user (username, password, create_by, create_date) values (:username, :password, :create_by, now() )";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", user.getUsername());
        parameterSource.addValue("password", user.getPassword());
        parameterSource.addValue("create_by", "super_admin");
        namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public User findUserByUsername(String username) {
        String sql = "SELECT u.id, u.username, u.password  FROM tbl_user u where u.username = :username and status = 1";
        User user = new User();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);

        namedParameterJdbcTemplate.query(sql, parameterSource, rs -> {
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setId(rs.getInt("id"));
        });
        if (user.getId() != null){
            List<Role> roles = new ArrayList<>();
            String sqlRole = "SELECT r.id, r.name FROM tbl_role r inner join tbl_user_role ur on r.id = ur.id_role where ur.id_user = :user_id";
            MapSqlParameterSource parameterSourceRole = new MapSqlParameterSource();
            parameterSourceRole.addValue("user_id", user.getId());
            namedParameterJdbcTemplate.query(sqlRole, parameterSourceRole, rs -> {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(ERole.valueOf(rs.getString("name")));
                roles.add(role);
            });
            user.setRoles(roles);
        }

        return user;
    }
}
