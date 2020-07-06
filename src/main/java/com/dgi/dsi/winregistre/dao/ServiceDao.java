package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.ContribuableBis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;


import com.dgi.dsi.winregistre.entites.Service;

import java.util.List;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ServiceDao extends JpaRepository<Service, Long>{



    public Service findByIdIs(String id);
    public Service findByIdIs(Long id);
    Service findByCodeLike(String code);

    //exécuter une requête sql native
    @Query(value = "SELECT * FROM service WHERE designation = ?1", nativeQuery = true)
    Service designationByService(String designation);

}
	


