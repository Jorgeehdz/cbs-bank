package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Geo;

public interface GeoRepository extends JpaRepository<Geo, Long>{

}
