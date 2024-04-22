package com.postmanagement.imageupload.utill;


import com.postmanagement.imageupload.dto.PostDTO;
import com.postmanagement.imageupload.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setCaption(postDTO.getCaption());
        return post;
    }

    public PostDTO toDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setCaption(post.getCaption());
        postDTO.setImageUri(post.getImage());
        postDTO.setCreatedAt(post.getCreatedAt());
        return postDTO;
    }
}