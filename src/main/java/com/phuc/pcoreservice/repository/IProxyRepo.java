package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.dto.ProxyDTO;

import java.lang.reflect.Proxy;

public interface IProxyRepo {
    ProxyDTO getProxyV6();
}
