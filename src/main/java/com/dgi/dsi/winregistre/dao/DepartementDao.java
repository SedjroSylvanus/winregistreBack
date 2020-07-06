package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.Agent;
import com.dgi.dsi.winregistre.payload.DepartementPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import com.dgi.dsi.winregistre.entites.Departement;

import java.util.List;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface DepartementDao extends JpaRepository<Departement, Long>{

    public Departement findByCode(String code);

    public Departement findByIdIs(String id);
    public Departement findByIdIs(Long id);

    Departement findByCodeLike(String code);

    @Query(value = "SELECT *, case da.revtype when 0 then 'Ajout' when 1 then 'Modification' when 2 then 'Suppression' else 'N/A'   end FROM departement_aud as da", nativeQuery = true)
    List listDepAudit();



}
	


