package com.phuc.pcoreservice.util;


import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;

public class FileCommon {

    private Path foundFile;

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
