package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.DetailQuittance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface DetailQuittanceDao extends JpaRepository<DetailQuittance, Long>{

// DetailQuittance findByCodeProduitLike(String code);

    public DetailQuittance findByIdIs(String id);
    public DetailQuittance findByIdIs(Long id);


    List<DetailQuittance> findDetailQuittanceByQuittance_Id(Long quittanceID);

}
	


