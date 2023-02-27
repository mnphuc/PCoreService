package com.phuc.pcoreservice.config;

import com.phuc.pcoreservice.util.FileCommon;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class InitApplication {


    @PostConstruct
    public void init(){
        initPathFingerprint(FileCommon.DEFAULT_PATH_FINGERPRINT);
        initPathFingerprint(FileCommon.DEFAULT_PATH_PROFILE);
    }
    private void initPathFingerprint(String path){
        Path uploadPath = Paths.get(path);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
