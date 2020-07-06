package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.*;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.service.AjoutQuittanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin("*")
public class QuittanceApi {


//	@Autowired
//	private ExerciceDao exerciceDao;
	
	@Autowired
	private QuittanceDao quittanceDao;

	@Autowired
	private ActeDao acteDao;


	@Autowired
	private BordereauCaisseDao bordereauCaisseDao;


	@Autowired
	private AgentDao agentDao;

	@Autowired
	private AjoutQuittanceService ajoutQuittanceService;






	@GetMapping(value = "/oneBordereauQuittance/{numeroQuittance}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public BordereauCaisse getBordereauCaisse(@PathVariable String numeroQuittance) {


		System.out.println(numeroQuittance);

		Quittance quittance = quittanceDao.findByNumeroActe(numeroQuittance);
		if(quittance != null) {
			BordereauCaisse bordereauCaisse = bordereauCaisseDao.findOne(quittance.getBordereauCaisse().getId());


			if (bordereauCaisse == null) {
				return null;
			} else {
				System.out.println(bordereauCaisse);
				return bordereauCaisse;
			}
		}else{
			return null;
		}
//        List<Acte> listActe = acteDao.findOne(bordereauActe0.getId());


	}


	@GetMapping(value = "/listQuittanceBordereauCaisse/{numBordereauCaisse}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Quittance> getlistQuittancesBordereauCaisse(@PathVariable String numBordereauCaisse) {


		System.out.println(numBordereauCaisse);

		BordereauCaisse bordereauCaisse = bordereauCaisseDao.findByNumeroEquals(numBordereauCaisse);


		if(bordereauCaisse ==null){
			return null;
		}else{
			return quittanceDao.listQuittanceBordereauCaisse(bordereauCaisse.getId());
		}

	}


	@GetMapping(value = "/lastNumberQuittancesBordereauCaisse/{numBordereauCaisse}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public String getNumLastQuittancesBordereauCaisse(@PathVariable String numBordereauCaisse) {

		System.out.println("----------------numBordereauCaisse-------------------"+ numBordereauCaisse);

		BordereauCaisse bordereauCaisse = bordereauCaisseDao.findByNumeroEquals(numBordereauCaisse);

		if (bordereauCaisse != null){
			System.out.println("-----------bordereauCaisse.getId()----------"+ bordereauCaisse.getId());
			Long idBordereauCaisse = quittanceDao.maxIdByBordereauCaisse(bordereauCaisse.getId());
//        idBordereauActe = idBordereauActe==null ?1L: idBordereauActe;
			System.out.println("----------------idBordereauCaisse-------------------"+idBordereauCaisse);

			if (idBordereauCaisse != null){
				Quittance quittance = quittanceDao.findOne(idBordereauCaisse);

				System.out.println("----------------getNumeroQuittance-------------------"+quittance.getNumeroQuittance());
				String[] decoupe = quittance.getNumeroQuittance().split("_");

				System.out.println(quittance.getNumeroQuittance());
//				return decoupe[1];
				return quittance.getNumeroQuittance();
			}else{
				return null;
			}

		}else{
			return null;
		}



	}

	@GetMapping(value = "/lastBordereauCaisseByAgent/{matricule}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public BordereauCaisse getNumLastBordereauCaisseByMatricule(@PathVariable String matricule) {

		System.out.println("----------------matricule-------------------"+ matricule);

		Agent agent = agentDao.findByMatricule(matricule);

		if (agent != null){
			System.out.println("-----------bordereauCaisse.getId()----------"+ agent.getId());
			Long idBordereauCaisse = quittanceDao.maxIdBordereauCaisseByAgent(agent.getId());
//        idBordereauActe = idBordereauActe==null ?1L: idBordereauActe;
			System.out.println("----------------idBordereauCaisse-------------------"+idBordereauCaisse);

			if (idBordereauCaisse != null){
				BordereauCaisse  bordereauCaisse = bordereauCaisseDao.findOne(idBordereauCaisse);


				return bordereauCaisse;
			}else{
				return null;
			}

		}else{
			return null;
		}



	}




	@GetMapping(value = "/listQuittances")
	public List<Quittance> getQuittances() {
		return quittanceDao.findAll();
	}


	@PostMapping("/ajoutQuittance")
	public Quittance ajoutQuittance(@RequestBody Quittance userForm) {


		System.out.println("---------------------------------Ajout---------------------------------"+userForm);

		Quittance quittance = ajoutQuittanceService.ajoutQuittance(userForm);


//		System.out.println("---------------------------------Ajout 2---------------------------------"+ ajoutQuittanceService.ajoutQuittance(userForm));
		return quittance;

	}
	
	
	@DeleteMapping(value = "/deleteQuittance/{id}")
	public boolean deleteQuittance(@PathVariable Long id) {
		// contactRepository.delete(id);

		Quittance exercice = quittanceDao.findOne(id);
		
		if (exercice != null) {
			quittanceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePQuittance/{id}")
	public Quittance updateQuittance(@PathVariable Long id) {

		Quittance exercice = quittanceDao.findOne(id);
		if (exercice != null) {
			exercice.setId(id);
			return quittanceDao.save(exercice);
		}
		return exercice;
	}

		@GetMapping(value = "/searchQuittanceByCode/{code}")
	public Quittance updateQuittanceDaoByCode (@PathVariable String code) {

//		return quittanceDao.findByCodeLike("%"+code+"%");

			return null;

	}

	@PatchMapping(value = "/mergeQuittance")
	public Quittance updatePartielQuittance( @Valid @RequestBody Quittance banque) {

		Quittance banqueRecherchee = quittanceDao.findOne(banque.getId());
		if (banqueRecherchee != null) {
			banque.setId(banqueRecherchee.getId());
			return quittanceDao.save(banque);
		}else{
			quittanceDao.save(banque);
		}
		return banque;
	}

}
