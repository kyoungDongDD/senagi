package com.ssafy.b105.controller;

import com.ssafy.b105.service.ImageService;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/api/imgs/{fileName}")
    public ResponseEntity<?> serveFile(@PathVariable String fileName){
        try {

            Resource resource = imageService.loadFile(fileName);

            return ResponseEntity.ok()
                .contentType(MediaType.valueOf(Files.probeContentType(Path.of(fileName))))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.builder("attachment")
                    .filename(fileName, StandardCharsets.UTF_8)
                    .build().toString())
                .body(resource);

        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
