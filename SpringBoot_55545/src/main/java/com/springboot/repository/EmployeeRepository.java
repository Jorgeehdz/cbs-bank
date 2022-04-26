package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query("select e from Employee e where e.city=?1")
	List<Employee> getByCity(String city);

	
	@Query(value = "select * from employee where city=?1",nativeQuery = true)
	List<Employee> getByCityNative(String city);
}
