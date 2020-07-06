package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.JourFerie;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.dgi.dsi.winregistre.dao.ExerciceDao;
import com.dgi.dsi.winregistre.dao.ModePaiementDao;
import com.dgi.dsi.winregistre.entites.Exercice;
import com.dgi.dsi.winregistre.entites.ModePaiement;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class ModePaiementApi {


//	@Autowired
//	private ExerciceDao modePaiementDao;
	
	@Autowired
	private ModePaiementDao modePaiementDao;


	@GetMapping(value = "/listModePaiements")
	public List<ModePaiement> getModePaiements() {
		return modePaiementDao.findAll();
	}


	@GetMapping(value = "/oneModePaiementByDesignation/{designation}")
	public ModePaiement getModePaiementByDesignation(@PathVariable String designation) {

		return modePaiementDao.findByDesignationEquals(designation);
	}

	@PostMapping("/ajoutModePaiement")
	public ModePaiement ajoutModePaiement(@RequestBody ModePaiement userForm) {

		ModePaiement userSearch = modePaiementDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			modePaiementDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
		@GetMapping(value = "/searchModePaiementByCode/{code}")
	public ModePaiement updateModePaiementByCode(@PathVariable String code) {

		return modePaiementDao.findByCodeLike("%"+code+"%");

	}

	@DeleteMapping(value = "/deleteModePaiement/{id}")
	public boolean deleteModePaiement(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		ModePaiement exercice = modePaiementDao.findByIdIs(id);
		
		if (exercice != null) {
			modePaiementDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePModePaiement/{id}")
	public ModePaiement updateModePaiement(@PathVariable Long id) {

		ModePaiement exercice = modePaiementDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return modePaiementDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeModePaiement")
	public ModePaiement updatePartielModePaiement(@Valid @RequestBody ModePaiement modePaiement) {

		ModePaiement modePaiementRech = modePaiementDao.findByIdIs(modePaiement.getId());
		if (modePaiementRech != null) {
			modePaiement.setId(modePaiementRech.getId());
			return modePaiementDao.save(modePaiement);
		}else{
			modePaiementDao.save(modePaiement);
		}
		return modePaiement;
	}

}
