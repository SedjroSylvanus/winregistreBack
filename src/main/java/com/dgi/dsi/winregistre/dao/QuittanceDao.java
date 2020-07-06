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


    public Quittance findByIdIs(String id);
    public Quittance findByIdIs(Long id);

    

    @Query("select u from Quittance u where u.numeroQuittance = :numeroQuittance")
    Quittance getQuittanceByNumeroQuittance(@Param("numeroQuittance") String numeroQuittance) ;
    
    @Query("select u from Quittance u where u.numeroQuittance = :numeroQuittance")
    Quittance findByNumeroActe(@Param("numeroQuittance") String numeroQuittance) ;

    @Query("select u from Quittance u where u.numeroQuittance = :numeroQuittance")
    List<Quittance> findByNumeroActeBis(@Param("numeroQuittance") String numeroQuittance) ;

    @Query("select u from Quittance u where u.numeroQuittance = :numeroQuittance")
    List<Quittance> findByNumeroQuittance(@Param("numeroQuittance") String numeroQuittance) ;



//    @Query("SELECT MAX(u.id) FROM Quittance u WHERE u.bordereauCaisse =: idBordereau")
    
    @Query(value = "SELECT MAX(id) FROM winregist.quittance WHERE bordereau_caisse_id = ?1", nativeQuery = true)
    Long maxIdByBordereauCaisse( Long idBordereau);


  
  @Query(value = "SELECT MAX(id) FROM winregist.bordereaucaisse WHERE agent_id = ?1", nativeQuery = true)
  Long maxBordereauCaisseAgentAvecQuittance( Long idAgent);
    
    Quittance findByNumeroQuittanceEquals(String numeroQuittance);

//    @Query("SELECT u FROM Quittance u WHERE u.bordereauCaisse =: idBordereauCaisse")
    @Query(value = "SELECT * FROM winregist.quittance WHERE bordereau_caisse_id = ?1", nativeQuery = true)
    List<Quittance> listQuittanceBordereauCaisse( Long idBordereauCaisse);

//    @Query("SELECT MAX(u.id) FROM Quittance u WHERE u.bordereauCaisse =: idBordereau and u.numeroQuittance like :codeNatureImpot")
    @Query(value = "SELECT MAX(id) FROM winregist.quittance WHERE bordereau_caisse_id = ?1 AND numero_quittance like ?2 ", nativeQuery = true)
    Long maxIdByBordereauCaisse( Long idBordereau, String codeNatureImpot);

//    @Query("SELECT MAX(u.id) FROM BordereauCaisse u WHERE u.agent =: idAgent")
    @Query(value = "SELECT MAX(id) FROM winregist.bordereaucaisse WHERE agent_id = ?1", nativeQuery = true)
    Long maxIdBordereauCaisseByAgent( Long idAgent);

    List<Quittance> findQuittanceByBordereauCaisseIs(BordereauCaisse bordereauCaisse);

}
	


