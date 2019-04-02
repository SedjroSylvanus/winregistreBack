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
import com.dgi.dsi.winregistre.dao.BordereauActeDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.BordereauActe;


@RestController
@CrossOrigin("*")
public class BordereauActeApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private BordereauActeDao exerciceDao;


	@GetMapping(value = "/listBordereauActes")
	public List<BordereauActe> getBordereauActes() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutBordereauActe")
	public BordereauActe ajoutBordereauActe(@RequestBody BordereauActe userForm) {

		BordereauActe userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteBordereauActe/{id}")
	public boolean deleteBordereauActe(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		BordereauActe exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePBordereauActe/{id}")
	public BordereauActe updateBordereauActe(@PathVariable Long id) {

		BordereauActe exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeBordereauActe/{id}")
	public BordereauActe updatePartielBordereauActe(@PathVariable Long id) {

		BordereauActe exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
