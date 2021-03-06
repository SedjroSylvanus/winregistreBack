package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Tarif;
import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface TypePenaliteAmendeDao extends JpaRepository<TypePenaliteAmende, Long> {



    public TypePenaliteAmende findByIdIs(String id);
    public TypePenaliteAmende findByIdIs(Long id);
    public TypePenaliteAmende findByCodeEquals(String code);

    //exécuter une requête sql native
    @Query(value = "SELECT * FROM type_penalite_amende WHERE code = ?1", nativeQuery = true)
    TypePenaliteAmende typePenaliteAmendeByCode(String code);


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
	


