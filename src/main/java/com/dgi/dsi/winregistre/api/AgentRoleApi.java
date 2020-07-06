package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.BanqueDao;
import com.dgi.dsi.winregistre.dao.RoleDao;
import com.dgi.dsi.winregistre.entites.AppRole;
import com.dgi.dsi.winregistre.entites.Banque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("*")
public class AgentRoleApi {



    @Autowired
    private RoleDao roleDao;

//	@Autowired
//	private ApplicationContext applicationContext;






    @GetMapping(value = "/listRoles")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<AppRole> getRoles() {
        return roleDao.findAll();
    }


    @PostMapping("/ajoutRole")
    public AppRole ajoutRole( @Valid @RequestBody AppRole userForm) {

        AppRole userSearch = roleDao.findByIdIs(userForm.getId());

        if (userSearch == null) {
            roleDao.saveAndFlush(userForm);
        } else {
            throw new RuntimeException(userSearch + "Role existant");
        }

        return userForm;

    }

    @DeleteMapping(value = "/deleteRole")
    public boolean deleteRole(@Valid @RequestBody AppRole userForm) {
        // contactRepository.delete(id);

        AppRole role = roleDao.findByIdIs(userForm.getId());

        if (role != null) {
            roleDao.delete(role);
            return true;
        } else {

            return false;
        }

    }



    @PatchMapping(value = "/mergeRole")

    public AppRole updatePartielAgent( @Valid @RequestBody AppRole userForm) {
        //,
//                                     @PathVariable String matricule) {

        AppRole role = roleDao.findByRoleName(userForm.getRoleName());
        if (role != null) {

            userForm.setId(role.getId());
            return roleDao.saveAndFlush(userForm);

        } else {

            roleDao.saveAndFlush(userForm);
        }
        return userForm;
    }


}
