package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.BordereauActe;
import com.dgi.dsi.winregistre.entites.ContribuableBis;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ActeDao extends JpaRepository<Acte, Long>{

    @Query("select u from Acte u where u.numero = :numeroActe")
    Acte findByNumeroActe(@Param("numeroActe") String numeroActe) ;



    Acte findByNumeroEquals(String numeroActe);

//    Acte findByNumeroEquals(String numero);

    @Query(value = "SELECT * FROM acte WHERE bordereau_acte_id = ?1", nativeQuery = true)
    List<Acte> listActeBordereau(Long idBordereau);

    @Query(value = "SELECT MAX(id) FROM acte WHERE bordereau_acte_id = ?1", nativeQuery = true)
    Long maxIdByBordereau(Long idBordereau);

//    List<Acte> findActeByBordereauActeIs(BordereauActe bordereauActe);



    @Query("select u from Acte u where u.sourceInterne =:sourceInterne")
    List<Acte> getActeContribuables(@Param("sourceInterne") Boolean sourceInterne);

    @Query("select u from Acte u where u.montantDu >:montantDu")
    List<Acte> getActeQuittancer(@Param("montantDu") Double montantDu);
}
	


