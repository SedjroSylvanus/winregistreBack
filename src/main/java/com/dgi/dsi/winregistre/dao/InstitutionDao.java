package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.Institution;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface InstitutionDao extends JpaRepository<Institution, Long>{



    public Institution findByIdIs(String id);
    public Institution findByIdIs(Long id);
    Institution findByCodeLike(String code);
}
	


