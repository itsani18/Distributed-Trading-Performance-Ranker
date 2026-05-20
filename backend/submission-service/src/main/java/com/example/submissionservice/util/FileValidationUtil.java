package com.example.submissionservice.util;

import org.springframework.web.multipart.MultipartFile;

public class FileValidationUtil {

    public static void validate(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !(fileName.endsWith(".zip")
                        || fileName.endsWith(".tar"))) {

            throw new RuntimeException(
                    "Only .zip or .tar files are allowed"
            );
        }

        long maxSize = 50 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new RuntimeException(
                    "File size exceeds 50MB limit"
            );
        }
    }
}