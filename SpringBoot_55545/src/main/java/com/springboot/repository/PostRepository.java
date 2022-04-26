package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
