package com.skillspeed.springmvc.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 300)
    private String title;

    @Lob @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;

    @Column(nullable = false)
    private Date date = new Date();
    
    @Column(nullable = false, length = 30)
    private String tweetword;
    
    @Column(nullable = false, length = 255)
    private String image;   


    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTweetword() {
		return tweetword;
	}

	public void setTweetword(String tweetword) {
		this.tweetword = tweetword;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Post() {}

    public Post(Integer id, String title, String body, User author, String tweetword, String image) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.tweetword = tweetword;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", tweetword=" + tweetword +
                ", image=" + image +
                ", date=" + date +
                '}';
    }
}
