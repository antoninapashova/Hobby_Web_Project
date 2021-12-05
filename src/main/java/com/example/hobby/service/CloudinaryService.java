package com.example.hobby.service;

import com.example.hobby.service.impl.CloudinaryImage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    CloudinaryImage upload(MultipartFile file) throws IOException;

    boolean delete(String publicId);


}
