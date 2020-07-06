package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Service;
import com.dgi.dsi.winregistre.entites.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface TarifDao extends JpaRepository<Tarif, Long> {



    public Tarif findByIdIs(String id);
    public Tarif findByIdIs(Long id);

    //exécuter une requête sql native
    @Query(value = "SELECT * FROM tarif WHERE code = ?1", nativeQuery = true)
    Tarif tarifByCode(String code);

//exécuter une requête sql native
//    @Query(value = "SELECT * FROM tarif WHERE designation = ?1", nativeQuery = true)
//    Tarif tarifByDesignation(String designation);
    Tarif findTarifByDesignation(String designation);


//exemple avec paramètre
//    @Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
//    Banque findByLastnameOrFirstname(@Param("lastname") String lastname,
//            @Param("firstname") String firstname);

    //exemple de requête
//    @Query("select u from User u where u.firstname like %?1")
//    List<Banque> findByFirstnameEndsWith(String firstname);

    //exécuter une requête sql native
//    @Query(value = "SELECT * FROM banque WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
//    Banque findByEmailAddress(String emailAddress);

    //	String Q_GET_ALL_USERS = "select u from Banque u left join u.role";
//	@Query(Q_GET_ALL_USERS)
//	Collection<Banque> getAllUsers();
}
	


