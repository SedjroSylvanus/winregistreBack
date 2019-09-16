package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.Agent;
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

import com.dgi.dsi.winregistre.entites.Banque;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class BanqueApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private BanqueDao exerciceDao;


	@GetMapping(value = "/listBanques")
	public List<Banque> getBanques() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutBanque")
	public Banque ajoutBanque(@RequestBody Banque userForm) {

		Banque userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteBanque/{id}")
	public boolean deleteBanque(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Banque exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePBanque/{id}")
	public Banque updateBanque(@PathVariable Long id) {

		Banque exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

		@GetMapping(value = "/searchBanqueByCode/{code}")
	public Banque updateBanqueByCode (@PathVariable String code) {

		return exerciceDao.findByCodeLike("%"+code+"%");


	}

	@PatchMapping(value = "/mergeBanque")
	public Banque updatePartielBanque( @Valid @RequestBody Banque banque) {

		Banque banqueRecherchee = exerciceDao.findOne(banque.getId());
		if (banqueRecherchee != null) {
			banque.setId(banqueRecherchee.getId());
			return exerciceDao.save(banque);
		}else{
			exerciceDao.save(banque);
		}
		return banque;
	}

}
