package com.firebase.postgresql.service.impl;

import com.firebase.postgresql.service.FirebaseImageService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseImageServiceImpl implements FirebaseImageService {
    @Value("${firebase.image-url}")
    private String imageUrl;
    @Override
    public String getImageUrl(String name) {
        return String.format(imageUrl, name);
    }

    @Override
    public String save(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String name = generateFileName(file.getOriginalFilename());
        bucket.create(name, file.getBytes(), file.getContentType());
        return name;
    }

    @Override
    public String save(BufferedImage bufferedImage, String originalFileName) throws IOException {
        byte[] bytes = getByteArrays(bufferedImage, getExtension(originalFileName));
        Bucket bucket = StorageClient.getInstance().bucket();
        String name = generateFileName(originalFileName);
        bucket.create(name, bytes);
        return name;
    }

    @Override
    public void delete(String name) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        if(StringUtils.isEmpty(name)){
            throw new IOException("invalid file name");
        }
        Blob blob = bucket.get(name);
        if(blob == null){
            throw new IOException("file not found");
        }
        blob.delete();
    }
    String getExtension(String originalFileName){
        return StringUtils.getFilenameExtension(originalFileName);
    }
    String generateFileName(String originalFileName){
        return UUID.randomUUID().toString() + getExtension(originalFileName);
    }
    byte[] getByteArrays(BufferedImage bufferedImage, String format) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ImageIO.write(bufferedImage, format, baos);
            baos.flush();
            return baos.toByteArray();
        }catch(IOException e){
            throw e;
        }finally {
            baos.close();
        }
    }
}
