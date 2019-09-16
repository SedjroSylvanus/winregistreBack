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

import com.dgi.dsi.winregistre.dao.NatureActeDao;
import com.dgi.dsi.winregistre.entites.NatureActe;


@RestController
@CrossOrigin("*")
public class NatureActeApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private NatureActeDao exerciceDao;


	@GetMapping(value = "/listNatureActes")
	public List<NatureActe> getTypeActes() {
		return exerciceDao.findAll();
	}

	@GetMapping(value = "/getNatureActeByDesignation/{designation}")
	public NatureActe getNatureActeByDesignation(@PathVariable String designation) {
		// contactRepository.delete(id);

		return  exerciceDao.findByDesignationEquals(designation);


	}

	@GetMapping(value = "/getNatureActeByCode/{code}")
	public NatureActe getNatureActeByCode(@PathVariable String code) {
		// contactRepository.delete(id);

		return  exerciceDao.findByCodeEquals(code);


	}



	@PostMapping("/ajoutNatureActe")
	public NatureActe ajoutTypeActe(@RequestBody NatureActe userForm) {

		NatureActe userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteNatureActe/{id}")
	public boolean deleteTypeActe(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		NatureActe exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePNatureActe/{id}")
	public NatureActe updateTypeActe(@PathVariable Long id) {

		NatureActe exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeNatureActe/{id}")
	public NatureActe updatePartielTypeActe(@PathVariable Long id) {

		NatureActe exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
