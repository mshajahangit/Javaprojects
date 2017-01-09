package com.skillspeed.springmvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillspeed.springmvc.dao.TweetDao;
import com.skillspeed.springmvc.model.Tweet;
import com.skillspeed.springmvc.service.TwitterService;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@EnableScheduling
@Service("twitterService")
@Transactional
public class TwitterServiceImpl implements TwitterService {
	
	@Autowired
    private Environment environment;

	@Autowired
	private TweetDao tweetDao;
	
	@Override
	@Scheduled(fixedRate = 1000000)
	public void getTweeterFeeds() {
		Twitter twitter = new TwitterFactory().getInstance();
		StringBuilder tweetFeedContent = new StringBuilder(); 
		List<Tweet> allTweets = findAllTweetword();
		AccessToken accessToken = new AccessToken(environment.getRequiredProperty("ACCESS_TOKEN"), environment.getRequiredProperty("ACCESS_TOKEN_SECRET"));
	    twitter.setOAuthConsumer(environment.getRequiredProperty("CONSUMER_KEY"), environment.getRequiredProperty("CONSUMER_SECRET"));
		twitter.setOAuthAccessToken(accessToken);
		if(allTweets.size() == 0){

			System.out.println("Tweet table empty");
		}else{
		    try {
		    	for(Tweet tweetFeed : allTweets){	    		
		    		System.out.println("tweet word = "+tweetFeed.getTweetword());
			        Query query = new Query(tweetFeed.getTweetword());
			        QueryResult result;
			        result = twitter.search(query);
			        List<Status> tweets = result.getTweets();
			        for (Status tweet : tweets) {			            
			        	tweetFeedContent.append("Date--"+tweet.getCreatedAt()+" @" + tweet.getUser().getScreenName() + " --- " + tweet.getText()+"<br>" );			          
			        }
			        System.out.println("tweetword  = "+tweetFeed.getTweetword() + "tweetFeedContent = "+tweetFeedContent);
			        Tweet tweet = findByTweetword(tweetFeed.getTweetword());
			        tweet.setTweetcontent(tweetFeedContent == null ? tweet.getTweetcontent() : tweetFeedContent.toString());
			        save(tweet);
			        tweetFeedContent = new StringBuilder();
			        System.out.println("Tweet saved successfully");
		    	}   
		    }
		    catch (TwitterException te) {
		        te.printStackTrace();
		        System.out.println("Failed to search tweets: " + te.getMessage());
		        
		    }
		}
	    System.out.println("tweetFeedContent = "+ tweetFeedContent);
	   
	}
	
	@Override
	public String getTweeterFeeds(String feed) {		
		Twitter twitter = new TwitterFactory().getInstance();		
		StringBuilder tweetFeedContent = new StringBuilder();	
		AccessToken accessToken = new AccessToken(environment.getRequiredProperty("ACCESS_TOKEN"), environment.getRequiredProperty("ACCESS_TOKEN_SECRET"));
	    twitter.setOAuthConsumer(environment.getRequiredProperty("CONSUMER_KEY"), environment.getRequiredProperty("CONSUMER_SECRET"));
		twitter.setOAuthAccessToken(accessToken);		
	    try {
		        Query query = new Query(feed);		        
		        QueryResult result;
		        result = twitter.search(query);
		        List<Status> tweets = result.getTweets();
		        for (Status tweet : tweets) {		            
		        	tweetFeedContent.append("Date--"+tweet.getCreatedAt()+" @" + tweet.getUser().getScreenName() + " --- " + tweet.getText()+"<br>" );
		        	System.out.println("tweetFeedContent = "+ tweetFeedContent);
		        }
		        Tweet tweet = findByTweetword(feed);
		        tweet.setTweetcontent(tweetFeedContent == null ? tweet.getTweetcontent() : tweetFeedContent.toString());
		        save(tweet);
		        	  
	    }
	    catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        
	    }   
	   return tweetFeedContent == null ? null : tweetFeedContent.toString();
	}

	@Override
	public Tweet findById(int id) {
		Tweet tweet = tweetDao.findById(id);
		return tweet;
	}

	@Override
	public Tweet findByTweetword(String tweetword) {
		Tweet tweet = tweetDao.findByTweetword(tweetword);
		return tweet;
	}

	@Override
	public void save(Tweet tweet) {
		tweetDao.save(tweet);		
	}

	@Override
	public List<Tweet> findAllTweetword() {
		List<Tweet> tweets = tweetDao.findAllTweetword();
		return tweets;
	}

}
