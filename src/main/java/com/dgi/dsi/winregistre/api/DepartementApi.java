package com.dgi.dsi.winregistre.api;

import java.util.ArrayList;
import java.util.List;


import com.dgi.dsi.winregistre.entites.DegreSuccession;
import com.dgi.dsi.winregistre.payload.DepartementPayload;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.dgi.dsi.winregistre.dao.DepartementDao;

import com.dgi.dsi.winregistre.entites.Departement;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
//@RequestMapping("/departement")
public class DepartementApi {


//	@Autowired
//	private departementDao departementDao;
	
	@Autowired
	private DepartementDao departementDao;


	@GetMapping(value = "/listDepartements")
	public List<Departement> getDepartements() {
		return departementDao.findAll();
	}

	@GetMapping(value = "/listDepartementsAudit")
	public List getDepartementsAudit() {
		List<Departement> listeDepartementAudit = new ArrayList<>();

		departementDao.listDepAudit().forEach(s-> System.out.println(s.toString()));
		return departementDao.listDepAudit();
	}

	@PostMapping("/ajoutDepartement")
	public Departement ajoutDepartement(@RequestBody Departement userForm) {

		Departement userSearch = departementDao.findByIdIs(userForm.getId());

		if (userSearch == null) {
			departementDao.save(userForm);
		} else {

			userForm.setId(userSearch.getId());
			departementDao.save(userForm);
		}



		return userForm;

	}
	

	@GetMapping(value = "/searchDepartementByCode/{code}")
	public Departement updateDepartementByCode (@PathVariable String code) {

		return departementDao.findByCodeLike("%"+code+"%");

	}

	@DeleteMapping(value = "/deleteDepartement")
	public boolean deleteDepartement(@RequestBody Departement userForm) {
		// contactRepository.delete(id);
		
		Departement exercice = departementDao.findByIdIs(userForm.getId());
		
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
	
	@PatchMapping(value = "/mergeDepartement")
	public Departement updatePartielDepartement(@Valid @RequestBody Departement departement) {

		Departement departementRech = departementDao.findByIdIs(departement.getId());
		if (departementRech != null) {
			departement.setId(departementRech.getId());
			return departementDao.save(departement);
		}else{
			departementDao.save(departement);
		}
		return departement;
	}


}
