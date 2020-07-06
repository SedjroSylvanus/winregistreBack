package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.dgi.dsi.winregistre.entites.DegreSuccession;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface DegreSuccessionDao extends JpaRepository<DegreSuccession,  Long>{



    public DegreSuccession findByIdIs(String id);
    public DegreSuccession findByIdIs(Long id);
    DegreSuccession findByCodeLike(String code);
}
	


