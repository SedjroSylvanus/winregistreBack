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
import com.dgi.dsi.winregistre.dao.ProduitDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Produit;


@RestController
@CrossOrigin("*")
public class ProduitApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private ProduitDao exerciceDao;


	@GetMapping(value = "/listProduits")
	public List<Produit> getProduits() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutProduit")
	public Produit ajoutProduit(@RequestBody Produit userForm) {

		Produit userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteProduit/{id}")
	public boolean deleteProduit(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Produit exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePProduit/{id}")
	public Produit updateProduit(@PathVariable Long id) {

		Produit exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeProduit/{id}")
	public Produit updatePartielProduit(@PathVariable Long id) {

		Produit exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}

}
