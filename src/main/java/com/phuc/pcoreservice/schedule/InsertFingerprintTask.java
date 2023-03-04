package com.phuc.pcoreservice.schedule;

import com.phuc.pcoreservice.dto.FingerprintDTO;
import com.phuc.pcoreservice.service.IProfileService;
import com.phuc.pcoreservice.util.CommonUtil;
import com.phuc.pcoreservice.util.FileCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InsertFingerprintTask implements Runnable {

    private IProfileService profileService;
    private List<FingerprintDTO> list;
    private String urlRequest;
    private String type;

    public InsertFingerprintTask(List<FingerprintDTO> list, String urlRequest, String type) {
        this.list = list;
        this.urlRequest = urlRequest;
        this.type = type;
    }


    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://fingerprints.bablosoft.com/prepare?version=5&tags=Desktop&key=7O9tLlrGfAqBL6WFRme34LK98jAOfnV4F3PAJJjWf398DcW7GOB7qYqSBuNYVGJ2&returnpc=true", String.class);
        if (response.getStatusCode().value() == 200) {
            String path = FileCommon.createJsonFile(response.getBody(), "Desktop");
            FingerprintDTO fingerprintDTO = new FingerprintDTO();
            fingerprintDTO.setFile(path);
            fingerprintDTO.setType(type);
            list.add(fingerprintDTO);
        }
    }
}
