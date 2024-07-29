package com.company.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AttachService {

    ResponseEntity<?> uploadFile(MultipartFile ile);

    int upload(MultipartFile file);

    byte[] download(int id);

    ResponseEntity<?> removeFile(int id);
}
