package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.NatureImpotDao;
import com.dgi.dsi.winregistre.entites.NatureImpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("*")
public class NatureImpotApi {


//	@Autowired
//	private ExerciceDao natureImpotDao;
	
	@Autowired
	private NatureImpotDao natureImpotDao;  


	@GetMapping(value = "/listNatureImpots")
	public List<NatureImpot> getNatureImpots() {
		return natureImpotDao.findAll();
	}


	@GetMapping(value = "/oneNatureImpotByDesignation/{designation}")
	public NatureImpot getNatureImpotByDesignation(@PathVariable String designation) {

		return natureImpotDao.findByDesignationEquals(designation);
	}



	@PostMapping("/ajoutNatureImpot")
	public NatureImpot ajoutNatureImpot(@RequestBody NatureImpot userForm) {

		NatureImpot userSearch = natureImpotDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			natureImpotDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteNatureImpot/{id}")
	public boolean deleteNatureImpot(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		NatureImpot exercice = natureImpotDao.findByIdIs(id);
		
		if (exercice != null) {
			natureImpotDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePNatureImpot/{id}")
	public NatureImpot updateNatureImpot(@PathVariable Long id) {

		NatureImpot exercice = natureImpotDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return natureImpotDao.save(exercice);
		}
		return exercice;
	}


	@GetMapping(value = "/searchNatureImpotByCode/{code}")
	public NatureImpot updateNatureImpotByCode (@PathVariable String code) {

		return natureImpotDao.findByCodeLike("%"+code+"%");

	}

	@PatchMapping(value = "/mergeNatureImpot")
	public NatureImpot updatePartielNatureImpot( @Valid @RequestBody NatureImpot banque) {

		NatureImpot banqueRecherchee = natureImpotDao.findByIdIs(banque.getId());
		if (banqueRecherchee != null) {
			banque.setId(banqueRecherchee.getId());
			return natureImpotDao.save(banque);
		}else{
			natureImpotDao.save(banque);
		}
		return banque;
	}

}
