package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.GrilleActeSousSeingPriveDao;
import com.dgi.dsi.winregistre.entites.Direction;
import com.dgi.dsi.winregistre.entites.GrilleActeSousSeingPrive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("*")
public class GrilleActeSousSeingPriveApi {


//	@Autowired
//	private ExerciceDao grilleActeSousSeingPriveDao;
	
	@Autowired
	private GrilleActeSousSeingPriveDao grilleActeSousSeingPriveDao;


	@GetMapping(value = "/listGrilleActeSousSeingPrives")
	public List<GrilleActeSousSeingPrive> getGrilleActeSousSeingPrives() {
		return grilleActeSousSeingPriveDao.findAll();
	}


//	@GetMapping(value = "/oneGrilleActeSousSeingPriveByDesignation/{designation}")
//	public GrilleActeSousSeingPrive getGrilleActeSousSeingPriveByDesignation(@PathVariable String designation) {
//
//		return grilleActeSousSeingPriveDao.findByDesignationEquals(designation);
//	}



	@PostMapping("/ajoutGrilleActeSousSeingPrive")
	public GrilleActeSousSeingPrive ajoutGrilleActeSousSeingPrive(@RequestBody GrilleActeSousSeingPrive userForm) {

		GrilleActeSousSeingPrive userSearch = grilleActeSousSeingPriveDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			grilleActeSousSeingPriveDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteGrilleActeSousSeingPrive/{id}")
	public boolean deleteGrilleActeSousSeingPrive(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		GrilleActeSousSeingPrive exercice = grilleActeSousSeingPriveDao.findByIdIs(id);
		
		if (exercice != null) {
			grilleActeSousSeingPriveDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePGrilleActeSousSeingPrive/{id}")
	public GrilleActeSousSeingPrive updateGrilleActeSousSeingPrive(@PathVariable Long id) {

		GrilleActeSousSeingPrive exercice = grilleActeSousSeingPriveDao.findByIdIs(id);
		if (exercice != null) {
			exercice.setId(id);
			return grilleActeSousSeingPriveDao.save(exercice);
		}
		return exercice;
	}

//		@GetMapping(value = "/searchGrilleActeSousSeingPriveByCode/{code}")
//	public GrilleActeSousSeingPrive updateGrilleActeSousSeingPriveByCode (@PathVariable String code) {
//
//		return grilleActeSousSeingPriveDao.findByCodeLike("%"+code+"%");
//
//
//	}

	@PatchMapping(value = "/mergeGrilleActeSousSeingPrive")
	public GrilleActeSousSeingPrive updatePartielGrilleActeSousSeingPrive( @Valid @RequestBody GrilleActeSousSeingPrive grilleActeSousSeingPrive) {

		GrilleActeSousSeingPrive grilleActeSousSeingPriveRecherchee = grilleActeSousSeingPriveDao.findByIdIs(grilleActeSousSeingPrive.getId());
		if (grilleActeSousSeingPriveRecherchee != null) {
			grilleActeSousSeingPrive.setId(grilleActeSousSeingPriveRecherchee.getId());
			return grilleActeSousSeingPriveDao.save(grilleActeSousSeingPrive);
		}else{
			grilleActeSousSeingPriveDao.save(grilleActeSousSeingPrive);
		}
		return grilleActeSousSeingPrive;
	}



	@GetMapping(value = "/grilleActeSousSeingPriveByCode/{code}")
	public GrilleActeSousSeingPrive grilleActeSousSeingPriveByCode(@PathVariable String code) {

		return grilleActeSousSeingPriveDao.grilleActeSousSeingPriveByCode(code);

	}
}
