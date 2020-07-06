package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.*;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.service.AjoutQuittanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import sun.rmi.transport.proxy.HttpReceiveSocket;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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



    @GetMapping(value = "/lastNumberQuittanceInBordereauCaisse/{numBordereauCaisse}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public String getLastNumQuittanceInBordereau(@PathVariable String numBordereauCaisse) {

        System.out.println("----------------numBordereau-------------------"+ numBordereauCaisse);
        if(numBordereauCaisse.isEmpty() || numBordereauCaisse == null){
            return null;
        }else{

            BordereauCaisse bordCaisse = bordereauCaisseDao.findByNumeroEquals(numBordereauCaisse.toUpperCase());

            if (bordCaisse != null){
                System.out.println("-----------bordActe0.getId()----------"+ bordCaisse.getId());
                Long idBordereauCaisse = quittanceDao.maxIdByBordereauCaisse(bordCaisse.getId());

                System.out.println("----------------idBordereauCaisse-------------------"+idBordereauCaisse);

                if (idBordereauCaisse != null){
                	Quittance bordCaisseee = quittanceDao.findByIdIs(idBordereauCaisse);

                    String[] decoupe = bordCaisseee.getNumeroQuittance().split("_");

                    System.out.println("----------->"+bordCaisseee.getNumeroQuittance());
                    System.out.println("--->"+(decoupe.length -1));
                    return decoupe[decoupe.length -1];
                }else{
                    return null;
                }

            }else{
                return null;
            }
        }

    }
    



	@GetMapping(value = "/oneBordereauQuittance/{numeroQuittance}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public BordereauCaisse getBordereauCaisse(@PathVariable String numeroQuittance) {


		System.out.println(numeroQuittance);

		Quittance quittance = quittanceDao.findByNumeroActe(numeroQuittance);
		if(quittance != null) {
			BordereauCaisse bordereauCaisse = bordereauCaisseDao.findByIdIs(quittance.getBordereauCaisse().getId());


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


	@GetMapping(value = "/lastNumberQuittancesBordereauCaisse/{numBordereauCaisse}/{codeNatureImpot}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public ResponseEntity<?> getNumLastQuittancesBordereauCaisse(HttpServletRequest req, @PathVariable String numBordereauCaisse, @PathVariable String codeNatureImpot) {

		System.out.println("----------------numBordereauCaisse-------------------"+ numBordereauCaisse);

		BordereauCaisse bordereauCaisse = bordereauCaisseDao.findByNumeroEquals(numBordereauCaisse);
		Map<String, String>  map = new HashMap();

		if (bordereauCaisse != null){
			System.out.println("-----------bordereauCaisse.getId()----------"+ bordereauCaisse.getId());
			Long idBordereauCaisse = quittanceDao.maxIdByBordereauCaisse(bordereauCaisse.getId());




			if (idBordereauCaisse != null ){
				Quittance quittance = quittanceDao.findByIdIs(idBordereauCaisse);

				System.out.println("----------------getNumeroQuittance-------------------"+quittance.getNumeroQuittance());
				String[] decoupe = quittance.getNumeroQuittance().split("_");

				System.out.println("------>>>>>>>>>>>><<<<<<<<<<<<<<< "+quittance.getNumeroQuittance());

				map.put("numeroQuittance",quittance.getNumeroQuittance());

				return ResponseEntity.ok()
						.body(map);

			}else{
				return null; // ResponseEntity.notFound().build();

			}

		}else{
			System.out.println("----------------numBordereauCaisse 0000-------------------"+ numBordereauCaisse);

			
			Long idAgent = authAgentApi.whoami(req).getId();

			Long idBordereauCaisseR = quittanceDao.maxBordereauCaisseAgentAvecQuittance(idAgent);
			System.out.println("------num Bordereau Caisse idBordereauCaisseR-------------------"+ idBordereauCaisseR);

			if(idBordereauCaisseR != null) {
			Long maxIdQuittance = quittanceDao.maxIdByBordereauCaisse(idBordereauCaisseR);

			System.out.println("------maxIdQuittance -------------------"+ maxIdQuittance);

			Quittance quittance = quittanceDao.findByIdIs(maxIdQuittance);


			if(quittance == null) {
				System.out.println("Quittance Null");
				return null;
			}else {
			map.put("numeroQuittance",quittance.getNumeroQuittance());

			return ResponseEntity.ok()
					.body(map);

				
			}
			}else {
				return null;
			}

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


			if (idBordereauCaisse != null){
				BordereauCaisse  bordereauCaisse = bordereauCaisseDao.findByIdIs(idBordereauCaisse);


				return bordereauCaisse;
			}else{
				return null;
			}

		}else{
			return null;
		}



	}


	@Autowired
	private AuthAgentApi authAgentApi;



	@GetMapping(value = "/listQuittances")
	public List<Quittance> getQuittances() {
		return quittanceDao.findAll();
	}


	@PostMapping("/ajoutQuittance")
	public Quittance ajoutQuittance(HttpServletRequest req, @RequestBody Quittance userForm) {


		System.out.println("---------------------------------Ajout---------------------------------"+userForm);

			
		Quittance quittance = ajoutQuittanceService.ajoutQuittance(req, userForm);

		return quittance;

	}
	
	
	@DeleteMapping(value = "/deleteQuittance/{id}")
	public boolean deleteQuittance(@PathVariable Long id) {
		// contactRepository.delete(id);

		Quittance exercice = quittanceDao.findByIdIs(id);
		
		if (exercice != null) {
			quittanceDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePQuittance/{id}")
	public Quittance updateQuittance(@PathVariable Long id) {

		Quittance exercice = quittanceDao.findByIdIs(id);
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
	public Quittance updatePartielQuittance( @Valid @RequestBody Quittance quittance) {

		System.out.println("-----------------------------MAJ------------------------------");
//		Quittance quittanceRecherchee = quittanceDao.findByIdIs(quittance.getId());
		return quittanceDao.saveAndFlush(quittance);

//		if (quittanceRecherchee != null) {
//			quittance.setId(quittanceRecherchee.getId());
//			return quittanceDao.save(quittance);
//		}else{
//			quittanceDao.save(quittance);
//		}
//		return quittance;
	}

}
