package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.DetailQuittanceDao;
import com.dgi.dsi.winregistre.entites.DetailQuittance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("*")
public class DetailQuittanceApi {


//	@Autowired
//	private ExerciceDao quittancerDao;
	
	@Autowired
	private DetailQuittanceDao quittancerDao;


	@GetMapping(value = "/listQuittancers")
	public List<DetailQuittance> getQuittancers() {
		return quittancerDao.findAll();
	}

	@PostMapping("/ajoutQuittancer")
	public DetailQuittance ajoutQuittancer(@RequestBody DetailQuittance userForm) {

		DetailQuittance userSearch = quittancerDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			quittancerDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}


	@DeleteMapping(value = "/deleteQuittancer/{id}")
	public boolean deleteQuittancer(@PathVariable Long id) {
		// contactRepository.delete(id);

		DetailQuittance quittancer = quittancerDao.findByIdIs(id);

		if (quittancer != null) {
			quittancerDao.delete(quittancer);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePQuittancer/{id}")
	public DetailQuittance updateQuittancer(@PathVariable Long id) {

		DetailQuittance quittancer = quittancerDao.findByIdIs(id);
		if (quittancer != null) {
			quittancer.setId(id);
			return quittancerDao.save(quittancer);
		}
		return quittancer;
	}

//	@GetMapping(value = "/searchQuittancerByCode/{code}")
//	public DetailQuittance updateQuittancerByCode (@PathVariable String code) {
//
//		return quittancerDao.findByCodeProduitLike("%"+code+"%");
//
//	}

	@PatchMapping(value = "/mergeQuittancer")
	public DetailQuittance updatePartieldeleteQuittancer(@Valid @RequestBody DetailQuittance banque) {

		DetailQuittance banqueRecherchee = quittancerDao.findByIdIs(banque.getId());
		if (banqueRecherchee != null) {
			banque.setId(banqueRecherchee.getId());
			return quittancerDao.save(banque);
		}else{
			quittancerDao.save(banque);
		}
		return banque;
	}

}
