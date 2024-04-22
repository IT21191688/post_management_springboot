package com.postmanagement.imageupload.services.Impl;

import com.postmanagement.imageupload.dto.PostDTO;
import com.postmanagement.imageupload.entity.Post;
import com.postmanagement.imageupload.repository.PostRepository;
import com.postmanagement.imageupload.services.PostService;
import com.postmanagement.imageupload.utill.CloudinaryService;
import com.postmanagement.imageupload.utill.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CloudinaryService cloudinaryService;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, CloudinaryService cloudinaryService, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.cloudinaryService = cloudinaryService;
        this.postMapper = postMapper;
    }

    @Override
    public PostDTO savePost(PostDTO postDTO) throws IOException {
        MultipartFile imageFile = postDTO.getImageFile();
        String imageUri = cloudinaryService.uploadImageAndGetUri(imageFile, "posts");

        Post post = postMapper.toEntity(postDTO);
        post.setImage(imageUri);

        Post savedPost = postRepository.save(post);
        return postMapper.toDto(savedPost);
    }

    @Override
    public PostDTO findPost(int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return postMapper.toDto(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, int id) throws IOException {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        if (postDTO.getImageFile() != null) {
            cloudinaryService.deleteImageByUri(existingPost.getImage());
            String imageUri = cloudinaryService.uploadImageAndGetUri(postDTO.getImageFile(), "posts");
            existingPost.setImage(imageUri);
        }

        existingPost.setCaption(postDTO.getCaption());

        Post updatedPost = postRepository.save(existingPost);
        return postMapper.toDto(updatedPost);
    }

    @Override
    public void deletePost(int id) throws IOException {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        cloudinaryService.deleteImageByUri(post.getImage());
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }
}
