package com.socialtime.line.services;

import java.util.List;
import java.util.Optional;import javax.websocket.Decoder.Text;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.socialtime.line.entities.Comment;
import com.socialtime.line.entities.Post;
import com.socialtime.line.entities.User;
import com.socialtime.line.repos.CommentRepository;
import com.socialtime.line.requests.CommentCreateRequest;
import com.socialtime.line.requests.CommentUpdateRequest;

@Service
public class CommentService {
  private CommentRepository commentRepository;
  private UserService userService;
  private PostService postservice;
  
 
public CommentService(CommentRepository commentRepository, UserService userService, PostService postservice) {
	 
	this.commentRepository = commentRepository;
	this.userService = userService;
	this.postservice = postservice;
}

public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
	 if (userId.isPresent() && postId.isPresent()) {
		return commentRepository.findByUserIdAndPostId(userId.get(), userId.get());
	}else if(userId.isPresent()){
		return commentRepository.findByUserId(userId.get());
	}else if(postId.isPresent()) {
		return commentRepository.findByPostId(postId.get());
	}else  
		return commentRepository.findAll(); 
 }

public Comment getOneCommentById(Long commentId) { 
	
		return commentRepository.findById(commentId).orElse(null) ;
	}

	public Comment createOneComment(CommentCreateRequest request) {
		User user= userService.getOneUserById(request.getUserId());
		Post post=postservice.getOnePostById(request.getPostId());
		if (user!=null && post!=null) {
			Comment commentToSave=  new Comment();
			commentToSave.setId(request.getId());
			commentToSave.setPost(post);
			commentToSave.setUser(user);
			commentToSave.setText(request.getText());
			return commentRepository.save(commentToSave);
		}else
		return null;
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
		 Optional<Comment> comment= commentRepository.findById(commentId);
		 if (comment.isPresent()) {
			Comment commentToUpdate=comment.get();
			commentToUpdate.setText(request.getText());
			return commentRepository.save(commentToUpdate); 
			}
		 else 
		 return null;
	}

	public void deleteOneCommentById(Long commentId) {
		 commentRepository.deleteById(commentId);
		
	}
	
	
}
