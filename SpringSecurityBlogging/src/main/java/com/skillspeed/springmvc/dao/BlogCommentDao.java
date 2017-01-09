package com.skillspeed.springmvc.dao;

import java.util.List;

import com.skillspeed.springmvc.model.BlogComment;

public interface BlogCommentDao {

	void save(BlogComment comment);
	
	List<BlogComment> findCommentsById(int id);
}
