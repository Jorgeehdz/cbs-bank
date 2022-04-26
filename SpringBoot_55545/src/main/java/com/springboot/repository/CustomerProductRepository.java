package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Customer;
import com.springboot.model.CustomerProduct;
import com.springboot.model.Product;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Long>{

	@Query("select c from CustomerProduct cp JOIN cp.customer c "
			+ "JOIN cp.product p where p.id=?1")
	List<Customer> getCustomerByProductId(Long pid);

	@Query("select p from CustomerProduct cp JOIN cp.customer c JOIN cp.product p where c.id=?1")
	List<Product> getProductByCustomerId(Long cid);

	@Query("select c from CustomerProduct cp "
			+ "JOIN cp.customer c "
			+ "JOIN cp.product p "
			+ "JOIN p.category ct "
			+ "where LOWER(ct.name)=LOWER(?1)")
	List<Customer> getCustomerByCategoryName(String cname);

	@Query("select p from CustomerProduct cp "
			+ "JOIN cp.customer c "
			+ "JOIN cp.product p "
			+ "where LOWER(c.city)=?1 OR LOWER(c.city)=?2")
	List<Product> getProductByCustomerCity(String city1,String city2);

}




