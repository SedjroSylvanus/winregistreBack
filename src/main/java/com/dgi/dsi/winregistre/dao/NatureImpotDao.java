package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.NatureImpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface NatureImpotDao extends JpaRepository<NatureImpot, Long> {

    List<NatureImpot> findByDesignationLike(String designation);
    NatureImpot findByDesignationEquals(String designation);

    NatureImpot findByCodeLike(String code);

    public NatureImpot findByIdIs(String id);
    public NatureImpot findByIdIs(Long id);

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
	


