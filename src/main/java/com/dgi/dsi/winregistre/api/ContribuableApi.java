package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.ContribuableDao;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.ContribuableBis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.websocket.server.PathParam;


@RestController
@CrossOrigin("*")
public class ContribuableApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private ContribuableDao contribuableDao;
	private ContribuableDao getContribuableDao;


	@GetMapping(value = "/listContribuables")
	public List<ContribuableBis> getContribuables() {
		return contribuableDao.findAll();
	}


	@GetMapping(value = "/listContribuablesByPage")
	public Page<ContribuableBis> listContribuablesByPage(@RequestParam(value="page", required=true)  int page, @RequestParam(value="size", required=true)  int size, @RequestParam(value="keyWord", required=false)  String keyWord) {

		Page<ContribuableBis> contribuableBis;
		if(keyWord == null || keyWord.isEmpty()) {
			Pageable firstPageWithTwoElements = PageRequest.of(page, size);
			contribuableBis = contribuableDao.listeConstriByPage("%"+""+"%", firstPageWithTwoElements);

		}else {
			Pageable firstPageWithTwoElements = PageRequest.of(page, size);
			contribuableBis = contribuableDao.listeConstriByPage("%"+keyWord.toUpperCase()+"%", firstPageWithTwoElements);

		}
			
				return contribuableBis;
	}
	


	@GetMapping(value = "/getContribuableByIfu/{ifu}")
	public ContribuableBis getContribuableByIfu(@PathVariable String ifu) {
		// contactRepository.delete(id);

		ContribuableBis contribuableBis = contribuableDao.findAllByContImmatr(ifu);
//		System.out.println(contribuableBis.toString());

			return contribuableBis;
	}

	@GetMapping(value = "/getContribuableByRaisonSocial/{raisoc}")
	public  List<ContribuableBis>  getContribuableByRaisonSocial(@PathVariable String raisoc) {
		// contactRepository.delete(id);

		List<ContribuableBis>  contribuableBis = contribuableDao.findByContRaisLike("%"+raisoc.toUpperCase()+"%");

		return contribuableBis;

	}

	@GetMapping(value = "/getContribuableByNom/{nom}")
	public  List<ContribuableBis>  getContribuableByNom(@PathVariable String nom) {
		// contactRepository.delete(id);

		List<ContribuableBis>  contribuableBis = contribuableDao.findByContNomLike("%"+nom.toUpperCase()+"%");

		return contribuableBis;

	}


}
