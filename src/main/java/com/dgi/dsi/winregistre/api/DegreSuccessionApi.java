package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.Commune;
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


import com.dgi.dsi.winregistre.dao.DegreSuccessionDao;

import com.dgi.dsi.winregistre.entites.DegreSuccession;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class DegreSuccessionApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private DegreSuccessionDao exerciceDao;


	@GetMapping(value = "/listDegreSuccessions")
	public List<DegreSuccession> getDegreSuccessions() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutDegreSuccession")
	public DegreSuccession ajoutDegreSuccession(@RequestBody DegreSuccession userForm) {

		DegreSuccession userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	@GetMapping(value = "/searchByDegreSuccessionCode/{code}")
	public DegreSuccession updateDegreSuccessionByCode (@PathVariable String code) {

		return exerciceDao.findByCodeLike("%"+code+"%");

	}

	@DeleteMapping(value = "/deleteDegreSuccession/{id}")
	public boolean deleteDegreSuccession(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		DegreSuccession exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePDegreSuccession/{id}")
	public DegreSuccession updateDegreSuccession(@PathVariable Long id) {

		DegreSuccession exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeDegreSuccession")
	public DegreSuccession updatePartielDegreSuccession(@Valid @RequestBody DegreSuccession degreSuccession) {

		DegreSuccession degreSuccessionRech = exerciceDao.findOne(degreSuccession.getId());
		if (degreSuccessionRech != null) {
			degreSuccession.setId(degreSuccessionRech.getId());
			return exerciceDao.save(degreSuccession);
		}else{
			exerciceDao.save(degreSuccession);
		}
		return degreSuccession;
	}

}
