package com.firebase.postgresql.controller;

import com.firebase.postgresql.service.FirebaseImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/firebase-images")
public class FirebaseImageController {
    private final FirebaseImageService firebaseImageService;

    @PostMapping
    public ResponseEntity<?> createImage(@RequestParam("file") MultipartFile[] files){
        List<String> imageUrls = new ArrayList<>();
        for(MultipartFile file : files){
            try{
                String fileName = firebaseImageService.save(file);
                String imageUrl = firebaseImageService.getImageUrl(fileName);
                imageUrls.add(imageUrl);
            }catch(Exception e){
                e.printStackTrace();
                return new ResponseEntity<>("Error while uploading file: " + file.getOriginalFilename(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(imageUrls, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteImage(@RequestParam("fileName") String fileName) throws IOException {
        firebaseImageService.delete(fileName);
        return new ResponseEntity<>("delete image success", HttpStatus.OK);
    }
}
