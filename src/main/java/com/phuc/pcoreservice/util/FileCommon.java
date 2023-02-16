package com.phuc.pcoreservice.util;


import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCommon {

    private static final String  DEFAULT_PATH = "file";
    public static String saveFile(MultipartFile multipartFile) {
        String result = new String();
        Path uploadPath = Paths.get(DEFAULT_PATH);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(multipartFile.getOriginalFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            result = filePath.toFile().getPath();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return result;
    }
}
