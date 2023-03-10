package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.dto.ProxyDTO;
import com.phuc.pcoreservice.model.ProxyModel;
import com.phuc.pcoreservice.repository.IProxyRepo;
import com.phuc.pcoreservice.service.IProxyService;
import com.phuc.pcoreservice.util.HttpUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ResponseEntity<?> getStringProxyV6(HttpServletRequest request) {
        String ip = HttpUtils.getRequestIP(request);
        List<String> result = proxyRepo.getListProxy(ip);
        if (result == null){
            ResponseEntity.badRequest().body("no data");
        }
        return ResponseEntity.ok().body(StringUtils.join(result, "\n"));
    }

    @Override
    public ResponseEntity<?> importProxy(MultipartFile file) {
        try {
            if (file == null){
                new RuntimeException("Import file!");
            }
            if (!"text/plain".equals(file.getContentType())){
                new RuntimeException("File wrong format !");
            }
            List<ProxyModel> proxyList = new ArrayList<>();
            String theString = IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);
            String[] arrayProxy = theString.split("\n");
            for (String s : arrayProxy) {
                if (StringUtils.isNotBlank(s)){
                    proxyList.add(new ProxyModel(s.trim()));
                }
            }
            proxyRepo.importProxy(proxyList);
        }catch (IOException exception){
            new RuntimeException("Read File Error ");
        }
        return ResponseEntity.ok().body("Import thành công");
    }

    @Override
    public ResponseEntity<?> getProxyFree() {
        return ResponseEntity.ok().body(proxyRepo.getProxyFree());
    }
}
