package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import com.dgi.dsi.winregistre.entites.Exercice;
import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ExerciceDao extends JpaRepository<Exercice, Long>{


    public Exercice findByIdIs(String id);
    public Exercice findByIdIs(Long id);
}
	


