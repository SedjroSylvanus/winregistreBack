package com.dgi.dsi.winregistre.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.dgi.dsi.winregistre.dao.NatureActeDao;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
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
import com.dgi.dsi.winregistre.dao.CategorieActeDao;
import com.dgi.dsi.winregistre.dao.ExerciceDao;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class CategorieActeApi {


//	@Autowired
//	private ExerciceDao categorieActeDao;
	
	@Autowired
	private CategorieActeDao categorieActeDao;

	@Autowired
	private NatureActeDao natureActeDao;



	@GetMapping(value = "/lastNumberNatureActeCategorie/{codeCategorie}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public ResponseEntity<?> getNumberNatureActeCategorie(@PathVariable String codeCategorie) {

		System.out.println("----------------numBordereau-------------------"+ codeCategorie);
		CategorieActe categorieActe0 = categorieActeDao.findByCodeEquals(codeCategorie);
		if (categorieActe0 != null){
			System.out.println("-----------bordActe0.getId()----------"+ categorieActe0.getId());
			Long idCatNatureActe = natureActeDao.maxIdByCategorie(categorieActe0.getId());
			System.out.println("----------------idBordereauActe-------------------"+idCatNatureActe);

			Map<String, String> map = new HashMap<>();

			if (idCatNatureActe != null){
				NatureActe natActe = natureActeDao.findByIdIs(idCatNatureActe);

				map.put("codenatureActe", natActe.getCode());
				return ResponseEntity.ok().body(map);
			}else{
				return ResponseEntity.notFound().build();
			}

		}else{
			return ResponseEntity.notFound().build();
		}



	}

	
	

	@GetMapping(value = "/listCategorieActes")
	public List<CategorieActe> getCategorieActes() {
		return categorieActeDao.findAll();
	}

	@PostMapping("/ajoutCategorieActe")
	public CategorieActe ajoutCategorieActe(@RequestBody CategorieActe userForm) {

		CategorieActe userSearch = categorieActeDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			categorieActeDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	@GetMapping(value = "/searchCategorieActeByCode/{code}")
	public CategorieActe updateCategorieActeByCode (@PathVariable String code) {

		return categorieActeDao.findByCodeLike("%"+code+"%");


	}
	
	@DeleteMapping(value = "/deleteCategorieActe/{id}")
	public boolean deleteCategorieActe(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		CategorieActe exercice = categorieActeDao.findByIdIs(id);
		
		if (exercice != null) {
			categorieActeDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePCategorieActe/{id}")
	public CategorieActe updateCategorieActe(@PathVariable Long id) {

		CategorieActe exercice = categorieActeDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return categorieActeDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeCategorieActe")
	public CategorieActe updatePartielCategorieActe(@Valid @RequestBody CategorieActe categorieActe) {

		CategorieActe categorieActeRech = categorieActeDao.findByIdIs(categorieActe.getId());
		if (categorieActeRech != null) {
			categorieActe.setId(categorieActeRech.getId());
			return categorieActeDao.save(categorieActe);
		}else{
			categorieActeDao.save(categorieActe);
		}
		return categorieActe;
	}




}
