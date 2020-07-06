package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.DetailQuittance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface DetailQuittanceDao extends JpaRepository<DetailQuittance, Long>{

// DetailQuittance findByCodeProduitLike(String code);

}
	


