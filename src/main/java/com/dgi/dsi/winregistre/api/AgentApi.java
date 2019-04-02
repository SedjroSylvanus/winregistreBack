package com.dgi.dsi.winregistre.api;

import java.util.List;

import com.dgi.dsi.winregistre.dao.AvoirPourcentageDao;
import com.dgi.dsi.winregistre.dao.RoleRepository;
import com.dgi.dsi.winregistre.entites.AppRole;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dgi.dsi.winregistre.dao.AgentDao;

import com.dgi.dsi.winregistre.entites.Agent;


import javax.validation.Valid;

@RestController
@CrossOrigin("*")


public class AgentApi {

//	@Autowired
//	private agentDao agentDao;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private RoleRepository roleRepository;


    @GetMapping(value = "/listAgents")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Agent> getAgents() {
		return agentDao.findAll();
	}

    @GetMapping(value = "/listAgents/{matricule}")
    public List<Agent> getAgents(@PathVariable String matricule) {
	    return agentDao.findByMatricule(matricule);
    }

    @PostMapping("/ajoutAgent")
	public Agent ajoutAgent( @Valid @RequestBody Agent userForm) {

		Agent userSearch = agentDao.findOne(userForm.getId());

		if (userSearch == null) {
			agentDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}

	@DeleteMapping(value = "/deleteAgent/{id}")
	public boolean deleteAgent(@PathVariable Long id) {
		// contactRepository.delete(id);

		Agent exercice = agentDao.findOne(id);

		if (exercice != null) {
			agentDao.delete(exercice);
			return true;
		} else {

			return false;
		}

	}

	@PutMapping(value = "/mergePAgent/{matricule}")
	public Agent updateAgent( @Valid @RequestBody Agent userForm,
                              @PathVariable String matricule) {

        List<Agent> exercice = agentDao.findByMatricule(matricule);
        if (exercice.get(0) != null) {

            userForm.setId(exercice.get(0).getId());
            agentDao.saveAndFlush(userForm);

        } else {

            agentDao.saveAndFlush(exercice.get(0));
        }
        return exercice.get(0);
	}

	@PatchMapping(value = "/mergeAgent/{matricule}")
	public Agent updatePartielAgent( @Valid @RequestBody Agent userForm,
                                     @PathVariable String matricule) {

        List<Agent> exercice = agentDao.findByMatricule(matricule);
        if (exercice.get(0) != null) {

            userForm.setId(exercice.get(0).getId());
            agentDao.saveAndFlush(userForm);

        } else {

            agentDao.saveAndFlush(exercice.get(0));
        }
        return exercice.get(0);
	}

	@GetMapping(value = "/roles")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<AppRole> getRoles() {
		return roleRepository.findAll();
	}


}
