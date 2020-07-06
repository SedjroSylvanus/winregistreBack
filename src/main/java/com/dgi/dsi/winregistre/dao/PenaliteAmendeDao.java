package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface PenaliteAmendeDao extends JpaRepository<TypePenaliteAmende, Long>{


    public TypePenaliteAmende findByIdIs(String id);
    public TypePenaliteAmende findByIdIs(Long id);

    //exécuter une requête sql native
//    @Query(value = "SELECT * FROM type_penalite_amende WHERE code = ?1", nativeQuery = true)
//    TypePenaliteAmende typePenaliteAmendeByCode(String code);
    TypePenaliteAmende findTypePenaliteAmendeByCode(String code);

    //exécuter une requête sql native
//    @Query(value = "SELECT * FROM type_penalite_amende WHERE designation = ?1", nativeQuery = true)
//    TypePenaliteAmende typePenaliteAmendeByDesignation(String designation);
    TypePenaliteAmende findTypePenaliteAmendeByDesignation(String designation);


}
	


