package com.postmanagement.imageupload.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "post")
@Entity
public class Post {
    @Id
    private int id;
    private String caption;
    private String image;
    @CreationTimestamp
    private LocalDateTime createdAt;
}