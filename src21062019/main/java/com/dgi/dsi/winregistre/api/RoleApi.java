package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.AgentDao;
import com.dgi.dsi.winregistre.dao.RoleDao;
import com.dgi.dsi.winregistre.entites.Agent;
import com.dgi.dsi.winregistre.entites.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class RoleApi {

//	@Autowired
////	private agentDao agentDao;
//
//	@Autowired
//	private RoleDao roleDao;
//
////	@Autowired
////	private ApplicationContext applicationContext;
//
//
//
//
//
//
//    @GetMapping(value = "/listRoles")
////	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
//	public List<AppRole> getRoles() {
//		return roleDao.findAll();
//	}
//
//
//    @PostMapping("/ajoutRole")
//	public AppRole ajoutRole( @Valid @RequestBody AppRole userForm) {
//
//		AppRole userSearch = roleDao.findOne(userForm.getId());
//
//		if (userSearch == null) {
//			roleDao.saveAndFlush(userForm);
//		} else {
//			throw new RuntimeException(userSearch + "Role existant");
//		}
//
//		return userForm;
//
//	}
//
//	@DeleteMapping(value = "/deleteRole")
//	public boolean deleteRole(@Valid @RequestBody AppRole userForm) {
//		// contactRepository.delete(id);
//
//		AppRole role = roleDao.findOne(userForm.getId());
//
//		if (role != null) {
//			roleDao.delete(role);
//			return true;
//		} else {
//
//			return false;
//		}
//
//	}
//
////	@PutMapping(value = "/mergePRole")
////	public AppRole updateRole( @Valid @RequestBody AppRole userForm
////                             ) {
////
////        List<AppRole> role = (List<AppRole>) roleDao.findByRoleName(userForm.getRoleName());
////        if (role.get(0) != null) {
////
////            userForm.setId(role.get(0).getId());
////            roleDao.saveAndFlush(userForm);
////
////        } else {
////
////            roleDao.saveAndFlush(role.get(0));
////        }
////        return role.get(0);
////	}
//
//	@PatchMapping(value = "/mergeRole")
//
//	public AppRole updatePartielAgent( @Valid @RequestBody AppRole userForm) {
//		//,
////                                     @PathVariable String matricule) {
//
//        AppRole role = roleDao.findByRoleName(userForm.getRoleName());
//        if (role != null) {
//
//            userForm.setId(role.getId());
//            return roleDao.saveAndFlush(userForm);
//
//        } else {
//
//			roleDao.saveAndFlush(userForm);
//        }
//        return userForm;
//	}




}
