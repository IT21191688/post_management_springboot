package com.postmanagement.imageupload.controller;


import com.postmanagement.imageupload.dto.PostDTO;
import com.postmanagement.imageupload.services.PostService;
import com.postmanagement.imageupload.utill.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createPost")
    public ResponseEntity<StandardResponseEntity> savePost(@RequestParam("caption") String caption,
                                                           @RequestParam("image") MultipartFile imageFile) throws IOException {
        PostDTO postDTO = new PostDTO();
        postDTO.setCaption(caption);
        postDTO.setImageFile(imageFile);
        PostDTO postdto=postService.savePost(postDTO);

        StandardResponseEntity responseEntity = new StandardResponseEntity(HttpStatus.CREATED.value(), "Post saved successfully", postdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity);
    }

    @GetMapping("/getOnePost/{id}")
    public ResponseEntity<StandardResponseEntity> findPost(@PathVariable int id) {
        PostDTO postDTO = postService.findPost(id);
        StandardResponseEntity responseEntity = new StandardResponseEntity(HttpStatus.OK.value(), "Post found", postDTO);
        return ResponseEntity.ok(responseEntity);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<StandardResponseEntity> updatePost(@PathVariable int id,
                                                             @RequestParam("caption") String caption,
                                                             @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
        PostDTO postDTO = new PostDTO();
        postDTO.setCaption(caption);
        postDTO.setImageFile(imageFile);
        PostDTO postdto=postService.updatePost(postDTO, id);

        StandardResponseEntity responseEntity = new StandardResponseEntity(HttpStatus.OK.value(), "Post updated successfully", postdto);
        return ResponseEntity.ok(responseEntity);
    }

    @DeleteMapping("/deleteOne/{id}")
    public ResponseEntity<StandardResponseEntity> deletePost(@PathVariable int id) throws IOException {
        postService.deletePost(id);

        StandardResponseEntity responseEntity = new StandardResponseEntity(HttpStatus.OK.value(), "Post deleted successfully", null);
        return ResponseEntity.ok(responseEntity);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<StandardResponseEntity> findAllPosts() {
        List<PostDTO> posts = postService.findAllPosts();
        StandardResponseEntity responseEntity = new StandardResponseEntity(HttpStatus.OK.value(), "All posts retrieved successfully", posts);
        return ResponseEntity.ok(responseEntity);
    }
}
