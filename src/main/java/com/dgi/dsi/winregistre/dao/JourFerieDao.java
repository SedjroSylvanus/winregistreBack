package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;


import com.dgi.dsi.winregistre.entites.JourFerie;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface JourFerieDao extends JpaRepository<JourFerie, Long>{
	
	
}
	


