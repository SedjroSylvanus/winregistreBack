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

import com.dgi.dsi.winregistre.dao.BanqueDao;
import com.dgi.dsi.winregistre.dao.StatutDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Statut;


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

		Statut userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteStatut/{id}")
	public boolean deleteStatut(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Statut exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePStatut/{id}")
	public Statut updateStatut(@PathVariable Long id) {

		Statut exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeStatut/{id}")
	public Statut updatePartielStatut(@PathVariable Long id) {

		Statut exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
