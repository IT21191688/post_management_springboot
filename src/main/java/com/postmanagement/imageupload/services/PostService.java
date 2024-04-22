package com.postmanagement.imageupload.services;

import com.postmanagement.imageupload.dto.PostDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostDTO savePost(PostDTO postDTO) throws IOException;

    PostDTO findPost(int id);

    PostDTO updatePost(PostDTO postDTO, int id) throws IOException;

    void deletePost(int id) throws IOException;

    List<PostDTO> findAllPosts();
}
