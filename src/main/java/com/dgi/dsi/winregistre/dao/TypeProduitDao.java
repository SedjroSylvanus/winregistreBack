package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface TypeProduitDao extends JpaRepository<TypeProduit, Long>{



    public TypeProduit findByIdIs(String id);
    public TypeProduit findByIdIs(Long id);
    TypeProduit findByCodeLike(String code);
}
	


