package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Direction;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface DirectionDao extends JpaRepository<Direction, Long>{


    public Direction findByIdIs(String id);
    public Direction findByIdIs(Long id);

    //exécuter une requête sql native
    @Query(value = "SELECT * FROM direction WHERE code = ?1", nativeQuery = true)
    Direction directionByCode(String code);


    Direction findByCodeLike(String code);
}
	


