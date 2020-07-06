package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.GrilleActeSousSeingPrive;
import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
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

import com.dgi.dsi.winregistre.dao.PenaliteAmendeDao;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class TypePenaliteAmendeApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private PenaliteAmendeDao exerciceDao;


	@GetMapping(value = "/listTypePenaliteAmendes")
	public List<TypePenaliteAmende> getPenaliteAmendes() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutTypePenaliteAmende")
	public TypePenaliteAmende ajoutBanque(@RequestBody TypePenaliteAmende userForm) {

		TypePenaliteAmende userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteTypePenaliteAmende/{id}")
	public boolean deletePenaliteAmende(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		TypePenaliteAmende exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePTypePenaliteAmende/{id}")
	public TypePenaliteAmende updatePenaliteAmende(@PathVariable Long id) {

		TypePenaliteAmende exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeTypePenaliteAmende")
	public TypePenaliteAmende updatePartielPenaliteAmende(@Valid @RequestBody TypePenaliteAmende penaliteAmende) {

		TypePenaliteAmende penaliteAmendeRech = exerciceDao.findByIdIs(penaliteAmende.getId());
		if (penaliteAmendeRech != null) {
			penaliteAmende.setId(penaliteAmendeRech.getId());
			return exerciceDao.save(penaliteAmende);
		}else{
			exerciceDao.save(penaliteAmende);
		}
		return penaliteAmende;
	}



	@GetMapping(value = "/typePenaliteAmendeByCode/{code}")
	public TypePenaliteAmende typePenaliteAmendeByCode(@PathVariable String code) {

		return exerciceDao.findTypePenaliteAmendeByCode(code);

	}


	@GetMapping(value = "/typePenaliteAmendeByDesignation/{designation}")
	public TypePenaliteAmende typePenaliteAmendeByDesignation(@PathVariable String designation) {
		return exerciceDao.findTypePenaliteAmendeByDesignation(designation);
	}



}
