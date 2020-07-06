package com.dgi.dsi.winregistre.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import com.dgi.dsi.winregistre.entites.Banque;
import org.springframework.data.repository.query.Param;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface BanqueDao extends JpaRepository<Banque, Long> {

    List<Banque> findByDesignationLike(String designation);

    Banque findByCodeLike(String code);
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
	


