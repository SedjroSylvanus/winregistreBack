package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.BanqueDao;
import com.dgi.dsi.winregistre.dao.ContribuableDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.Contribuable;

import com.dgi.dsi.winregistre.entites.ContribuableBis;
import com.dgi.dsi.winregistre.entites.EXPORT_TABLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("*")
public class ContribuableApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private ContribuableDao contribuableDao;


	@GetMapping(value = "/listContribuables")
	public List<ContribuableBis> getContribuables() {
		return contribuableDao.findAll();
	}




	@GetMapping(value = "/getContribuableByIfu/{ifu}")
	public ContribuableBis getContribuableByIfu(@PathVariable String ifu) {
		// contactRepository.delete(id);

		ContribuableBis contribuableBis = contribuableDao.getContribuableBisByIfu(ifu);


			return contribuableBis;



	}

	@GetMapping(value = "/getContribuableByRaisonSocial/{raisoc}")
	public  List<ContribuableBis>  getContribuableByRaisonSocial(@PathVariable String raisoc) {
		// contactRepository.delete(id);

		List<ContribuableBis>  contribuableBis = contribuableDao.findByCONT_RAISLike("%"+raisoc+"%");

		return contribuableBis;

	}

	@GetMapping(value = "/getContribuableByNom/{nom}")
	public  List<ContribuableBis>  getContribuableByNom(@PathVariable String nom) {
		// contactRepository.delete(id);

		List<ContribuableBis>  contribuableBis = contribuableDao.findByCONT_NOMLike("%"+nom+"%");

		return contribuableBis;

	}


}
