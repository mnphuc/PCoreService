package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.model.Role;

import java.util.List;

public interface IRoleRepo {

    List<Role> findListByName(List<String> roleName);
    Boolean saveRole(List<Role> roles, Integer userId);
}
