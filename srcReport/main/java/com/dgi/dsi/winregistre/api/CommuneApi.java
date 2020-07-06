package com.dgi.dsi.winregistre.api;

import java.util.List;

import com.dgi.dsi.winregistre.entites.CategorieActe;
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

import com.dgi.dsi.winregistre.dao.CommuneDao;

import com.dgi.dsi.winregistre.entites.Commune;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class CommuneApi {

//	@Autowired
//	private communeDao communeDao;

	@Autowired
	private CommuneDao communeDao;

	@GetMapping(value = "/listCommunes")
	public List<Commune> getCommunes() {
		return communeDao.findAll();
	}

	@PostMapping("/ajoutCommune")
	public Commune ajoutExercice(@RequestBody Commune userForm) {

		Commune userSearch = communeDao.findOne(userForm.getId());

		System.out.println(userForm);
		System.out.println(userSearch);
		if (userSearch == null) {

			communeDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}

	@GetMapping(value = "/getCommuneByDesignation/{designation}")
	public Commune getCommuneByDesignation (@PathVariable String designation) {

		return communeDao.findByDesignationEquals(designation);

	}
	@GetMapping(value = "/searchCommuneByCode/{code}")
	public Commune updateCommuneByCode (@PathVariable String code) {

		return communeDao.findByCodeEquals(code);

	}

	@DeleteMapping(value = "/deleteCommune/{id}")
	public boolean deleteCommune(@PathVariable Long id) {
		// contactRepository.delete(id);

		Commune exercice = communeDao.findOne(id);

		if (exercice != null) {
			communeDao.delete(exercice);
			return true;
		} else {

			return false;
		}

	}

//	@PutMapping(value = "/mergePCommune")
//	public Commune updateCommune(@PathVariable Long id) {
//
//		Commune commune = communeDao.findOne(id);
//		if (commune != null) {
//			commune.setId(id);
//			return communeDao.save(commune);
//		}
//		return commune;
//	}

	@PatchMapping(value = "/mergeCommune")
	public Commune updatePartielCommune(@Valid @RequestBody Commune commune) {

		Commune communeRech = communeDao.findOne(commune.getId());
		if (communeRech != null) {
			commune.setId(communeRech.getId());
			return communeDao.save(commune);
		}else{
			communeDao.save(commune);
		}
		return commune;
	}


}
