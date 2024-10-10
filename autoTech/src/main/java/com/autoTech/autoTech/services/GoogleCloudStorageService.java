package com.autoTech.autoTech.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.WriteChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

@Service
public class GoogleCloudStorageService {

    private final Storage storage;
    private final String BUCKET_NAME = "auto_tech_1";

    public GoogleCloudStorageService() throws IOException {
        // Load service account credentials from JSON key file
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\Asus\\Downloads\\autotech-437714-dd7fb2e06909.json"))
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
        
        storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        BlobInfo blobInfo = BlobInfo.newBuilder(BUCKET_NAME, uniqueFileName)
                .setContentType(file.getContentType())
                .build();
        try (WriteChannel writer = storage.writer(blobInfo);
             var inputStream = file.getInputStream()) {
            byte[] buffer = new byte[1024];
            int limit;
            while ((limit = inputStream.read(buffer)) >= 0) {
                writer.write(ByteBuffer.wrap(buffer, 0, limit));
            }
        }

        return String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, uniqueFileName);
    }

    public void deleteImage(String imageUrl) {
        String fileName = extractFileNameFromUrl(imageUrl);

        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);

        boolean deleted = storage.delete(blobId);

        if (!deleted) {
            throw new IllegalArgumentException("Failed to delete image. File not found: " + imageUrl);
        }
    }

    private String extractFileNameFromUrl(String imageUrl) {
        int lastIndex = imageUrl.lastIndexOf('/');
        if (lastIndex == -1) {
            throw new IllegalArgumentException("Invalid Google Cloud Storage URL: " + imageUrl);
        }
        return imageUrl.substring(lastIndex + 1);
    }
}
