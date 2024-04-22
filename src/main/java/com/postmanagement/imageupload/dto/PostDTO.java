package com.postmanagement.imageupload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {
    private int id;
    private String caption;
    private String imageUri; // Changed from File to String
    private LocalDateTime createdAt;
    private MultipartFile imageFile; // Added MultipartFile field

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}