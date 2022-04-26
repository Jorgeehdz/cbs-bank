package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.PostDto;
import com.springboot.model.Post;
import com.springboot.repository.PostRepository;

@RestController
public class PostController {

	/*
	 * API for batch inserts/ Multiple Inserts
	 */
	
	@Autowired
	private PostRepository postRepository; 
	
	@PostMapping("/post/batch")
	public void insertBatchPost(@RequestBody List<Post> posts) {
		postRepository.saveAll(posts);
	}
	
	@GetMapping("/posts")
	public PostDto getAllPosts(
			@RequestParam("page") Integer page, 
			@RequestParam("size") Integer size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Post> pageData = postRepository.findAll(pageable);
		
		List<Post> list = pageData.getContent(); 
		int totalPages =  pageData.getTotalPages();
		int totalRecords = totalPages * size; 
		
		PostDto postDto = new PostDto();
		postDto.setPageNumber(page);
		postDto.setSize(size);
		postDto.setTotalPages(totalPages);
		postDto.setTotalRecords(totalRecords);
		postDto.setPageData(list);
		return postDto;
	}
	
}




















