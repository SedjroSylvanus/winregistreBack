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
import com.dgi.dsi.winregistre.dao.CategorieActeDao;
import com.dgi.dsi.winregistre.dao.ExerciceDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.CategorieActe;
import com.dgi.dsi.winregistre.entites.Exercice;


@RestController
@CrossOrigin("*")
public class CategorieActeApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private CategorieActeDao exerciceDao;


	@GetMapping(value = "/listCategorieActes")
	public List<CategorieActe> getCategorieActes() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutCategorieActe")
	public CategorieActe ajoutCategorieActe(@RequestBody CategorieActe userForm) {

		CategorieActe userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteCategorieActe/{id}")
	public boolean deleteCategorieActe(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		CategorieActe exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePCategorieActe/{id}")
	public CategorieActe updateCategorieActe(@PathVariable Long id) {

		CategorieActe exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeCategorieActe/{id}")
	public CategorieActe updatePartielCategorieActe(@PathVariable Long id) {

		CategorieActe exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
