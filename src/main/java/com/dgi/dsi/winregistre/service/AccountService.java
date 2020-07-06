package com.dgi.dsi.winregistre.service;

import com.dgi.dsi.winregistre.entites.Agent;
import com.dgi.dsi.winregistre.entites.AppRole;
import com.dgi.dsi.winregistre.entites.AppUser;


public interface AccountService {
	public Agent saveUser(Agent user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUse(String username, String roleName);
	public void removeRoleToUse(String username, String roleName);
	public Agent findUserByUsername(String username);
}
