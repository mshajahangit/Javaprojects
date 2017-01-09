package com.skillspeed.springmvc.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.skillspeed.springmvc.model.BlogComment;
import com.skillspeed.springmvc.model.Post;
import com.skillspeed.springmvc.model.Tweet;
import com.skillspeed.springmvc.model.User;
import com.skillspeed.springmvc.service.BlogCommentService;
import com.skillspeed.springmvc.service.PostService;
import com.skillspeed.springmvc.service.TwitterService;
import com.skillspeed.springmvc.service.UserService;

@Controller
@SessionAttributes("roles")
public class HomeController {

    @Autowired
    private PostService postService;
    
    @Autowired
    private BlogCommentService blogCommentService;
    
    @Autowired
	UserService userService;
    
    @Autowired
    TwitterService twitterService;

   /* @Autowired
    private NotificationService notificationService;*/

    @RequestMapping(value = { "/blogList" }, method = RequestMethod.GET)
    public String home(Model model) {
    	try {
    		BlogComment singleBlogComment =new BlogComment();
    		singleBlogComment.setComment("");
			List<BlogComment> blogComment = null;
			Map<String,String> tweetComments = new HashMap<String,String>();
			Map<Integer,List<BlogComment>> comments = new HashMap<Integer,List<BlogComment>>();
			List<Post> latest5Posts = postService.findLatest5();    	
			model.addAttribute("latest5posts", latest5Posts);
			for(Post post: latest5Posts){
				blogComment = blogCommentService.findCommentsById(post.getId().intValue());
				comments.put(post.getId().intValue(), blogComment);
			}
			if(blogComment!=null)
			model.addAttribute("blogComment", comments);
			model.addAttribute("newComment",singleBlogComment );
			for(Post postForTweetword : latest5Posts){
				String tweetword = postForTweetword.getTweetword();
				Tweet tweet = twitterService.findByTweetword(tweetword);		
				tweetComments.put(tweetword, tweet.getTweetcontent()!=null ? tweet.getTweetcontent() : "");
			}
			model.addAttribute("tweetComments", tweetComments);
			
		} catch (Exception e) {
			System.out.println("Exception = "+e);
			e.printStackTrace();
		}
        return "index";
    }
    @RequestMapping(value = { "/postBlog" }, method = RequestMethod.GET)
    public String getPostBlog(Model model) {
    	User user = new User();
		model.addAttribute("user", user);		
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("post", new Post());	
		return "postBlogPage";
    }
    @RequestMapping(value = { "/postBlog" }, method = RequestMethod.POST)
    public String UploadBlog(@Valid Post post,BindingResult result, ModelMap model) {
    	User user = userService.findBySSO(getPrincipal());    	
    	post.setAuthor(user);
    	post.setDate(new Date());
    	Tweet tweet = new Tweet();
    	tweet.setTweetword(post.getTweetword());    	
    	postService.save(post);
    	
    	Tweet tweetwordCheck = twitterService.findByTweetword(post.getTweetword());	
    	if(tweetwordCheck == null){
    		twitterService.save(tweet);
    		twitterService.getTweeterFeeds(post.getTweetword());
    	}
 
		return "redirect:/blogList";
    }

    @RequestMapping(value = { "/blogList-{id}" }, method = RequestMethod.POST)   
    public String saveCommentSection(@PathVariable("id") int postId, @Valid BlogComment newComment, Model model) {
    	try {			
			Post post = new Post();    	
			Post post1 = postService.findById(postId);			
			post.setId(post1.getId());
			post.setAuthor(post1.getAuthor());
			post.setBody(post1.getBody());
			post.setDate(post1.getDate());
			post.setTitle(post1.getTitle());
			//post1.setId(Long.valueOf(postId));
			newComment.setPost(post);
			newComment.setComment(newComment.getComment());			
			blogCommentService.save(newComment);			
		} catch (Exception e) {
			System.out.println("Exception saveCommentSection = "+ e);
			e.printStackTrace();
		}		
    	return "redirect:/blogList";
    }
    
    @RequestMapping("/comment-{postId}")
	public String saveComment(@PathVariable int postId,@Valid BlogComment newComment,BindingResult result, ModelMap model) {    	
    	Post post = new Post();
    	post.setId((postId));
    	newComment.setPost(post);    	
    	newComment.setComment(newComment.getComment());
    	newComment.setDate( Calendar.getInstance().getTime());
    	blogCommentService.save(newComment);    	
		return "redirect:/blogList";
	}
    
    @RequestMapping("/commentDummy-{postId}")
	public String saveCommentDummy(@PathVariable int postId,@Valid BlogComment newComment,BindingResult result, ModelMap model) {
    	Post post = new Post();
    	post.setId((postId));
    	newComment.setPost(post);    	
    	newComment.setComment(newComment.getComment());
    	blogCommentService.save(newComment);    	
		return "redirect:/blogList";
	}
    
    
    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id,
                       Model model) {
        Post post = postService.findById(id.intValue());

        if (post == null) {
           /* notificationService.addErrorMessage(
                    "Cannot find post: " + id);*/
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "/posts/index";
    }
    
    /**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	

}