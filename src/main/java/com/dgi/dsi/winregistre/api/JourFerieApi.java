package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.Institution;
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
import com.dgi.dsi.winregistre.dao.JourFerieDao;
import com.dgi.dsi.winregistre.entites.Exercice;
import com.dgi.dsi.winregistre.entites.JourFerie;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class JourFerieApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private JourFerieDao exerciceDao;


	@GetMapping(value = "/listJourFeries")
	public List<JourFerie> getJourFeries() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutJourFerie")
	public JourFerie ajoutJourFerie(@RequestBody JourFerie userForm) {

		JourFerie userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	@GetMapping(value = "/searchJourFerieByCode/{code}")
	public JourFerie updateJourFerieByCode(@PathVariable String code) {

		return exerciceDao.findByCodeLike("%"+code+"%");

	}

	
	@DeleteMapping(value = "/deleteJourFerie/{id}")
	public boolean deleteJourFerie(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		JourFerie exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePJourFerie/{id}")
	public JourFerie updateJourFerie(@PathVariable Long id) {

		JourFerie exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeJourFerie")
	public JourFerie updatePartielJourFerie(@Valid @RequestBody JourFerie jourFerie) {

		JourFerie jourFerieRech = exerciceDao.findByIdIs(jourFerie.getId());
		if (jourFerieRech != null) {
			jourFerie.setId(jourFerieRech.getId());
			return exerciceDao.save(jourFerie);
		}else{
			exerciceDao.save(jourFerie);
		}
		return jourFerie;
	}
}
