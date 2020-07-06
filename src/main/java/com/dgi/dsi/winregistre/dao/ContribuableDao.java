package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.ContribuableBis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface ContribuableDao extends JpaRepository<ContribuableBis, Long> {


    public ContribuableBis findByIdIs(String id);
    public ContribuableBis findByIdIs(Long id);
    

    @Query("select c from ContribuableBis c where c.contImmatr  like :keyBoard or c.contNom  like :keyBoard or c.contRais  like :keyBoard or c.contPren  like :keyBoard " )
    Page<ContribuableBis> listeConstriByPage( @Param("keyBoard") String keyBoard,  Pageable pageable);

//    public List<ContribuableBis> findAll();


//    ContribuableBis findByCONT_IMMATREquals(String ifu);
//
//    List<ContribuableBis> findByCONT_RAISLike(String raisonSocial);
//
//    List<ContribuableBis> findByCONT_NOMLike(String nom);

   //exemple de requête
//    @Query("select u from Contribuablebis u where u.CONT_IMMATR = ?1")
//    ContribuableBis findByCONT_IMMATREquals(String ifu);

    //exécuter une requête sql native
//    @Query(value = "SELECT * FROM public.contribuable_base WHERE cont_immatr = ?1", nativeQuery = true)
//    ContribuableBis getContribuableBisByIfu(String ifu);

    //exécuter une requête sql native
//    @Query(value = "SELECT * FROM contribuablebis WHERE cont_immatr = ?1", nativeQuery = true)
//    ContribuableBis getContribuableBisByIfu(String ifu);
    ContribuableBis findAllByContImmatr(String ifu);

    //exécuter une requête sql native
//    @Query(value = "SELECT * FROM `contribuablebis` WHERE `cont_rais` like ?1", nativeQuery = true)
//    List<ContribuableBis> findByCONT_RAISLike(String raisoc);
    List<ContribuableBis> findByContRaisLike(String raisoc);

    //exécuter une requête sql native
//    @Query(value = "SELECT * FROM contribuablebis WHERE cont_nom LIKE ?1", nativeQuery = true)
//    List<ContribuableBis> findByCONT_NOMLike(String nom);
    List<ContribuableBis> findByContNomLike(String nom);

//exemple de requête
//    @Query("select u from Contribuablebis u where u.CONT_RAIS like %?1")
//    List<ContribuableBis> findByCONT_RAISLike(String raisoc);
////exemple de requête
//    @Query("select u from Contribuablebis u where u.CONT_NOM like %?1")
//    List<ContribuableBis> findByCONT_NOMLike(String nom);

}
	


