package com.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.company.service.AttachService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/attach")
@Slf4j
@Tag(name = "Attach")
@AllArgsConstructor
public class AttachController {

    private final AttachService attachService;

    @Operation(
            description = "Upload attach",
            summary = "upload endpoint of attach"
    )
    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        log.info("Received upload file request");
        return attachService.uploadFile(file);
    }

    @Operation(
            description = "Remove attach",
            summary = "remove endpoint of attach"
    )
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeFile(
            @PathVariable("id") int id
    ) {
        log.info("Received remove file request");
        return attachService.removeFile(id);
    }
}
