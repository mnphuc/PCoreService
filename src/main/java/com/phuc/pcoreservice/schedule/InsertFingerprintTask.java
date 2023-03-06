package com.phuc.pcoreservice.schedule;
//
//import com.phuc.pcoreservice.config.FTPConfig;

import com.phuc.pcoreservice.config.FtpFileExchange;
import com.phuc.pcoreservice.dto.FingerprintDTO;
import com.phuc.pcoreservice.service.IFTPService;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.util.CommonUtil;
import com.phuc.pcoreservice.util.FileCommon;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class InsertFingerprintTask implements Runnable {

    //    private FTPConfig.FtpGateway ftpGateway;

    private List<FingerprintDTO> list;
    private String urlRequest;
    private String type;


    public InsertFingerprintTask( List<FingerprintDTO> list, String urlRequest, String type) {
        this.list = list;
        this.urlRequest = urlRequest;
        this.type = type;
//        this.ftpGateway = ftpGateway;
    }


    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(urlRequest, String.class);
        if (response.getStatusCode().value() == 200) {
            String path = FileCommon.createJsonFile(response.getBody(), type);
            FingerprintDTO fingerprintDTO = new FingerprintDTO();
            fingerprintDTO.setFile(path);
            fingerprintDTO.setType(type);
            list.add(fingerprintDTO);
        }
    }

    private String buildFileJson(String json) throws IOException {
        String result = "";
        Long fileName = new Date().getTime();
        Path uploadPath = Paths.get("file/fingerprint");
        File file = new File(uploadPath + "/" + type + fileName + ".json");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
        return result;
    }
}
