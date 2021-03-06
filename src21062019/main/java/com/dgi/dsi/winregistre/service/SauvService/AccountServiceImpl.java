//package com.dgi.dsi.winregistre.service.SauvService;
//
//import com.dgi.dsi.winregistre.dao.RoleRepository;
//import com.dgi.dsi.winregistre.dao.UserRepository;
//import com.dgi.dsi.winregistre.entites.AppRole;
//import com.dgi.dsi.winregistre.entites.AppUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class AccountServiceImpl implements AccountService {
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private RoleRepository roleRepository;
//
//	@Override
//	public AppUser saveUser(AppUser user) {
//		//System.out.println(user.getUsername()+""+user.getPassword());
//		String hashPW= bCryptPasswordEncoder.encode(user.getPassword());
//		user.setPassword(hashPW);
//		System.out.println(user.getUsername()+""+user.getPassword());
//		return userRepository.save(user);
//	}
//
//	@Override
//	public AppRole saveRole(AppRole role) {
//		// TODO Auto-generated method stub
//		return roleRepository.save(role);
//	}
//
//	@Override
//	public void addRoleToUse(String username, String roleName) {
//		AppRole role=roleRepository.findByRoleName(roleName);
//		AppUser user=userRepository.findByUsername(username);
//		user.getRoles().add(role);
//
//	}
//
//	@Override
//	public AppUser findUserByUsername(String username) {
//		// TODO Auto-generated method stub
//		return userRepository.findByUsername(username);
//	}
//
//}
