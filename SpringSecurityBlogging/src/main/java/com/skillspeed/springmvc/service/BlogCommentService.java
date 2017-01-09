package com.skillspeed.springmvc.service;

import java.util.List;

import com.skillspeed.springmvc.model.BlogComment;

public interface BlogCommentService {

	void save(BlogComment comment);
	
	List<BlogComment> findCommentsById(int id);
}
