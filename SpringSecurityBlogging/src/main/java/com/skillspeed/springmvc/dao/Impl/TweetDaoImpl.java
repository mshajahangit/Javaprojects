package com.skillspeed.springmvc.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skillspeed.springmvc.dao.AbstractDao;
import com.skillspeed.springmvc.dao.TweetDao;
import com.skillspeed.springmvc.model.Tweet;

@Repository("tweetDao")
public class TweetDaoImpl extends AbstractDao<Integer, Tweet> implements TweetDao {

	@Override
	public Tweet findById(int id) {
		Tweet tweet = getByKey(id);		
		return tweet;
	}

	@Override
	public Tweet findByTweetword(String tweetword) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("tweetword", tweetword));
		Tweet tweet = (Tweet)crit.uniqueResult();
		return tweet;
	}

	@Override
	public void save(Tweet tweet) {
		persist(tweet);
	}

	@Override
	public List<Tweet> findAllTweetword() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("tweetword"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		@SuppressWarnings("unchecked")
		List<Tweet> tweets = (List<Tweet>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return tweets;
	}

}
