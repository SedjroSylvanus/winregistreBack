package com.dgi.dsi.winregistre.api;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.dgi.dsi.winregistre.dao.DepartementDao;

import com.dgi.dsi.winregistre.entites.Departement;



@RestController
@CrossOrigin("*")
@RequestMapping("/departement")
public class DepartementApi {


//	@Autowired
//	private departementDao departementDao;
	
	@Autowired
	private DepartementDao departementDao;


	@GetMapping(value = "/listDepartements")
	public List<Departement> getDepartements() {
		return departementDao.findAll();
	}

	@PostMapping("/ajoutDepartement")
	public Departement ajoutDepartement(@RequestBody Departement userForm) {

		Departement userSearch = departementDao.findOne(userForm.getId());

		if (userSearch == null) {
			departementDao.save(userForm);
		} else {

			userForm.setId(userSearch.getId());
			departementDao.save(userForm);
		}



		return userForm;

	}
	
	
	@DeleteMapping(value = "/deleteDepartement/{id}")
	public boolean deleteDepartement(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		Departement exercice = departementDao.findOne(id);
		
		if (exercice != null) {
			departementDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PostMapping(value = "/mergePDepartement")
	public Departement updateDepartement(@RequestBody Departement userForm) {

		if (userForm != null) {

			return departementDao.save(userForm);
		}
		return userForm;
	}
	
	@PatchMapping(value = "/mergeDepartement/{id}")
	public Departement updatePartielDepartement(@PathVariable Long id) {

		Departement exercice = departementDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return departementDao.save(exercice);
		}
		return exercice;
	}

}
