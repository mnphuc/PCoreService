package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.model.User;

public interface IUserRepository {
    User findUserByUsername(String username);
    Integer saveUser(User user);

}
