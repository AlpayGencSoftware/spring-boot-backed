package com.socialtime.line.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialtime.line.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
