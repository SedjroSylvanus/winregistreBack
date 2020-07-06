package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.BordereauActe;
import org.springframework.data.repository.query.Param;

import java.util.List;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface BordereauActeDao extends JpaRepository<BordereauActe, Long>{
	
	BordereauActe findByNumeroEquals(String numero);
	BordereauActe findByNumeroLike(String numero);


	public BordereauActe findByIdIs(String id);
	public BordereauActe findByIdIs(Long id);

	@Query(value = "SELECT MAX(id) FROM winregist.bordereauacte WHERE numero LIKE ?1", nativeQuery = true)
	Long maxIdByBordereau(String numeroBorderau);


	@Query("select u from BordereauActe u where UPPER(u.numero) LIKE :natureActe and u.agent.id =:idAgent")
	List<BordereauActe> listeBordereauActeAgent(@Param("natureActe") String natureActe, @Param("idAgent") Long idAgent);

	List<BordereauActe> findBordereauActeByNumeroEqualsAndAgent_Id( String numero, Long idAgent);

	@Query("select u from BordereauActe u where  u.agent.id =:idAgent")
	List<BordereauActe> listeBordereauActeAgent( @Param("idAgent") Long idAgent);

	@Query("select u from BordereauActe u left join fetch u.agent a where u.numero LIKE :natureActe and a.id =:idAgent")
	List<BordereauActe> ListeBordereauActeAgentBis(@Param("natureActe") String natureActe, @Param("idAgent") Long idAgent);

	@Query("select u from BordereauActe u where u.validated =:validation and u.sourceInterne =:sourceInterne and u.numero like :numBordereau" )
	List<BordereauActe> ListeBordereauActeAuthentiqueValide(@Param("validation") Boolean validation, @Param("sourceInterne") Boolean sourceInterne, @Param("numBordereau") String numBordereau);

	@Query("select b from BordereauActe b where b.transferred =:transferred  and b.numero like :numBordereau" )
	List<BordereauActe> ListeBordereauActeAuthentiqueTransferer(@Param("transferred") Boolean transferred, @Param("numBordereau") String numBordereau);


//	@Query(value = "SELECT MAX(id) FROM Acte WHERE bordereau_caisse_id = ?1 AND numero_quittance like ?2 ")
//	Long maxIdByBordereauCaisse(Long idBordereau, String codeNatureImpot);

}
	


