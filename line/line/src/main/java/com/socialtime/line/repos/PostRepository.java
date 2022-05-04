package com.socialtime.line.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialtime.line.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> { 
  List<Post> findByUserId(Long userId); 
}
