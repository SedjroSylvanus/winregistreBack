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

import com.dgi.dsi.winregistre.dao.AvoirPourcentageDao;
import com.dgi.dsi.winregistre.dao.AvoirStatutDao;
import com.dgi.dsi.winregistre.dao.BanqueDao;
import com.dgi.dsi.winregistre.entites.AvoirPourcentage;
import com.dgi.dsi.winregistre.entites.AvoirStatut;
import com.dgi.dsi.winregistre.entites.Banque;


@RestController
@CrossOrigin("*")
public class AvoirPourcentageApi {


//	@Autowired
//	private avoirPourcentageDao avoirPourcentageDao;
	
	@Autowired
	private AvoirPourcentageDao avoirPourcentageDao;


	@GetMapping(value = "/listAvoirAvoirPourcentages")
	public List<AvoirPourcentage> getAvoirPourcentages() {
		return avoirPourcentageDao.findAll();
	}

	@PostMapping("/ajoutAvoirPourcentage")
	public AvoirPourcentage ajoutAvoirPourcentage(@RequestBody AvoirPourcentage userForm) {

		AvoirPourcentage userSearch = avoirPourcentageDao.findOne(userForm.getId());

		if (userSearch == null) {
			avoirPourcentageDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteAvoirPourcentage/{id}")
	public boolean deleteAvoirPourcentage(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		AvoirPourcentage exercice = avoirPourcentageDao.findOne(id);
		
		if (exercice != null) {
			avoirPourcentageDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePAvoirPourcentage/{id}")
	public AvoirPourcentage updateAvoirPourcentage(@PathVariable Long id) {

		AvoirPourcentage exercice = avoirPourcentageDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return avoirPourcentageDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeAvoirPourcentage/{id}")
	public AvoirPourcentage updatePartielAvoirPourcentage(@PathVariable Long id) {

		AvoirPourcentage exercice = avoirPourcentageDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return avoirPourcentageDao.save(exercice);
		}
		return exercice;
	}

}
