package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.dto.ProxyDTO;
import com.phuc.pcoreservice.model.ProxyModel;

import java.util.List;

public interface IProxyRepo {
    ProxyDTO getProxyV6();

    List<String> getListProxy(String ipAddress);

    Boolean importProxy(List<ProxyModel> list);
}
