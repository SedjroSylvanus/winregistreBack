package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.TypeProduit;
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

import com.dgi.dsi.winregistre.dao.TypeProduitDao;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class TypeProduitApi {


//	@Autowired
//	private ExerciceDao typeProduitDao;
	
	@Autowired
	private TypeProduitDao typeProduitDao;


	@GetMapping(value = "/listTypeProduits")
	public List<TypeProduit> getTypeProduits() {
		return typeProduitDao.findAll();
	}

	@PostMapping("/ajoutTypeProduit")
	public TypeProduit ajoutTypeProduit(@RequestBody TypeProduit userForm) {

		TypeProduit userSearch = typeProduitDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			typeProduitDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}

	@GetMapping(value = "/searchTypeProduitByCode/{code}")
	public TypeProduit updateTypeProduitByCode(@PathVariable String code) {

		return typeProduitDao.findByCodeLike("%"+code+"%");

	}

	@DeleteMapping(value = "/deleteTypeProduit/{id}")
	public boolean deleteTypeProduit(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		TypeProduit exercice = typeProduitDao.findByIdIs(id);
		
		if (exercice != null) {
			typeProduitDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePTypeProduit/{id}")
	public TypeProduit updateTypeProduit(@PathVariable Long id) {

		TypeProduit exercice = typeProduitDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return typeProduitDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeTypeProduit")
	public TypeProduit updatePartielTypeProduit(@Valid @RequestBody TypeProduit produit) {

		TypeProduit produitRech = typeProduitDao.findByIdIs(produit.getId());
		if (produitRech != null) {
			produit.setId(produitRech.getId());
			return typeProduitDao.save(produit);
		}else{
			typeProduitDao.save(produit);
		}
		return produit;
	}
}
