package com.skillspeed.springmvc.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.skillspeed.springmvc.dao.AbstractDao;
import com.skillspeed.springmvc.dao.PostDao;
import com.skillspeed.springmvc.model.Post;

@Repository("postDao")
public class PostDaoImpl extends AbstractDao<Integer, Post> implements PostDao{
	
	
	@Override
	public List<Post> findLatest5Posts() {
		/*
		 * 
		 * Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		System.out.println("findLatest5Posts PostDaoImpl");
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("date"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		@SuppressWarnings("unchecked")
		List<Post> posts = (List<Post>) criteria.list();
		
		return posts;
	}
	

	public Post findById(int id) {
		System.out.println("before Reacahed to set");
		Post post = getByKey(id);
		/*if(user!=null){
			Hibernate.initialize(post.get.getUserProfiles());
		}*/
		System.out.println("Reacahed to set");

		return post;
	}


	@Override
	public void save(Post post) {
		persist(post);
		
	}
	
	
}
