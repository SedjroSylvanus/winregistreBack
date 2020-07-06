package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import com.dgi.dsi.winregistre.entites.Commune;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface CommuneDao extends JpaRepository<Commune, Long>{



    public Commune findByIdIs(String id);
    public Commune findByIdIs(Long id);
    Commune findByCodeEquals(String code);
    Commune findByDesignationEquals(String designation);
}
	


