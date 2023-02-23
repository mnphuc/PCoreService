package com.phuc.pcoreservice.model;

import lombok.Data;

@Data
public class ProxyModel {
    private Integer id;
    private Integer type;
    private String proxy;
    private Integer vpsUse;
    private Integer status;

    public ProxyModel(String proxy) {
        this.proxy = proxy;
    }
}
