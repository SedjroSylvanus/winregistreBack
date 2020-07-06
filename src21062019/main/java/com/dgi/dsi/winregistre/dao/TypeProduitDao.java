package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface TypeProduitDao extends JpaRepository<TypeProduit, Long>{


    TypeProduit findByCodeLike(String code);
}
	


