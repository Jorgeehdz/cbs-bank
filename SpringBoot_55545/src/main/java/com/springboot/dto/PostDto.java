package com.springboot.dto;

import java.util.List;

import com.springboot.model.Post;

public class PostDto {
	int totalPages;
	int totalRecords;
	int pageNumber;
	int size;
	List<Post> pageData;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Post> getPageData() {
		return pageData;
	}

	public void setPageData(List<Post> pageData) {
		this.pageData = pageData;
	}

}
