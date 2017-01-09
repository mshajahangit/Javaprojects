package com.skillspeed.springmvc.dao;

import java.util.List;

import com.skillspeed.springmvc.model.Post;

public interface PostDao {

	List<Post> findLatest5Posts();
	
	Post findById(int id);
	
	void save(Post post);
}
