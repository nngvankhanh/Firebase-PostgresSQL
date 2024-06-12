package com.firebase.postgresql.service;

import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface FirebaseImageService {
    String getImageUrl(String name);
    String save(MultipartFile file) throws IOException;
    String save(BufferedImage bufferedImage, String originalFileName) throws IOException;
    void delete(String name) throws IOException;
}
