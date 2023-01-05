package com.socialmeli.be_java_hisp_w20_g03.repository;

import com.socialmeli.be_java_hisp_w20_g03.model.Post;

import java.util.List;

public interface IPostRepository {
    boolean addPost(Post post);
    List<Post> getPosts();
}
