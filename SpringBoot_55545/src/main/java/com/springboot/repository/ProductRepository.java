package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select p from Product p JOIN p.category c where c.id=?1")
	List<Product> fetchProductsByCategoryId(Long cid);

	@Query("select p from Product p JOIN p.category c where LOWER(c.name)=?1")
	List<Product> fetchProductsByCategoryName(String cname);

}
