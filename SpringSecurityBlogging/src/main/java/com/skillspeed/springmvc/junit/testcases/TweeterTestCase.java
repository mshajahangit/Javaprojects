package com.skillspeed.springmvc.junit.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.skillspeed.springmvc.configuration.AppConfig;
import com.skillspeed.springmvc.configuration.AppInitializer;
import com.skillspeed.springmvc.configuration.HibernateConfiguration;
import com.skillspeed.springmvc.service.TwitterService;


//@Category(Optional.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes  = {AppInitializer.class, AppConfig.class,HibernateConfiguration.class})
@WebAppConfiguration
public class TweeterTestCase {
	
	@Autowired
	TwitterService twitterService;
	
	@BeforeClass
	public static void beforeExecutingHelloWorldTestCases(){
		System.out.println("Before Executing HelloWorldTestCases :: ");
	}

	@AfterClass
	public static void afterExecutingHelloWorldTestCases(){
		System.out.println("\n\nAfter Executing all Test Cases HelloWorldTestCases :: ");
	}
	
	// Executed before every test case
	@Before
	public void beforeExecutingTestCase(){
		System.out.println("\n\nBefore Executing Test Case :: ");
		
	}
	
	@After
	public void afterExecutingTestCase(){
		System.out.println("After Executing Test Case :: ");
	}

	@Test
	public void testTweetword(){
		// Print a message before assertion
		System.out.println("Before Getting testTweetword #1 Started ::"+twitterService);
		String tweetfeed = null;
		try {
			assertEquals("",twitterService.getTweeterFeeds("Java"));
			
		} catch (Exception e) {
			System.out.println("Exception in tweet search " + e);
		}
		// If assertion goes wrong, the execution of Test would fail
		System.out.println(" tweetfeed = "+tweetfeed);
		
		
	}
	
	
	
}