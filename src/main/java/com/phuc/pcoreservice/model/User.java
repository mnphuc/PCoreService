package com.phuc.pcoreservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;

    private List<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
