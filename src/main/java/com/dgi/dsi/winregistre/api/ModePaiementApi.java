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
import com.dgi.dsi.winregistre.dao.ModePaiementDao;
import com.dgi.dsi.winregistre.entites.Exercice;
import com.dgi.dsi.winregistre.entites.ModePaiement;


@RestController
@CrossOrigin("*")
public class ModePaiementApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private ModePaiementDao exerciceDao;


	@GetMapping(value = "/listModePaiements")
	public List<ModePaiement> getModePaiements() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutModePaiement")
	public ModePaiement ajoutModePaiement(@RequestBody ModePaiement userForm) {

		ModePaiement userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteModePaiement/{id}")
	public boolean deleteModePaiement(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		ModePaiement exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePModePaiement/{id}")
	public ModePaiement updateModePaiement(@PathVariable Long id) {

		ModePaiement exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeModePaiement/{id}")
	public ModePaiement updatePartielModePaiement(@PathVariable Long id) {

		ModePaiement exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
