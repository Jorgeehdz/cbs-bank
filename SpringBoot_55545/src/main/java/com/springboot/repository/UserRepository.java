package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Post;
import com.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select p from Post p where p.userId IN "
			+ " (select u.id from User u "
			+ " where LOWER(u.username)=LOWER(?1))")
	Page<Post> getPostsByUsername(String username, Pageable pageable);

}
