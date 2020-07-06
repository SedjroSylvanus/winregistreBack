package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;


import com.dgi.dsi.winregistre.entites.ModePaiement;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ModePaiementDao extends JpaRepository<ModePaiement, Long>{


    public ModePaiement findByIdIs(String id);
    public ModePaiement findByIdIs(Long id);

    ModePaiement findByCodeLike(String code);
    ModePaiement findByDesignationEquals(String designation);
}
	


