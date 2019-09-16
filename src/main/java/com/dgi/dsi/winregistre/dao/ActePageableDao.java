package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.BordereauActe;
import com.dgi.dsi.winregistre.entites.ContribuableBis;
import com.dgi.dsi.winregistre.entites.Quittance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ActeDao extends PagingAndSortingRepository<Acte, Long> {

    @Query("select u from Acte u where u.numero = :numeroActe")
    Acte findByNumeroActe(@Param("numeroActe") String numeroActe) ;

//    @Query("select u from Acte u where u.numero LIKE :numeroActe")
    Acte findActeByNumeroEquals( String numeroActe) ;

    @Query("select u from Quittance u where u.acteQuittance.numero =:numeroActe")
    List<Quittance> findByNumeroQuittance(@Param("numeroActe") String numeroActe) ;



    @Query("select distinct b.numeroTransfert from Acte b where b.statutCourant like :statutCourant " )
    Page<Acte> ListeTransfererStatut(@Param("statutCourant") String statutCourant, Pageable pageable);

    List<Acte> findActeByNumeroTransfertAndStatutCourantLike( String statutCourant);

    @Override
    Page<Acte> findAll(Pageable pageable);

    //    @Query("select u from Quittance u where u.acteQuittance = :numeroQuittance")
//    List<Quittance> findByNumeroQuittance(@Param("numeroQuittance") Long numeroQuittance) ;



    Acte findByNumeroEquals(String numeroActe);

//    Acte findByNumeroEquals(String numero);

    @Query(value = "SELECT * FROM acte WHERE bordereau_acte_id = ?1", nativeQuery = true)
    List<Acte> listActeBordereau(Long idBordereau);

    @Query(value = "SELECT MAX(id) FROM acte WHERE bordereau_acte_id = ?1", nativeQuery = true)
    Long maxIdByBordereau(Long idBordereau);

    @Query(value = "SELECT MAX(id) FROM acte WHERE agent_liquidateur_id = ?1", nativeQuery = true)
    Long maxIdByAgentLiquidateur(Long idAgentLiquidateur);

    @Query("select u from Acte u where u.montantDu <=:montantN and u.agentLiquidateur.id =:idAgentLiquidateur")
    List<Acte> ListeActeAgentTransfert(@Param("montantN") Double montantN, @Param("idAgentLiquidateur") Long idAgentLiquidateur);

//    List<Acte> findActeByBordereauActeIs(BordereauActe bordereauActe);



    @Query("select u from Acte u where u.sourceInterne =:sourceInterne")
    List<Acte> getActeContribuables(@Param("sourceInterne") Boolean sourceInterne);

    @Query("select u from Acte u where u.montantDu >:montantDu")
    List<Acte> getActeQuittancer(@Param("montantDu") Double montantDu);
}
	


