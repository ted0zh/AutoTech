package com.autoTech.autoTech.services;

import com.autoTech.autoTech.data.models.AutoShop;
import com.autoTech.autoTech.data.models.Image;
import com.autoTech.autoTech.repositories.AutoShopRepo;
import com.autoTech.autoTech.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final AutoShopRepo autoShopRepo;
    private final ImageRepository imageRepository;
    private final GoogleCloudStorageService googleCloudStorageService;

    @Autowired
    public ImageService(AutoShopRepo autoShopRepo, ImageRepository imageRepository, GoogleCloudStorageService googleCloudStorageService) {
        this.autoShopRepo = autoShopRepo;
        this.imageRepository = imageRepository;
        this.googleCloudStorageService = googleCloudStorageService;
    }

    public Image uploadImage(MultipartFile file, String autoShopName) throws IOException {
        String imageUrl = googleCloudStorageService.uploadImage(file);

        AutoShop autoShop = autoShopRepo.findAutoShopByShopName(autoShopName)
                .orElseThrow(() -> new RuntimeException("AutoShop not found"));

        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageUrl(imageUrl);


        return imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));

        googleCloudStorageService.deleteImage(image.getImageUrl());

        imageRepository.delete(image);

    }
}
