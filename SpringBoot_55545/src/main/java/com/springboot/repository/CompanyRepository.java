package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
