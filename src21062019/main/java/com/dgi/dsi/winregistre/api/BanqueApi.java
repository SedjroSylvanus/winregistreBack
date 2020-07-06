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
//	private ExerciceDao banqueDao;
	
	@Autowired
	private BanqueDao banqueDao;


	@GetMapping(value = "/listBanques")
	public List<Banque> getBanques() {
		return banqueDao.findAll();
	}


	@GetMapping(value = "/oneBanqueByDesignation/{designation}")
	public Banque getBanqueByDesignation(@PathVariable String designation) {

		return banqueDao.findByDesignationEquals(designation);
	}



	@PostMapping("/ajoutBanque")
	public Banque ajoutBanque(@RequestBody Banque userForm) {

		Banque userSearch = banqueDao.findOne(userForm.getId());

		if (userSearch == null) {
			banqueDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteBanque/{id}")
	public boolean deleteBanque(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Banque exercice = banqueDao.findOne(id);
		
		if (exercice != null) {
			banqueDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePBanque/{id}")
	public Banque updateBanque(@PathVariable Long id) {

		Banque exercice = banqueDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return banqueDao.save(exercice);
		}
		return exercice;
	}

		@GetMapping(value = "/searchBanqueByCode/{code}")
	public Banque updateBanqueByCode (@PathVariable String code) {

		return banqueDao.findByCodeLike("%"+code+"%");


	}

	@PatchMapping(value = "/mergeBanque")
	public Banque updatePartielBanque( @Valid @RequestBody Banque banque) {

		Banque banqueRecherchee = banqueDao.findOne(banque.getId());
		if (banqueRecherchee != null) {
			banque.setId(banqueRecherchee.getId());
			return banqueDao.save(banque);
		}else{
			banqueDao.save(banque);
		}
		return banque;
	}

}
