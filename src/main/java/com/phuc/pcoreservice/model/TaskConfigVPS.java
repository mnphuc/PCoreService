package com.phuc.pcoreservice.model;

import lombok.Data;

import java.util.List;

@Data
public class TaskConfigVPS {
    private String ipVPS;
    private Integer totalData;
    private List<Integer> listWebsite;
}
