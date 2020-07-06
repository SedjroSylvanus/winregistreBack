package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.*;
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
import com.dgi.dsi.winregistre.dao.DirectionDao;
import com.dgi.dsi.winregistre.dao.ExerciceDao;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class DirectionApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private DirectionDao exerciceDao;


	@GetMapping(value = "/listDirections")
	public List<Direction> getDirections() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutDirection")
	public Direction ajoutBanque(@RequestBody Direction userForm) {

		Direction userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	@GetMapping(value = "/searchDirectionByCode/{code}")
	public Direction updateDirectionByCode(@PathVariable String code) {

		return exerciceDao.findByCodeLike("%"+code+"%");

	}
	
	@DeleteMapping(value = "/deleteDirection/{id}")
	public boolean deleteBanque(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Direction exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePDirection/{id}")
	public Direction updateBanque(@PathVariable Long id) {

		Direction exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeDirection")
	public Direction updatePartielBanque(@Valid @RequestBody Direction direction) {

		Direction directionRech = exerciceDao.findByIdIs(direction.getId());
		if (directionRech != null) {
			direction.setId(directionRech.getId());
			return exerciceDao.save(direction);
		}else{
			exerciceDao.save(direction);
		}
		return direction;
	}


	@GetMapping(value = "/directionByCode/{code}")
	public Direction directionByCode(@PathVariable String code) {

		return exerciceDao.directionByCode(code);

	}
}
