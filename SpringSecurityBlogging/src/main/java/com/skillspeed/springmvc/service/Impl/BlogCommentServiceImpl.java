package com.skillspeed.springmvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillspeed.springmvc.dao.BlogCommentDao;
import com.skillspeed.springmvc.model.BlogComment;
import com.skillspeed.springmvc.service.BlogCommentService;

@Service("blogCommentService")
@Transactional
public class BlogCommentServiceImpl implements BlogCommentService {
	
	@Autowired
	private BlogCommentDao blogCommentDao;

	@Override
	public void save(BlogComment comment) {
		blogCommentDao.save(comment);
		
	}

	@Override
	public List<BlogComment> findCommentsById(int id) {
		List<BlogComment> blogComments = blogCommentDao.findCommentsById(id);
		return blogComments;
	}

}
