package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.BanqueDao;
import com.dgi.dsi.winregistre.entites.Banque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
public class RoleApi {


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
	
	@PatchMapping(value = "/mergeBanque/{id}")
	public Banque updatePartielBanque(@PathVariable Long id) {

		Banque exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
