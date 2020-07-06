package com.dgi.dsi.winregistre.service.SauvService;

import com.dgi.dsi.winregistre.entites.AppRole;
import com.dgi.dsi.winregistre.entites.AppUser;


public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUse(String username, String roleName);
	public AppUser findUserByUsername(String username);
}
