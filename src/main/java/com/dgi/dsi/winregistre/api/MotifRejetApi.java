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
import com.dgi.dsi.winregistre.dao.MotifRejetDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.MotifRejet;


@RestController
@CrossOrigin("*")
public class MotifRejetApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private MotifRejetDao exerciceDao;


	@GetMapping(value = "/listMotifRejets")
	public List<MotifRejet> getMotifRejets() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutMotifRejet")
	public MotifRejet ajoutMotifRejet(@RequestBody MotifRejet userForm) {

		MotifRejet userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteMotifRejet/{id}")
	public boolean deleteMotifRejet(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		MotifRejet exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePMotifRejet/{id}")
	public MotifRejet updateMotifRejet(@PathVariable Long id) {

		MotifRejet exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeMotifRejet/{id}")
	public MotifRejet updatePartielMotifRejet(@PathVariable Long id) {

		MotifRejet exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
