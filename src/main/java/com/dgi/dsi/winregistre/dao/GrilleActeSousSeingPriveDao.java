package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Direction;
import com.dgi.dsi.winregistre.entites.GrilleActeSousSeingPrive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface GrilleActeSousSeingPriveDao extends JpaRepository<GrilleActeSousSeingPrive, Long> {

//    List<GrilleActeSousSeingPrive> findByDesignationLike(String designation);
//    GrilleActeSousSeingPrive findByDesignationEquals(String designation);
//

    public GrilleActeSousSeingPrive findByIdIs(String id);
    public GrilleActeSousSeingPrive findByIdIs(Long id);

    GrilleActeSousSeingPrive findByDelaiMaxEquals(Long delai);
    GrilleActeSousSeingPrive findByDelaiMaxGreaterThanAndDelaiMinLessThanEqual(Integer delaiMax, Integer delaiMin);

    @Query(value = "SELECT * FROM grille_acte_sous_seing_prive WHERE code = ?1", nativeQuery = true)
    GrilleActeSousSeingPrive grilleActeSousSeingPriveByCode(String code);


}
	


