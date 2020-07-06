package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Contribuable;
import com.dgi.dsi.winregistre.entites.ContribuableBis;
import com.dgi.dsi.winregistre.entites.EXPORT_TABLE;
import com.dgi.dsi.winregistre.entites.Epargne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface ContribuableDao extends JpaRepository<ContribuableBis, Long> {

//    ContribuableBis findByCONT_IMMATREquals(String ifu);
//
//    List<ContribuableBis> findByCONT_RAISLike(String raisonSocial);
//
//    List<ContribuableBis> findByCONT_NOMLike(String nom);

   //exemple de requête
//    @Query("select u from Contribuablebis u where u.CONT_IMMATR = ?1")
//    ContribuableBis findByCONT_IMMATREquals(String ifu);

    //exécuter une requête sql native
    @Query(value = "SELECT * FROM contribuablebis WHERE cont_IMMATR = ?1", nativeQuery = true)
    ContribuableBis findByCONT_IMMATREquals(String ifu);

    //exécuter une requête sql native
    @Query(value = "SELECT * FROM contribuablebis WHERE cont_RAIS LIKE ?1", nativeQuery = true)
    List<ContribuableBis> findByCONT_RAISLike(String raisoc);

    //exécuter une requête sql native
    @Query(value = "SELECT * FROM contribuablebis WHERE cont_NOM LIKE ?1", nativeQuery = true)
    List<ContribuableBis> findByCONT_NOMLike(String nom);

//exemple de requête
//    @Query("select u from Contribuablebis u where u.CONT_RAIS like %?1")
//    List<ContribuableBis> findByCONT_RAISLike(String raisoc);
////exemple de requête
//    @Query("select u from Contribuablebis u where u.CONT_NOM like %?1")
//    List<ContribuableBis> findByCONT_NOMLike(String nom);

}
	


