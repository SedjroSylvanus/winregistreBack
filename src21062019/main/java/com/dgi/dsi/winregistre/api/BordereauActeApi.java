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
import com.dgi.dsi.winregistre.dao.BordereauActeDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.BordereauActe;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class BordereauActeApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private BordereauActeDao exerciceDao;


	@GetMapping(value = "/listBordereauActes")
	public List<BordereauActe> getBordereauActes() {
		return exerciceDao.findAll();
	}


	@GetMapping(value = "/oneBordereauActeByNumero/{numero}")
	public BordereauActe oneBordereauActeById(@PathVariable String numero) {
		// contactRepository.delete(id);

		BordereauActe bordereauActe = exerciceDao.findByNumeroEquals(numero);

		if(bordereauActe==null){
			return null;
		}else{
			return bordereauActe;
		}



	}


	@PostMapping("/ajoutBordereauActe")
	public BordereauActe ajoutBordereauActe(@RequestBody BordereauActe userForm) {

		BordereauActe userSearch = exerciceDao.findOne(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteBordereauActe")
	public boolean deleteBordereauActe(@Valid @RequestBody BordereauActe bordereauActe) {
		// contactRepository.delete(id);
		
		BordereauActe exercice = exerciceDao.findOne(bordereauActe.getId());
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePBordereauActe")
	public BordereauActe updateBordereauActe(@Valid @RequestBody BordereauActe bordereauActe) {

		BordereauActe exercice = exerciceDao.findOne(bordereauActe.getId());
		if (exercice != null) {
			exercice.setId(bordereauActe.getId());
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeBordereauActe")
	public BordereauActe updatePartielBordereauActe(@Valid @RequestBody BordereauActe bordereauActe) {

		BordereauActe bordereauActeRecherche = exerciceDao.findOne(bordereauActe.getId());
		if (bordereauActeRecherche != null) {
			bordereauActe.setId(bordereauActeRecherche.getId());
			return exerciceDao.save(bordereauActe);
		}else{
//			exerciceDao.save(bordereauActe);
		}
		return bordereauActe;
	}

}
