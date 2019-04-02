package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;


import com.dgi.dsi.winregistre.entites.PenaliteAmende;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface PenaliteAmendeDao extends JpaRepository<PenaliteAmende, Long>{
	
	
}
	


