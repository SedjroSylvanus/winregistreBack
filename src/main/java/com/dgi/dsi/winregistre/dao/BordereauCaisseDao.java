package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.BordereauCaisse;



@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface BordereauCaisseDao extends JpaRepository<BordereauCaisse, Long>{
	
	
}
	


