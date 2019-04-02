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

import com.dgi.dsi.winregistre.dao.TypeActeDao;
import com.dgi.dsi.winregistre.entites.TypeActe;


@RestController
@CrossOrigin("*")
public class TypeActeApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private TypeActeDao exerciceDao;


	@GetMapping(value = "/listTypeActes")
	public List<TypeActe> getTypeActes() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutTypeActe")
	public TypeActe ajoutTypeActe(@RequestBody TypeActe userForm) {

		TypeActe userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteTypeActe/{id}")
	public boolean deleteTypeActe(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		TypeActe exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePTypeActe/{id}")
	public TypeActe updateTypeActe(@PathVariable Long id) {

		TypeActe exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeTypeActe/{id}")
	public TypeActe updatePartielTypeActe(@PathVariable Long id) {

		TypeActe exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
