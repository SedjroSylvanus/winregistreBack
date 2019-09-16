package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.payload.EntrePaiement;
import com.dgi.dsi.winregistre.payload.SortiePaiement;
import com.dgi.dsi.winregistre.service.CalculDroit;
import com.dgi.dsi.winregistre.service.JourFerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

//import org.jfree.util.Log;

@RestController
@CrossOrigin("*")
public class GlobalApi {

//	@Autowired
//	private agentDao agentDao;

	@Autowired
	private ActeDao acteDao;

	@Autowired
	private CalculDroit calculDroit;

	@Autowired
	private WorkingOnController workingOnController;








	@GetMapping(value = "/dateServeur")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public LocalDate getDateServeur() {
		return LocalDate.parse(LocalDate.now().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH));
	}


    @GetMapping(value = "/listActes")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Acte> getActes() {

		return acteDao.findAll();
//		return ((acteDao.findAll()) != null) ?
//				Response.ok(gson.toJson(acteDao.findAll())).build() :
//				Response.status(Response.Status.NOT_FOUND).build();
	}

    @GetMapping(value = "/isSamedi/{dateTest}")
    public Boolean isSamedi(@PathVariable String dateTest) {
        JourFerieService testFonctionJF = new JourFerieService();

        return testFonctionJF.isSamedi(dateTest);


    }


    @GetMapping(value = "/isDimanche/{date}")
    public Boolean isDimanche(@PathVariable String date) {
        JourFerieService testFonctionJF = new JourFerieService();
	    return testFonctionJF.isDimanche(date);
    }


    @GetMapping(value = "/isFerie/{date}")
    public Boolean isFerie(@PathVariable String date) {
        JourFerieService testFonctionJF = new JourFerieService();
	    return testFonctionJF.isFerie(date);
    }



    @PostMapping("/calculPaiementAss")
	public SortiePaiement calculPaiementAss( @RequestBody EntrePaiement userForm) {


		return calculDroit.calculPaiement(userForm);

	}

//    @PostMapping("/ajoutActe")
//	public Acte ajoutActe( @Valid @RequestBody Acte userForm) {
//		System.out.println(userForm.getDateActe().toString());
//
//		Acte userSearch = acteDao.findOne(userForm.getId());
//
//
//		if (userSearch == null) {
//
////			Date date = new Date();
//			userForm.setPenalite(calculDroit.calculPaiement(userForm).getPenalite());
//			userForm.setAmende(calculDroit.calculPaiement(userForm).getAmende());
//			userForm.setDroitSimple(calculDroit.calculPaiement(userForm).getDroitSimple());
//			userForm.setManqueeGain(calculDroit.calculPaiement(userForm).getManqueeGain());
//			userForm.setRedevance(calculDroit.calculPaiement(userForm).getRedevance());
//			userForm.setDateActe(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
//			userForm.setDateEnregistrement(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
//			Double mtDu = (Double) calculDroit.calculPaiement(userForm).getAmende()+calculDroit.calculPaiement(userForm).getDroitSimple()+
//					calculDroit.calculPaiement(userForm).getRedevance()+ calculDroit.calculPaiement(userForm).getPenalite();
//			userForm.setMontantDu(mtDu);
//			userForm.setMontantPaye(0.0);
////			workingOnController.addTache(new WorkingOn(userForm, LocalDate.now(),userForm.getAgentLiquidateur()));
//			System.out.println(workingOnController.getAll());
//			acteDao.saveAndFlush(userForm);
//		} else {
//
//			userForm.setId(userForm.getId());
//			userForm.setPenalite(calculDroit.calculPaiement(userForm).getPenalite());
//			userForm.setAmende(calculDroit.calculPaiement(userForm).getAmende());
//			userForm.setDroitSimple(calculDroit.calculPaiement(userForm).getDroitSimple());
//			userForm.setManqueeGain(calculDroit.calculPaiement(userForm).getManqueeGain());
//			userForm.setRedevance(calculDroit.calculPaiement(userForm).getRedevance());
//			userForm.setDateActe(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
//			userForm.setDateEnregistrement(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
//
////			workingOnController.addTache(new WorkingOn(userForm, LocalDate.now(),userForm.getAgentLiquidateur()));
//			System.out.println(workingOnController.getAll());
//			Double mtDu = (Double) calculDroit.calculPaiement(userForm).getAmende()+calculDroit.calculPaiement(userForm).getDroitSimple()+
//					calculDroit.calculPaiement(userForm).getRedevance()+ calculDroit.calculPaiement(userForm).getPenalite();
//			Double mtPaye = mtDu - userForm.getMontantPaye();
//			userForm.setMontantDu(mtPaye);
//
//			acteDao.saveAndFlush(userForm);
//			throw new RuntimeException(userSearch + "Acte inexistant");
//		}
//
//		return userForm;
//
//	}

	@DeleteMapping(value = "/deleteActe/{id}")
	public Acte deleteActe(@PathVariable Long id) {
		// contactRepository.delete(id);

		Acte acte = acteDao.findOne(id);

		if (acte != null) {

			return acte;
		} else {

			return null;
		}

	}

	@PutMapping(value = "/mergePActe/{numero}")
	public Acte updateAgent( @Valid @RequestBody Acte userForm,
                              @PathVariable String numero) {

        Acte acte = acteDao.findByNumeroEquals(numero);
        if (acte != null) {

            userForm.setId(acte.getId());
            acteDao.saveAndFlush(userForm);

        } else {

            acteDao.saveAndFlush(acte);
        }
        return acte;
	}

	@PatchMapping(value = "/mergeActe")
//	@PatchMapping(value = "/mergeAgent/{matricule}")
	public Acte updatePartielActe( @Valid @RequestBody Acte userForm) {


        Acte acte = acteDao.findByNumeroEquals(userForm.getNumero());

        if (acte != null) {

            userForm.setId(acte.getId());
            acteDao.saveAndFlush(userForm);

        } else {

            acteDao.saveAndFlush(acte);
        }
        return acte;
	}




}
