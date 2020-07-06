package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.Direction;
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
import com.dgi.dsi.winregistre.entites.Exercice;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class ExerciceApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private ExerciceDao exerciceDao;

	@GetMapping(value = "/listExercices")
	public List<Exercice> getExercice() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutExercice")
	public Exercice ajoutExercice(@RequestBody Exercice userForm) {

		Exercice userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/delete/{id}")
	public boolean deleteExercice(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Exercice exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergeP/{id}")
	public Exercice updateExercice(@PathVariable Long id) {

		Exercice exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeExercice")
	public Exercice updatePartielExercice(@Valid @RequestBody Exercice exercice) {

		Exercice exerciceRech = exerciceDao.findByIdIs(exercice.getId());
		if (exerciceRech != null) {
			exercice.setId(exerciceRech.getId());
			return exerciceDao.save(exercice);
		}else{
			exerciceDao.save(exercice);
		}
		return exercice;
	}

}
