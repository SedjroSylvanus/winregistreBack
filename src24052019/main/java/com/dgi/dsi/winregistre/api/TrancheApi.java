package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.Statut;
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
import com.dgi.dsi.winregistre.dao.TrancheDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Exercice;
import com.dgi.dsi.winregistre.entites.Tranche;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class TrancheApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private TrancheDao exerciceDao;


	@GetMapping(value = "/listTranches")
	public List<Tranche> getBanques() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutTranche")
	public Tranche ajoutBanque(@RequestBody Tranche userForm) {

		Tranche userSearch = exerciceDao.findOne(userForm.getId());
		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}
		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteTranche/{id}")
	public boolean deleteBanque(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Tranche exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePTranche/{id}")
	public Tranche updateBanque(@PathVariable Long id) {

		Tranche exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeTranche")
	public Tranche updatePartielBanque(@Valid @RequestBody Tranche tranche) {

		Tranche trancheRech = exerciceDao.findOne(tranche.getId());
		if (trancheRech != null) {
			tranche.setId(trancheRech.getId());
			return exerciceDao.save(tranche);
		}else{
			exerciceDao.save(tranche);
		}
		return tranche;
	}


}
