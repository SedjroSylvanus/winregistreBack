package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;


import com.dgi.dsi.winregistre.entites.JourFerie;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Max;
import java.util.List;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface JourFerieDao extends JpaRepository<JourFerie, Long>{



    public JourFerie findByIdIs(String id);
    public JourFerie findByIdIs(Long id);
    JourFerie findByCodeLike(String code);
    List<JourFerie> findAll();
    @Query("select u from JourFerie u ")
    List<JourFerie> listJourFerieBase();

}
	


