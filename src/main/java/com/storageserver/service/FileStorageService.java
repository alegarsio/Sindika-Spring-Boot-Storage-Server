package com.storageserver.service;

/**
 * Beans service
 */

import org.springframework.beans.factory.annotation.Value;

/**
 * Spring core dependencies
 */
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Java lib dependencies
 */
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;



@Service
public class FileStorageService {
    
    private final Path fileStorageLocation;

    /**
     * 
     * @param uploadDir
     */
    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * Store file
     * @param file
     * @return
     * storeFile method provides a way to store an uploaded file on the server's filesystem.
     */

    public String storeFile(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = "";
        try {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        } catch (Exception e) {
            
        }

        String newFileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString().substring(0, 8) + fileExtension;

        try {
            Path targetLocation = this.fileStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Gagal menyimpan file " + newFileName, ex);
        }
    }

    /**
     * Load file as resource
     * @param fileName
     * @return
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File tidak ditemukan: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File tidak ditemukan: " + fileName, ex);
        }
    }

    /**
     * Delete file
     * @param fileName
     */

    public void deleteFile(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            if(!Files.exists(filePath)) {
                 throw new RuntimeException("File tidak ditemukan: " + fileName);
            }
            Files.delete(filePath);
        } catch (IOException ex) {
            throw new RuntimeException("Gagal menghapus file: " + fileName, ex);
        }
    }




    
     

}
