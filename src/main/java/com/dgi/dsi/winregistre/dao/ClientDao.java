package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Agent;
import com.dgi.dsi.winregistre.entitiesIfri.Client;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ClientDao extends JpaRepository<Client, Integer>{
List<Client> findByNomcliLike(String o);




	
}
	


