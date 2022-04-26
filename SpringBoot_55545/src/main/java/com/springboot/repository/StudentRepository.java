package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query("select s from Student s JOIN s.department d where d.id=?1")
	List<Student> fetchByDepartmentId(Long did);

}
