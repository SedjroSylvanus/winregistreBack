package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	
	
	AppUser findByUsername(String username);
	Boolean existsByUsername(String username);


	public AppUser findByIdIs(String id);
	public AppUser findByIdIs(Long id);
	Boolean existsByEmail(String email);




	@Transactional
	void deleteByUsername(String username);

}
