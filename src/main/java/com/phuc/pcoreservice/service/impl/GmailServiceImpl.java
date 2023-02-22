package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.dto.InfoGmailDTO;
import com.phuc.pcoreservice.repository.IGmailRepo;
import com.phuc.pcoreservice.service.IGmailService;
import com.phuc.pcoreservice.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GmailServiceImpl implements IGmailService {

    @Autowired
    private IGmailRepo gmailRepo;


    @Override
    public ResponseEntity<?> getGmailCreateProfile() {
        InfoGmailDTO result = gmailRepo.getGmail();
        if (StringUtils.isBlank(result.getUuid())){
            return ResponseEntity.badRequest().body("Gmail empty!");
        }
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<?> getGmailByVps(HttpServletRequest request) {
        String ip = HttpUtils.getRequestIP(request);
        List<String> result = gmailRepo.getListGmailByVps(ip);
        if (result == null){
            return ResponseEntity.badRequest().body("no data");
        }
        return ResponseEntity.ok().body(StringUtils.join(result, "\n"));
    }

    @Override
    public ResponseEntity<?> updateStatusLogin(Integer gmailId) {
        gmailRepo.updateStatusLoginGmail(gmailId);
        return ResponseEntity.ok().body("cập nhật gmail thành công");
    }
}
