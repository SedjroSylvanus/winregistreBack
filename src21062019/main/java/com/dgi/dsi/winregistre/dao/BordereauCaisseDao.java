package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.BordereauCaisse;
import org.springframework.data.repository.query.Param;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface BordereauCaisseDao extends JpaRepository<BordereauCaisse, Long>{

    @Query("select u from BordereauCaisse u where u.numero = :numero ")
    BordereauCaisse findByNumeroEquals(@Param("numero") String numero);


//    BordereauCaisse findByNumeroEquals(String numero);

}
	


