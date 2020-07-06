package com.dgi.dsi.winregistre.api;


import com.dgi.dsi.winregistre.dao.TarifDao;

import com.dgi.dsi.winregistre.entites.Service;
import com.dgi.dsi.winregistre.entites.Tarif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("*")
public class TarifApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private TarifDao tarifDao;


	@GetMapping(value = "/listTarifs")
	public List<Tarif> getTarifs() {
		return tarifDao.findAll();
	}

	@PostMapping("/ajoutTarif")
	public Tarif ajoutTarif(@RequestBody Tarif userForm) {

		Tarif userSearch = tarifDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			tarifDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteTarif/{id}")
	public boolean deleteBanque(@PathVariable Long id) {
		// contactRepository.delete(id);

		Tarif exercice = tarifDao.findByIdIs(id);
		
		if (exercice != null) {
			tarifDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePTarif/{id}")
	public Tarif updateBanque(@PathVariable Long id) {

		Tarif exercice = tarifDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return tarifDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeTarif")
	public Tarif updatePartielBanque( @Valid @RequestBody Tarif banque) {

		Tarif banqueRecherchee = tarifDao.findByIdIs(banque.getId());
		if (banqueRecherchee != null) {
			banque.setId(banqueRecherchee.getId());
			return tarifDao.save(banque);
		}else{
			tarifDao.save(banque);
		}
		return banque;
	}

	@GetMapping(value = "/tarifByCode/{code}")
	public Tarif tarifByCode(@PathVariable String code) {

		return tarifDao.tarifByCode(code);

	}

	@GetMapping(value = "/tarifByDEsignation/{designation}")
	public Tarif tarifByDesignation(@PathVariable String designation) {

		return tarifDao.findTarifByDesignation(designation);

	}


}
