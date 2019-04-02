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
import com.dgi.dsi.winregistre.dao.InstitutionDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Institution;


@RestController
@CrossOrigin("*")
public class InstitutionApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private InstitutionDao exerciceDao;


	@GetMapping(value = "/listInstitutions")
	public List<Institution> getBanques() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutInstitution")
	public Institution ajoutInstitution(@RequestBody Institution userForm) {

		Institution userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteInstitution/{id}")
	public boolean deleteInstitution(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Institution exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePInstitution/{id}")
	public Institution updateInstitution(@PathVariable Long id) {

		Institution exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeInstitution/{id}")
	public Institution updatePartielInstitution(@PathVariable Long id) {

		Institution exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
