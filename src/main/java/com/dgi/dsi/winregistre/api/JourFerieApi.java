package com.dgi.dsi.winregistre.api;

import java.util.List;



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

		JourFerie userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteJourFerie/{id}")
	public boolean deleteJourFerie(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		JourFerie exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePJourFerie/{id}")
	public JourFerie updateJourFerie(@PathVariable Long id) {

		JourFerie exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeJourFerie/{id}")
	public JourFerie updatePartielJourFerie(@PathVariable Long id) {

		JourFerie exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
