package com.skillspeed.springmvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillspeed.springmvc.dao.PostDao;
import com.skillspeed.springmvc.model.Post;
import com.skillspeed.springmvc.service.PostService;


@Service("postServiceImpl")
@Primary
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postRepository;

    @Override
    public List<Post> findAll() {
      //  return this.postRepository.findAll();
    	return null;
    }

    @Override
    public List<Post> findLatest5() {
        return this.postRepository.findLatest5Posts();
    }

    @Override
    public Post findById(int id) {
        return this.postRepository.findById(id);
    	
    }

    @Override
    public Post create(Post post) {
        return null;
    }

    @Override
    public Post edit(Post post) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

	@Override
	public void save(Post post) {
		postRepository.save(post);
		
	}
}
