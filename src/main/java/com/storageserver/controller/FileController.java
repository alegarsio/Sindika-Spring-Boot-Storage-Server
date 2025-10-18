package com.storageserver.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.storageserver.service.FileStorageService;

import java.io.IOException;
import java.util.Map;

/**
 * FileController 
 * filecontroller is a afile that contains the basic oepration to the cloud file storage
 * entry point -> /api
 * upload point -> /api/upload
 * get file point -> /api/files/ { filename }
 */

@RestController
@RequestMapping("/api")
public class FileController {

    private final FileStorageService fileStorageService;

    /**
     * @param fileStorageService
     */

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    /**
     * @param file
     * @return
     */

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file_upload") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(fileName)
                .toUriString();
        
        return ResponseEntity.ok(Map.of("status", "sukses", "file_url", fileUrl));
    }


    /**
     * 
     * @param fileName
     * @param request
     * @return
     */

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
           
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    /**
     * 
     * @param fileName
     * @return
     */

    @DeleteMapping("/files/{fileName:.+}")
    public ResponseEntity<Map<String, String>> deleteFile(@PathVariable String fileName) {
        fileStorageService.deleteFile(fileName);
        return ResponseEntity.ok(Map.of("status", "sukses", "message", "File berhasil dihapus."));
    }

}
