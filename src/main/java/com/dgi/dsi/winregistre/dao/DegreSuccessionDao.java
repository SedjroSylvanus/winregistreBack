package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.dgi.dsi.winregistre.entites.DegreSuccession;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface DegreSuccessionDao extends JpaRepository<DegreSuccession,  Long>{


    DegreSuccession findByCodeLike(String code);
}
	


