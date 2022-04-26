package com.cbs.banking.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cbs.banking.model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{

	@Query("select u from UserInfo u where u.username=?1 AND u.active=?2")
	UserInfo fetchByUserName(String username,boolean active);

	@Query("select u.securityQuestion from UserInfo u where u.username=?1 AND u.active=?2")
	String fetchSecurityQuestionByUsername(String username,boolean active);

	@Query("select u from UserInfo u where u.username=?1 AND u.securityAnswer=?2")
	UserInfo checkSecurityAnswer(String username, String answer);

	@Transactional
	@Modifying
	@Query("UPDATE UserInfo u SET u.password=?2 where u.username=?1")
	void updatePassword(String username, String encodedPassword);

}
