package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface PenaliteAmendeDao extends JpaRepository<TypePenaliteAmende, Long>{
	
	
}
	


