package com.company.service.impl;

import com.company.model.dto.AttachDto;
import com.company.model.entity.AttachEntity;
import com.company.repository.AttachRepository;
import com.company.service.AttachService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AttachServiceImpl implements AttachService {

    private final AttachRepository attachRepository;

    @Override
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        try {
            int upload = upload(file);
            AttachDto attachDto = AttachDto.builder()
                    .id(upload)
                    .originalName(file.getOriginalFilename())
                    .build();
            log.info("Attach upload successfully");
            return buildResponse(attachDto, "success", true, 200);
        } catch (Exception e) {
            log.error("Error occurred {}", e.getMessage(), e.getCause());
            log.warn("Attach upload failed, Item not found");
            return buildResponse(null, "Item not found", false, 500);
        }
    }

    @Override
    public int upload(MultipartFile file) {
        String attachFolder = "images/";
        if (file.isEmpty()) {
            log.error("File is empty");
            return 0;
        }
        try {
//            String pathFolder = getYmDString(); // dddd/mm/dd
            String uuid = UUID.randomUUID().toString();
            String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));
            String fileName = uuid + "." + extension;

//            File folder = new File(attachFolder + pathFolder); // attaches/2024/05/04
//            if (!folder.exists()) {
//                folder.mkdirs();
//            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(attachFolder + fileName);
            Files.write(path, bytes);
            AttachEntity attach = attachRepository.save(
                    AttachEntity.builder()
                            .size(file.getSize())
                            .originalName(file.getOriginalFilename())
                            .extension(extension)
                            .fileName(fileName)
                            .path(String.valueOf(path.toAbsolutePath()))
                            .build()
            );
            return attach.getId();
        } catch (IOException e) {
            log.error("Error occurred {}", e.getMessage(), e.getCause());
        }
        return 0;
    }

    @Override
    public byte[] download(int id) {
        Optional<AttachEntity> image
                = attachRepository.findById(id);
        if (image.isEmpty()) {
            log.error("Attach id {} not found", id);
            return null;
        }
        String path = "images/" + image.get().getFileName();
        byte[] images;
        try {
            images = Files.readAllBytes(new File(path).toPath());
        } catch (IOException e) {
            log.error("Error occurred {}", e.getMessage(), e.getCause());
            return null;
        }
        return images;
    }

    @Override
    public ResponseEntity<?> removeFile(int id) {
        try {
            Optional<AttachEntity> attach = attachRepository.findById(id);
            if (attach.isEmpty()) {
                log.warn("Attach not found");
                return buildResponse(null, "Attach not found", false, 404);
            }
            attachRepository.deleteById(id);
            log.info("Deleting attach from path: {}", attach.get().getPath());
            Path path1 = Paths.get(attach.get().getPath());
            Files.delete(path1);
            log.info("File deleted successfully");
            return buildResponse(null, "success", true, 200);
        } catch (Exception e) {
            log.error("Error occurred {}", e.getMessage(), e.getCause());
            return buildResponse(null, "Something went wrong while deleting file", false, 500);
        }
    }

    private String getExtension(String fileName) { // mp3/jpg/png/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }
}
