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

import com.dgi.dsi.winregistre.dao.ServiceDao;
import com.dgi.dsi.winregistre.entites.Service;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class ServiceApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private ServiceDao exerciceDao;


	@GetMapping(value = "/listServices")
	public List<Service> getServices() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutService")
	public Service ajoutService(@RequestBody Service userForm) {

		Service userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
		@GetMapping(value = "/searchServiceByCode/{code}")
	public Service updateServiceByCode(@PathVariable String code) {

		return exerciceDao.findByCodeLike("%"+code+"%");

	}

	@DeleteMapping(value = "/deleteService/{id}")
	public boolean deleteService(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Service exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePService/{id}")
	public Service updateService(@PathVariable Long id) {

		Service exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeService")
	public Service updatePartielService(@Valid @RequestBody Service service) {

		Service serviceRech = exerciceDao.findOne(service.getId());
		if (serviceRech != null) {
			service.setId(serviceRech.getId());
			return exerciceDao.save(service);
		}else{
			exerciceDao.save(service);
		}
		return service;
	}

}
