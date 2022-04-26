package com.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.security.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
