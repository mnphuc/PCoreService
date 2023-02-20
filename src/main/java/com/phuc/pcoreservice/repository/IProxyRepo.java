package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.dto.ProxyDTO;

import java.lang.reflect.Proxy;
import java.util.List;

public interface IProxyRepo {
    ProxyDTO getProxyV6();

    List<String> getListProxy(String ipAddress);
}
