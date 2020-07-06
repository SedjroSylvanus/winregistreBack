package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface QuittanceDao extends JpaRepository<Quittance, Long>{

    Quittance findByNumeroQuittanceLike(String code);

    @Query("select u from Quittance u where u.numeroQuittance = :numeroQuittance")
    Quittance findByNumeroActe(@Param("numeroQuittance") String numeroQuittance) ;


    Quittance findByNumeroQuittanceEquals(String numeroQuittance);

    @Query(value = "SELECT * FROM quittance WHERE bordereau_caisse_id = ?1", nativeQuery = true)
    List<Quittance> listQuittanceBordereauCaisse(Long idBordereauCaisse);

    @Query(value = "SELECT MAX(id) FROM quittance WHERE bordereau_caisse_id = ?1", nativeQuery = true)
    Long maxIdByBordereauCaisse(Long idBordereau);

    @Query(value = "SELECT MAX(id) FROM bordereaucaisse WHERE agent_id = ?1", nativeQuery = true)
    Long maxIdBordereauCaisseByAgent(Long idAgent);

    List<Quittance> findQuittanceByBordereauCaisseIs(BordereauCaisse bordereauCaisse);

}
	


