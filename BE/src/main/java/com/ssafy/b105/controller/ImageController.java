package com.ssafy.b105.controller;

import com.ssafy.b105.dto.OCRDto;
import com.ssafy.b105.dto.ocr.OcrDataDto;
import com.ssafy.b105.dto.ocr.OcrResponseDto;
import com.ssafy.b105.service.ImageService;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final RestTemplate restTemplate;
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

    @PostMapping("/api/ocr")
    public ResponseEntity<?> sendOcr(@RequestBody OCRDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-OCR-SECRET","amZPVUZxdG1RempqR056UG9TTnBUZVhGV2ZSS2dUQWI=");

        HttpEntity<OCRDto<OcrDataDto>> request = new HttpEntity<>(dto, headers);
        OCRDto<OcrResponseDto> ocrDto = restTemplate.postForObject(
            "https://sjzq3u7j26.apigw.ntruss.com/custom/v1/14843/0c3307a350bcc0e2b944ccdb8fc49c191fcd75425562e54c2570aa65f0b29b65/document/receipt",
            request, OCRDto.class);
        return ResponseEntity.ok(ocrDto);
    }
}
