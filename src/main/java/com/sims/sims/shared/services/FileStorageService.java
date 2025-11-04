package com.sims.sims.shared.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    public String storeFile(MultipartFile file, String subFolder) {
        if (file.isEmpty()) {
            throw new RuntimeException("File kosong, tidak bisa diupload");
        }

        try {
            // Buat subfolder jika belum ada
            Path targetDir = Paths.get(uploadDir, subFolder).normalize();
            Files.createDirectories(targetDir);

            // Amankan nama file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path targetPath = targetDir.resolve(fileName);

            // Simpan file ke folder
            file.transferTo(targetPath.toFile());

            return targetPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Gagal menyimpan file: " + e.getMessage());
        }
    }
}
