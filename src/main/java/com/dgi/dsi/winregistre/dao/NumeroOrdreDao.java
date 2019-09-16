package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.NatureActe;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface NatureActeDao extends JpaRepository<NatureActe, Long>{

	NatureActe findByDesignationEquals(String designation);
	NatureActe findByCodeEquals(String designation);

	public NatureActe findByIdIs(String id);
	public NatureActe findByIdIs(Long id);

	@Query(value = "SELECT MAX(id) FROM natureacte WHERE categorie_acte_id = ?1", nativeQuery = true)
	Long maxIdByCategorie(Long idBordereau);


}
	


