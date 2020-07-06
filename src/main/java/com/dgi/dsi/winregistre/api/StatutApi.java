package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.Service;
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
import com.dgi.dsi.winregistre.dao.StatutDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Statut;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class StatutApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private StatutDao exerciceDao;


	@GetMapping(value = "/listStatuts")
	public List<Statut> getStatuts() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutStatut")
	public Statut ajoutStatut(@RequestBody Statut userForm) {

		Statut userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
		@GetMapping(value = "/searchStatutByCode/{code}")
	public Statut updateStatutByCode(@PathVariable String code) {

		return exerciceDao.findByCodeLike("%"+code+"%");

	}

	
	@DeleteMapping(value = "/deleteStatut/{id}")
	public boolean deleteStatut(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Statut exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePStatut/{id}")
	public Statut updateStatut(@PathVariable Long id) {

		Statut exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeStatut")
	public Statut updatePartielStatut(@Valid @RequestBody Statut statut) {

		Statut statutRech = exerciceDao.findByIdIs(statut.getId());
		if (statutRech != null) {
			statut.setId(statutRech.getId());
			return exerciceDao.save(statut);
		}else{
			exerciceDao.save(statut);
		}
		return statut;
	}


}
