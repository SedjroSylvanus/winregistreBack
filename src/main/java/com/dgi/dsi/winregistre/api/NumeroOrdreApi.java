package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.NumeroOrdreDao;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.NumeroOrdre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("*")
public class NumeroOrdreApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private NumeroOrdreDao exerciceDao;


	@GetMapping(value = "/listNumeroOrdres")
	public List<NumeroOrdre> getTypeActes() {
		return exerciceDao.findAll();
	}

	@GetMapping(value = "/getNumeroOrdreByCodeService/{codeService}")
	public Integer getNumeroOrdreByCodeService(@PathVariable String codeService) {

		return exerciceDao.numeroOrdreService(codeService.toUpperCase());
//		Map<String, Integer> map = new  HashMap<>();
//		map.put("numeroOrdre", exerciceDao.numeroOrdreService(codeService.toUpperCase()));
//		return ResponseEntity.ok().body(map);
	}

//	@GetMapping(value = "/getNumeroOrdreByCode/{code}")
//	public NumeroOrdre getNumeroOrdreByCode(@PathVariable String code) {
//		// contactRepository.delete(id);
//
//		return  exerciceDao.findByCodeEquals(code);
//
//
//	}



	@PostMapping("/ajoutNumeroOrdre")
	public NumeroOrdre ajoutTypeActe(@RequestBody NumeroOrdre userForm) {

		NumeroOrdre userSearch = exerciceDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			exerciceDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteNumeroOrdre/{id}")
	public boolean deleteTypeActe(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		NumeroOrdre exercice = exerciceDao.findByIdIs(id);
		
		if (exercice != null) {
			exerciceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePNumeroOrdre/{id}")
	public NumeroOrdre updateTypeActe(@PathVariable Long id) {

		NumeroOrdre exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	
	@PatchMapping(value = "/mergeNumeroOrdre/{id}")
	public NumeroOrdre updatePartielTypeActe(@PathVariable Long id) {

		NumeroOrdre exercice = exerciceDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return exerciceDao.save(exercice);
		}
		return exercice;
	}
	@PatchMapping(value = "/updateNumeroOrdre/{numeroOrdre}/{codeService}")
	public NumeroOrdre updateNumeroOrdre(@PathVariable Integer numeroOrdre,@PathVariable String codeService) {
	NumeroOrdre numeroORdre = exerciceDao.numeroOrdreServiceObjet(codeService);
		if (numeroORdre != null) {

			numeroORdre.setNumeroOrdre(numeroOrdre);
			return exerciceDao.saveAndFlush(numeroORdre);
		}
		return numeroORdre;
	}

	@PatchMapping(value = "/resetNumeroOrdre/{codeService}")
	public NumeroOrdre resetNumeroOrdre(@PathVariable String codeService) {
	NumeroOrdre numeroORdre = exerciceDao.numeroOrdreServiceObjet(codeService);
		if (numeroORdre != null) {

			numeroORdre.setNumeroOrdre(0);
			return exerciceDao.saveAndFlush(numeroORdre);
		}
		return numeroORdre;
	}

}
