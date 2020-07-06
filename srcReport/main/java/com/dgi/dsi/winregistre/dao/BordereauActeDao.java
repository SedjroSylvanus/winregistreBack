package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.BordereauActe;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface BordereauActeDao extends JpaRepository<BordereauActe, Long>{
	
	BordereauActe findByNumeroEquals(String numero);
}
	


