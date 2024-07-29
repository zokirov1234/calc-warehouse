package com.company.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.company.model.dto.AttachDto;
import com.company.model.dto.ResponseDto;
import com.company.model.entity.AttachEntity;
import com.company.repository.AttachRepository;
import com.company.service.AttachService;
import com.company.util.BaseUtil;
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

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AttachServiceImpl implements AttachService {

    /*
     * mapper,
     *
     * */


    private final AttachRepository attachRepository;
    private final BaseUtil baseUtil;


    @Override
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        ResponseDto<?> response;
        try {
            int upload = upload(file);

            AttachDto attachDto = AttachDto.builder()
                    .id(upload)
                    .originalName(file.getOriginalFilename())
                    .build();
            log.info("Attach upload successfully");
            response = baseUtil.convertResponseDto(attachDto, "success", true, 200);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Attach upload failed, Item not found");
            response = baseUtil.convertResponseDto(null, "Item not found", false, 500);
            return ResponseEntity.status(500).body(response);
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
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public byte[] download(int id) {

        Optional<AttachEntity> image
                = attachRepository.findById(id);

        String path = "images/" + image.get().getFileName();
        byte[] images;
        try {
            images = Files.readAllBytes(new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return images;
    }

    @Override
    public ResponseEntity<?> removeFile(int id) {

        ResponseDto<?> response;
        try {
            Optional<AttachEntity> attach = attachRepository.findById(id);

            if (attach.isEmpty()) {
                log.warn("Attach not found");
                response = baseUtil.convertResponseDto(null, "Attach not found", false, 404);
                return ResponseEntity.status(404).body(response);
            }

            attachRepository.deleteById(id);


            log.info("Deleting attach from path: {}", attach.get().getPath());

            Path path1 = Paths.get(attach.get().getPath());

            Files.delete(path1);
            log.info("File deleted successfully");
            response = baseUtil.convertResponseDto(null, "success", true, 200);
            return ResponseEntity.status(200).body(response);


        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Something went wrong while deleting file {}", id);
            response = baseUtil.convertResponseDto(null, "Something went wrong while deleting file", false, 500);
            return ResponseEntity.status(500).body(response);
        }
    }

    private String getExtension(String fileName) { // mp3/jpg/png/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }
}
