package com.phuc.pcoreservice.util;


import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.core.io.Resource;

public class FileCommon {

    private Path foundFile;

    public static final String  DEFAULT_PATH_PROFILE = "file/profile";
    public static final String  DEFAULT_PATH = "file";
    public static final String  DEFAULT_PATH_FINGERPRINT = "file/fingerprint";

    public static String saveFileProfile(MultipartFile multipartFile) {
        String result = new String();
        Path uploadPath = Paths.get(DEFAULT_PATH_PROFILE);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(multipartFile.getOriginalFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            result = filePath.toFile().getPath();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return result;
    }
    public static String createJsonFile(String json, String type){
        Long fileName = new Date().getTime();
        Path uploadPath = Paths.get(DEFAULT_PATH_FINGERPRINT);
        File file = new File(uploadPath+ "/"+type+fileName+".json");

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return file.getPath();
    }

    public static Resource getFingerprintFile(String fileName){
        Path dirPath = Paths.get(fileName);
        try {
            return new UrlResource(dirPath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get(DEFAULT_PATH);

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }

}
