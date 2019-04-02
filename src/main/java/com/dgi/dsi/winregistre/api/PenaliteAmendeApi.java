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
import com.dgi.dsi.winregistre.dao.ExerciceDao;
import com.dgi.dsi.winregistre.dao.PenaliteAmendeDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Exercice;
import com.dgi.dsi.winregistre.entites.PenaliteAmende;


@RestController
@CrossOrigin("*")
public class PenaliteAmendeApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private PenaliteAmendeDao exerciceDao;


	@GetMapping(value = "/listPenaliteAmendes")
	public List<PenaliteAmende> getPenaliteAmendes() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutPenaliteAmende")
	public PenaliteAmende ajoutBanque(@RequestBody PenaliteAmende userForm) {

		PenaliteAmende userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deletePenaliteAmende/{id}")
	public boolean deletePenaliteAmende(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		PenaliteAmende exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePPenaliteAmende/{id}")
	public PenaliteAmende updatePenaliteAmende(@PathVariable Long id) {

		PenaliteAmende exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergePenaliteAmende/{id}")
	public PenaliteAmende updatePartielPenaliteAmende(@PathVariable Long id) {

		PenaliteAmende exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
