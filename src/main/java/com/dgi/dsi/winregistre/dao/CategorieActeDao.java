package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.BordereauActe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.CategorieActe;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface CategorieActeDao extends JpaRepository<CategorieActe, Long>{





    public CategorieActe findByIdIs(String id);
    public CategorieActe findByIdIs(Long id);
    CategorieActe findByCodeEquals(String codeCategorie);
    CategorieActe findByCodeLike(String code);



}
	


