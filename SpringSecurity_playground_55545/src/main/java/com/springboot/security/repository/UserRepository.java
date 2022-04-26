package com.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.security.model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{

	@Query("select u from UserInfo u where u.username=?1")
	UserInfo fetchUserByUsername(String username);

}
