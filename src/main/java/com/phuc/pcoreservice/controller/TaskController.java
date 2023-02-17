package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.request.ProfileRequest;
import com.phuc.pcoreservice.service.IGmailService;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.service.IProxyService;
import com.phuc.pcoreservice.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.DatagramSocket;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Value("${application.header-ip-candidates}")
    private String[] headerCandidates;
    @Autowired
    private IGmailService gmailService;

    @Autowired
    private IProxyService proxyService;

    @Autowired
    private IProfileService profileService;

    @GetMapping("/get-gmail")
    public ResponseEntity<?> getGmail(){
        return gmailService.getGmailCreateProfile();
    }

    @GetMapping(value = "/get-proxy")
    public ResponseEntity<?> getProxy(){
        return proxyService.getProxyV6();
    }

    @PostMapping(value = "/save-profile")
    public ResponseEntity<?> saveProfile(@ModelAttribute ProfileRequest request){
        return profileService.saveProfile(request);
    }

    @GetMapping("/get-ip")
    public ResponseEntity<?> getIpClient(){
//        if (RequestContextHolder.getRequestAttributes() == null) {
//            return ResponseEntity.ok("0.0.0.0");
//        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        for (String header: headerCandidates) {
//            String ipList = request.getHeader(header);
//            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
//                String ip = ipList.split(",")[0];
//                return ResponseEntity.ok(ip);
//            }
//        }
//        return ResponseEntity.ok(request.getRemoteAddr());
        return ResponseEntity.ok().body(HttpUtils.getRequestIP(request));
    }
}
