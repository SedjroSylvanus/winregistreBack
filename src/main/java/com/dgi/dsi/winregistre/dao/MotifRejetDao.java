package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.MotifRejet;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface MotifRejetDao extends JpaRepository<MotifRejet, Long>{
	
	
}
	


