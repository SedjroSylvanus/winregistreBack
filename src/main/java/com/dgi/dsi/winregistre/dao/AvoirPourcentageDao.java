package com.dgi.dsi.winregistre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgi.dsi.winregistre.entites.AvoirPourcentage;
import org.springframework.security.core.GrantedAuthority;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface AvoirPourcentageDao extends JpaRepository<AvoirPourcentage, Long>{


    enum Role implements GrantedAuthority {
      ROLE_ADMIN, ROLE_CLIENT;

      public String getAuthority() {
        return name();
      }

    }
}
	


