package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;


import com.dgi.dsi.winregistre.entites.Tranche;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface TrancheDao extends JpaRepository<Tranche, Long>{


    public Tranche findByIdIs(String id);
    public Tranche findByIdIs(Long id);
}
	


