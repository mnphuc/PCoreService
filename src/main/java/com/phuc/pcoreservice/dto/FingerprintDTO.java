package com.phuc.pcoreservice.dto;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class FingerprintDTO {

    private Integer id;
    private String file;
    private String type;
}
