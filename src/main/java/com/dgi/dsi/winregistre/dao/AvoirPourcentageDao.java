package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.Acte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.AvoirPourcentage;
import org.springframework.security.core.GrantedAuthority;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface AvoirPourcentageDao extends JpaRepository<AvoirPourcentage, Long>{



    public AvoirPourcentage findByIdIs(String id);
    public AvoirPourcentage findByIdIs(Long id);
    enum Role implements GrantedAuthority {
      ROLE_ADMIN, ROLE_CLIENT;

      public String getAuthority() {
        return name();
      }

    }
}
	


