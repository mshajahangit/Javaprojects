package com.skillspeed.springmvc.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "blog_comment")
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String comment;
    
    @Column(nullable = false)
    private Date date = new Date();

    @ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
    private Post post;


    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   

    public BlogComment() {}

    public BlogComment(Integer id, String comment, Post post,Date date) {
        this.id = id;
        this.comment = comment;
        this.post = post;
        this.date=date;
    }

    public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
    public String toString() {
        return "BlogComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", post_id=" + post +
                 ", date=" + date +
                '}';
    }
}
