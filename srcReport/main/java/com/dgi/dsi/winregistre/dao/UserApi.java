package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserApi extends JpaRepository<AppUser, Long>{
	
	public AppUser findByUsername(String username);
	
	public AppUser findByEmail(String email);
	public AppUser findByConfirmationToken(String confirmationToken);
	
	@Query("select c from AppUser c where c.nom like :x")
	public Page<AppUser> chercher(@Param("x") String mc, Pageable pageable);
	
}
