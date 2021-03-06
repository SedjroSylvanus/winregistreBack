package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.Produit;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ProduitDao extends JpaRepository<Produit, Long>{


    Produit findByCodeLike(String code);
}
	


