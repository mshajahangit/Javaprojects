package com.skillspeed.springmvc.dao;

import java.util.List;

import com.skillspeed.springmvc.model.Tweet;

public interface TweetDao {

	Tweet findById(int id);
	
	Tweet findByTweetword(String tweetword);
	
	void save(Tweet tweet);
	
	List<Tweet> findAllTweetword();
}
