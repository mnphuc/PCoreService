package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.config.FtpFileExchange;
import com.phuc.pcoreservice.dto.FingerprintDTO;
import com.phuc.pcoreservice.repository.IGmailRepo;
import com.phuc.pcoreservice.repository.IProfileRepo;
import com.phuc.pcoreservice.payload.request.ProfileRequest;
import com.phuc.pcoreservice.payload.response.ProfileTask;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.util.CommonUtil;
import com.phuc.pcoreservice.util.FileCommon;
import com.phuc.pcoreservice.util.HttpUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.integration.ftp.gateway.FtpOutboundGateway;
//import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private IProfileRepo profileRepo;

    @Autowired
    private IGmailRepo gmailRepo;
    @Value("${ftp.server.host}")
    private String host;
    @Value("${ftp.server.port}")
    private Integer port;
    @Value("${ftp.server.username}")
    private String user;
    @Value("${ftp.server.password}")
    private String password;
//
//    @Autowired
//    private DefaultFtpSessionFactory ftpSessionFactory;


    public static final String  DEFAULT_PATH_FINGERPRINT = "/file/fingerprint";

    @Override
    public ResponseEntity<?> saveProfile(ProfileRequest request) {
        String urlFile = FileCommon.saveFileProfile(request.getFile());
        Boolean bl = gmailRepo.changeStatusProfile(request.getGmailId(), urlFile);
        if (bl){
            return ResponseEntity.ok().body("Backup profile success!");
        }else{
            return ResponseEntity.badRequest().body("Backup profile false!");
        }

    }

    @Override
    public ResponseEntity<?> saveFingerprint(String value, String type) {

        Boolean bl = profileRepo.saveFingerprint(value, type);
        if (bl){
            return ResponseEntity.ok().body("save fingerprint success!");
        }else {
            return ResponseEntity.ok().body("save fingerprint fall!");
        }
    }

    @Override
    public ResponseEntity<?> getProfile(HttpServletRequest request) {

        String ip = HttpUtils.getRequestIP(request);
        System.out.println(ip);
        ProfileTask profileTask = profileRepo.getProfileTask(ip);
        if (profileTask.getProfileId() == null){
            return ResponseEntity.badRequest().body("no profile running!");
        }
        return ResponseEntity.ok().body(profileTask);
    }

    @Override
    public ResponseEntity<?> updateStatusRunning(Integer profileId) {
        Boolean bl = profileRepo.changeStatusRunning(profileId);
        if (bl){
            return  ResponseEntity.ok().body("Update status running success!");
        }else{
            return ResponseEntity.badRequest().body("Update status running fall!");
        }
    }

    @Override
    public void saveFingerprint(List<FingerprintDTO> list) {
        profileRepo.saveFingerprintList(list);
    }

    @Override
    public ResponseEntity<?> getFingerprint() {
        FingerprintDTO fingerprintDTO = profileRepo.getFingerprint();
        Path dirPath = Paths.get(fingerprintDTO.getFile());
        Resource resource = new FileSystemResource(dirPath.toString());
//        String jsonResult = null;
//        try (InputStream inputStream = resource.getInputStream()){
//            jsonResult = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fingerprints.json");
        try {
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("get fingerprint false");
//        return ResponseEntity.ok().body(jsonResult);
    }

    @Override
    public ResponseEntity<?> getFingerprintsFree() {
        return ResponseEntity.ok().body(profileRepo.getFingerprintsFree());
    }
}
