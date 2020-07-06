package com.dgi.dsi.winregistre.api;

import java.util.List;


import com.dgi.dsi.winregistre.entites.BordereauActe;
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
import com.dgi.dsi.winregistre.dao.BordereauCaisseDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.BordereauCaisse;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class BordereauCaisseApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private BordereauCaisseDao exerciceDao;


		@GetMapping(value = "/oneBordereauCaisseByINumero/{numero}")
	public BordereauCaisse oneBordereauCaisseById(@PathVariable String numero) {
		// contactRepository.delete(id);

			BordereauCaisse bordereauCaisse = exerciceDao.findByNumeroEquals(numero);


		return bordereauCaisse;

	}

	@GetMapping(value = "/listBordereauCaisses")
	public List<BordereauCaisse> getBordereauCaisses() {
		return exerciceDao.findAll();
	}

	@PostMapping("/ajoutBordereauCaisse")
	public BordereauCaisse ajoutBordereauCaisse(@RequestBody BordereauCaisse userForm) {

		BordereauCaisse userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteBordereauCaisse/{id}")
	public boolean deleteBordereauCaisse(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		BordereauCaisse exercice = exerciceDao.findOne(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePBordereauCaisse/{id}")
	public BordereauCaisse updateBordereauCaisse(@PathVariable Long id) {

		BordereauCaisse exercice = exerciceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeBordereauCaisse/{id}")
	public BordereauCaisse updatePartielBanque(@Valid @RequestBody BordereauCaisse bordereauCaisse) {

		BordereauCaisse bordereauCaisseRech = exerciceDao.findOne(bordereauCaisse.getId());
		if (bordereauCaisseRech != null) {
			bordereauCaisse.setId(bordereauCaisseRech.getId());
			return exerciceDao.save(bordereauCaisse);
		}else{
			exerciceDao.save(bordereauCaisse);
		}
		return bordereauCaisse;
	}

}
