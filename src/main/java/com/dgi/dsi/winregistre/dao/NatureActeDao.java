package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.CategorieActe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.NatureActe;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface NatureActeDao extends JpaRepository<NatureActe, Long>{

	NatureActe findByDesignationEquals(String designation);
	NatureActe findByCodeEquals(String code);

	public NatureActe findByIdIs(String id);
	public NatureActe findByIdIs(Long id);
//	public NatureActe findByCodeEquals(String code);

//    @Query(value = "SELECT MAX(id) FROM winregist.natureacte WHERE categorie_acte_id = ?1", nativeQuery = true)
    @Query(value = "select MAX(id) from winregist.natureacte  where code like  ?1", nativeQuery = true)
    Long maxIdByCategorieActe(String codeCategorieActe);


    @Query(value = "SELECT MAX(id) FROM natureacte WHERE categorie_acte_id = ?1", nativeQuery = true)
	Long maxIdByCategorie(Long idCategorieActe);


	CategorieActe findByCategorieActe_(String code);




}
	


