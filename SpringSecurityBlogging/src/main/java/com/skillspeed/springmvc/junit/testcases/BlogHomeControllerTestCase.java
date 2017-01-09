package com.skillspeed.springmvc.junit.testcases;

import net.sourceforge.jwebunit.junit.JWebUnit;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class BlogHomeControllerTestCase {
	
	@Before
	public void prepareBaseURL(){
		// Set base URL that JWebUnit will start from
		JWebUnit.setBaseUrl("http://localhost:8080/SpringSecurityBlogging/");
	} 
	
	@Test
	public void testBaseUrl(){
		// Begin a conversation
		JWebUnit.beginAt("/blogList/");
	}

	@Test
	public void testPostBlogButtonOnBlogPage(){
		// Check if the Post a Blog button is exist
		JWebUnit.beginAt("/blogList/");
	    JWebUnit.assertLinkPresent("Post a Blog");

	}

}
