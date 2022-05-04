package com.socialtime.line.responses;

import com.socialtime.line.entities.Post;

import lombok.Data;

@Data
public class PostResponse {
	 Long Id;
	 Long userId;
	 String userName;
	 String text;
	 String title;  
	 
	 public PostResponse(Post entity) {
		 this.Id=entity.getId();
		 this.userId=entity.getUser().getId();
		 this.userName= entity.getUser().getUserName();
		 this.title=entity.getTitle();
		 this.text= entity.getText();
	 }
}
