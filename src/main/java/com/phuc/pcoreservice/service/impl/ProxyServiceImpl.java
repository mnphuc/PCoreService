package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.dto.ProxyDTO;
import com.phuc.pcoreservice.repository.IProxyRepo;
import com.phuc.pcoreservice.service.IProxyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProxyServiceImpl implements IProxyService {

    @Autowired
    private IProxyRepo proxyRepo;

    @Override
    public ResponseEntity<?> getProxyV6() {
        ProxyDTO result = proxyRepo.getProxyV6();
        if (StringUtils.isBlank(result.getIp())){
            ResponseEntity.badRequest().body("Proxy Empty");
        }
        return ResponseEntity.ok().body(result);
    }
}
