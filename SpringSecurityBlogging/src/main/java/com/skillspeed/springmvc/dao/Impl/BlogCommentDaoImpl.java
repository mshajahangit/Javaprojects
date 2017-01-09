package com.skillspeed.springmvc.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skillspeed.springmvc.dao.AbstractDao;
import com.skillspeed.springmvc.dao.BlogCommentDao;
import com.skillspeed.springmvc.model.BlogComment;


@Repository("blogCommentDao")
public class BlogCommentDaoImpl extends AbstractDao<Integer, BlogComment>  implements BlogCommentDao {

	@Override
	public void save(BlogComment comment) {
		persist(comment);
		
	}

	@Override
	public List<BlogComment> findCommentsById(int id) {
		Criteria cr = getSession().createCriteria(BlogComment.class);
		System.out.println("findCommentsById ========= "+id);
		cr.add(Restrictions.eq("post.id", Integer.valueOf(id)));
		@SuppressWarnings("unchecked")
		List<BlogComment> results = cr.list();
		for(BlogComment blogcomment: results){			
				Hibernate.initialize(blogcomment.getPost());			
		}
		return results;	
	}

}
