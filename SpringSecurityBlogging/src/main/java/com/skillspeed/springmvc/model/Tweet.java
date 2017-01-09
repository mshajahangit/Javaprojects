package com.skillspeed.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tweetfeed")
public class Tweet {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 
	 @Column(nullable = true, length = 255)
	    private String tweetword;
	 
	 @Column(nullable = true, length = 12000)
	    private String tweetcontent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTweetword() {
		return tweetword;
	}

	public void setTweetword(String tweetword) {
		this.tweetword = tweetword;
	}

	public String getTweetcontent() {
		return tweetcontent;
	}

	public void setTweetcontent(String tweetcontent) {
		this.tweetcontent = tweetcontent;
	}
}
