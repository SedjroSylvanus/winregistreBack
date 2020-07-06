package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.NumeroOrdre;
import com.dgi.dsi.winregistre.entites.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface NumeroOrdreDao extends JpaRepository<NumeroOrdre, Long>{


	@Query("select distinct b.numeroOrdre from NumeroOrdre b where UPPER(b.service.code) =:codeService " )
	Integer numeroOrdreService(@Param("codeService") String codeService);


	@Query("select distinct b from NumeroOrdre b where UPPER(b.service.code) =:codeService " )
	NumeroOrdre numeroOrdreServiceObjet(@Param("codeService") String codeService);


	public NumeroOrdre findByIdIs(String id);
	public NumeroOrdre findByIdIs(Long id);

//	@Query(value = "SELECT MAX(id) FROM natureacte WHERE categorie_acte_id = ?1", nativeQuery = true)
//	Long maxIdByCategorie(Long idBordereau);


}
	


