package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.AppRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
	public AppRole findByRoleName(String roleName);
}
