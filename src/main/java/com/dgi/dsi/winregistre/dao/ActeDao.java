package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ActeDao extends JpaRepository<Acte, Long>{

    @Query("select u from Acte u where u.numero =:numeroActe")
    Acte findByNumeroActe(@Param("numeroActe") String numeroActe) ;

    

    public Acte findByIdIs(String id);
    public Acte findByIdIs(Long id);


    @Query("select u from Acte u where u.numero =:numeroActe")
    Acte getActeByNumeroEquals(@Param("numeroActe") String numeroActe) ;
    

    Acte findActeByNumero( String numeroActe) ;
    Acte findActeByNumeroEquals( String numeroActe) ;

    

    @Query("select u from Quittance u where u.acteQuittance.numero =:numeroActe")
    List<Quittance> findByNumeroQuittance(@Param("numeroActe") String numeroActe) ;



    @Query("select distinct b.numeroTransfert from Acte b where b.statutCourant like :statutCourant " )
    List<Acte> ListeTransfererStatut(@Param("statutCourant") String statutCourant);





    //    @Query("select u from Quittance u where u.acteQuittance = :numeroQuittance")
//    List<Quittance> findByNumeroQuittance(@Param("numeroQuittance") Long numeroQuittance) ;



//    Acte findByNumeroEquals(String numeroActe);


    @Query("select u from AvoirStatut u where u.numero =:numeroActe")
    List<AvoirStatut> listActeAuditAvoirStatut(@Param("numeroActe") String numeroActe);


    @Query(value = "SELECT *, case da.revtype when 0 then 'Ajout' when 1 then 'Modification' when 2 then 'Suppression' else 'N/A'   end FROM acte_aud as da where da.numero = ?1", nativeQuery = true)
    List listActeAudit(String numero);


    @Query(value = "SELECT * FROM winregist.acte WHERE bordereau_acte_id = ?1", nativeQuery = true)
    List<Acte> listActeBordereau(Long idBordereau);

    @Query(value = "SELECT MAX(id) FROM winregist.acte WHERE bordereau_acte_id = ?1 AND acte.numero like  ?2", nativeQuery = true)
    Long maxIdByBordereauAndSuffixNumActe(Long idBordereau, String suffix);

    @Query(value = "SELECT MAX(id) FROM winregist.acte WHERE bordereau_acte_id = ?1", nativeQuery = true)
    Long maxIdByBordereau(Long idBordereau);


    @Query(value = "SELECT MAX(id) FROM winregist.acte WHERE bordereau_acte_contrib_id = ?1", nativeQuery = true)
    Long maxIdByBordereauContrib(Long idBordereau);

    
//    @Query(value = "SELECT MAX(id) FROM acte WHERE agent_sommier_id = ?1", nativeQuery = true)
//    Long maxIdDernierActeSommierByService(String codeService);

    @Query(value = "SELECT MAX(id) FROM winregist.acte WHERE agent_transfert_id = ?1", nativeQuery = true)
    Long maxIdByAgentLiquidateur(Long idAgentLiquidateur);

    @Query("select u from Acte u where u.montantDu <=:montantN and u.numero like :categorie and  (u.agentLiquidateur.id =:idAgentLiquidateur or u.agentValidation.id =:idAgentLiquidateur) and (u.statutCourant='QT' or u.statutCourant='VA')  ")           //(u.statutCourant='QT' or u.statutCourant='VA') and (u.agentLiquidateur.id =:idAgentLiquidateur or u.agentValidation.id =:idAgentLiquidateur)")
    List<Acte> listeActeAgentTransfert(@Param("montantN") Double montantN, @Param("categorie") String categorie, @Param("idAgentLiquidateur") Long idAgentLiquidateur);

//    List<Acte> findActeByBordereauActeIs(BordereauActe bordereauActe);


    @Query("select  b from Acte b left join fetch b.agentLiquidateur  a where (UPPER(b.statutCourant)  like :statutCourantVa or UPPER(b.statutCourant) like :statutCourantTrv or UPPER(b.statutCourant) like :statutCourantQt) and UPPER(b.numero) like :numAss and  UPPER(a.service.code) =:codeService " )
    List<Acte> listActeAMentionner(@Param("statutCourantVa") String statutCourantVa, @Param("statutCourantTrv") String statutCourantTrv, @Param("statutCourantQt") String statutCourantQt,@Param("numAss") String numAss, @Param("codeService") String codeService);

//    or UPPER(b.statutCourant) like :statutCourantRt    
//    and UPPER(b.numero) like :numAss
    @Query("select  b from Acte b left join fetch b.agentValidateurFinal  a where ( UPPER(b.statutCourant) like :statutCourantSom )  and  UPPER(a.service.code) =:codeService " )
    List<Acte> listActeAuSommier( @Param("statutCourantSom") String statutCourantSom,  @Param("codeService") String codeService);

    @Query("select  b from Acte b left join fetch b.agentValidateurFinal  a where ( UPPER(b.statutCourant) like :statutCourantSom )  and  UPPER(a.service.code) =:codeService and b.dateEnregistrementSommier =:dateSommier" )
    List<Acte> listActeAuSommierParDate( @Param("statutCourantSom") String statutCourantSom,  @Param("codeService") String codeService, @Param("dateSommier") LocalDate dateSommier);

    
    @Query("select  b from Acte b left join fetch b.agentValidateurFinal  a where ( UPPER(b.statutCourant) like :statutCourantSom )  and  UPPER(a.service.code) =:codeService  and b.dateEnregistrementSommier =:dateDuJourAuSommier " )
    List<Acte> listActeDujourAuSommier( @Param("statutCourantSom") String statutCourantSom,  @Param("codeService") String codeService,  @Param("dateDuJourAuSommier") LocalDate dateDuJourAuSommier);

    @Query("select  b from Acte b left join fetch b.agentLiquidateur  a where  UPPER(b.caseSommierAss) =:caseSommierAss and UPPER(b.volumeSommierAss) =:volumeSommierAss and UPPER(b.folioSommierAss) =:folioSommierAss and  UPPER(a.service.code) =:codeService " )
    List<Acte> listActeAuSommierCaseVolumeFolio( @Param("caseSommierAss") String caseSommierAss, @Param("volumeSommierAss") String volumeSommierAss,@Param("folioSommierAss") String folioSommierAss, @Param("codeService") String codeService);


    @Query("select u from Acte u where u.sourceInterne =:sourceInterne and u.statutCourant =:statut or ( u.montantDu = 0 and u.statutCourant = 'SS') or ( u.montantDu = 0 and u.statutCourant = 'PRE')")
    List<Acte> getActeContribuables(@Param("sourceInterne") Boolean sourceInterne, @Param("statut") String statut);
    


    @Query("select u from Acte u where u.montantDu >:montantDu")
    List<Acte> getActeQuittancer(@Param("montantDu") Double montantDu);
    

    @Query("select u from Acte u where u.numeroTransfert =:numTransfert and u.statutCourant=:statutCourant ")
//    @Query(value = "select * from winregist.acte where numero_transfert =  ?1 and statut_courant =  ?2", nativeQuery = true)
    List<Acte> getActesInTransfertLotDao(@Param("numTransfert") String numTransfert, @Param("statutCourant") String statutCourant);


    @Query("select u from Acte u where u.statutCourant='APP' and u.agentLiquidateur.id =:idAgentLiquidateur ")
    List<Acte> getActesInSommierDao(@Param("idAgentLiquidateur") Long idAgentLiquidateur);
    
    List<Acte> findActeByMontantDuIsAfterAndDateEnregistrementAfter(Double montantDu, LocalDate dateReference);
    
//    @Query("select u from Acte u where u.montantDu >:montantDu and u.")
//    Page<Acte> listActeAquittancer(Double montantDu);

    
}
	


