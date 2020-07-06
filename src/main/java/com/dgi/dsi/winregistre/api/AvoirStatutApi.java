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

import com.dgi.dsi.winregistre.dao.AvoirStatutDao;
import com.dgi.dsi.winregistre.dao.BanqueDao;
import com.dgi.dsi.winregistre.entites.AvoirStatut;
import com.dgi.dsi.winregistre.entites.Banque;


@RestController
@CrossOrigin("*")
public class AvoirStatutApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private AvoirStatutDao exerciceDao;


	@GetMapping(value = "/listAvoirStatuts")
	public List<AvoirStatut> getAvoirStatuts() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutAvoirStatut")
	public AvoirStatut ajoutAvoirStatut(@RequestBody AvoirStatut userForm) {

		AvoirStatut userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteAvoirStatut/{id}")
	public boolean deleteAvoirStatut(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		AvoirStatut exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePAvoirStatut/{id}")
	public AvoirStatut updateAvoirStatut(@PathVariable Long id) {

		AvoirStatut exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeAvoirStatut/{id}")
	public AvoirStatut updatePartielAvoirStatut(@PathVariable Long id) {

		AvoirStatut exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
