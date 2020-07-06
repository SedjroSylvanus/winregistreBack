package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Epargne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface EpargneDao extends JpaRepository<Epargne, Long> {


}
	


