package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.dto.InfoGmailDTO;
import com.phuc.pcoreservice.model.GmailModel;
import com.phuc.pcoreservice.model.ProxyModel;
import com.phuc.pcoreservice.repository.IGmailRepo;
import com.phuc.pcoreservice.service.IGmailService;
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

    @Override
    public ResponseEntity<?> importGmail(MultipartFile file) {
        try {
            if (file == null){
                new RuntimeException("Import file!");
            }
            if (!"text/plain".equals(file.getContentType())){
                new RuntimeException("File wrong format !");
            }
            List<GmailModel> gmailList = new ArrayList<>();
            String theString = IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);
            String[] arrayProxy = theString.split("\n");
            for (String s : arrayProxy) {

                if (StringUtils.isNotBlank(s)){
                    String[] gmailArray = s.split(",");
                    GmailModel gmailModel = new GmailModel();
                    gmailModel.setUsername(gmailArray[0]);
                    gmailModel.setPassword(gmailArray[1]);
                    gmailModel.setMailRecovery(gmailArray[2]);
                    gmailList.add(gmailModel);
                }
            }
            gmailRepo.importGmail(gmailList);
        }catch (IOException exception){
            new RuntimeException("Read File Error ");
        }
        return ResponseEntity.ok().body("Import thành công");

    }
}
