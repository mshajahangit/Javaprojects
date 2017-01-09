package com.skillspeed.springmvc.service;

import com.skillspeed.springmvc.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    List<Post> findLatest5();
    Post findById(int id);
    Post create(Post post);
    Post edit(Post post);
    void deleteById(Long id);
    void save(Post post);
}
