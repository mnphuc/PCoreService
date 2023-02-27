package com.phuc.pcoreservice.model;

import com.phuc.pcoreservice.dto.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
public class Role {
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ERole name;
}
