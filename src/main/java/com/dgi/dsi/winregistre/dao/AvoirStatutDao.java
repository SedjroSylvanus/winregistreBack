package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.AvoirStatut;



@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface AvoirStatutDao extends JpaRepository<AvoirStatut, Long>{


    public AvoirStatut findByIdIs(String id);
    public AvoirStatut findByIdIs(Long id);
}
	


