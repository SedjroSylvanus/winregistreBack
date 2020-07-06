package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import com.dgi.dsi.winregistre.entites.Departement;

import java.util.List;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface DepartementDao extends JpaRepository<Departement, Long>{

    public Departement findByCode(String code);

    Departement findByCodeLike(String code);

}
	


