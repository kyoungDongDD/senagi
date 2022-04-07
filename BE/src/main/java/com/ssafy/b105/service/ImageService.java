package com.ssafy.b105.service;


import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ImageService {

    private final Path rootLocation;

    public ImageService(@Value("${uploadPath.path}") String uploadPath) {
        this.rootLocation = Paths.get(uploadPath);
    }

    public Resource loadFile(String fileName) {
        return new FileSystemResource(rootLocation + "/" + fileName);
    }

}
