package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.Exercice;
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

import com.dgi.dsi.winregistre.dao.BanqueDao;
import com.dgi.dsi.winregistre.dao.InstitutionDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Institution;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class InstitutionApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private InstitutionDao exerciceDao;


	@GetMapping(value = "/listInstitutions")
	public List<Institution> getBanques() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutInstitution")
	public Institution ajoutInstitution(@RequestBody Institution userForm) {

		Institution userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	@GetMapping(value = "/searchInstitutionByCode/{code}")
	public Institution updateInstitutionByCode(@PathVariable String code) {

		return exerciceDao.findByCodeLike("%"+code+"%");

	}

	
	@DeleteMapping(value = "/deleteInstitution/{id}")
	public boolean deleteInstitution(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Institution exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePInstitution/{id}")
	public Institution updateInstitution(@PathVariable Long id) {

		Institution exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeInstitution")
	public Institution updatePartielInstitution(@Valid @RequestBody Institution institution) {

		Institution institutionRech = exerciceDao.findByIdIs(institution.getId());
		if (institutionRech != null) {
			institution.setId(institutionRech.getId());
			return exerciceDao.save(institution);
		}else{
			exerciceDao.save(institution);
		}
		return institution;
	}

}
