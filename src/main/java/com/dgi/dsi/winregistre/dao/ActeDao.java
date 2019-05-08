package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface ActeDao extends JpaRepository<Acte, Long>{

    Acte findByNumeroEquals(String numero);

	
}
	


