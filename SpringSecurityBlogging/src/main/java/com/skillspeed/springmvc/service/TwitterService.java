package com.skillspeed.springmvc.service;

import java.util.List;

import com.skillspeed.springmvc.model.Tweet;

public interface TwitterService {

	void getTweeterFeeds();
	
	Tweet findById(int id);
	
	Tweet findByTweetword(String tweetword);
	
	void save(Tweet tweet);
	
	List<Tweet> findAllTweetword();
	
	String getTweeterFeeds(String feed);
}
