package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.payload.EntrePaiement;
import com.dgi.dsi.winregistre.payload.SortiePaiement;
import com.dgi.dsi.winregistre.dao.NatureActeDao;

//import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class ActeApi {

//	@Autowired
//	private agentDao agentDao;

	@Autowired
	private ActeDao acteDao;

	@Autowired
	private NatureActeDao natureActeDao;

	@Autowired
	private CalculDroit calculDroit;

	@Autowired
	private WorkingOnController workingOnController;








    @GetMapping(value = "/listActes")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Acte> getActes() {

		return acteDao.findAll();
//		return ((acteDao.findAll()) != null) ?
//				Response.ok(gson.toJson(acteDao.findAll())).build() :
//				Response.status(Response.Status.NOT_FOUND).build();
	}




    @PostMapping("/calculPaiementAss")
	public SortiePaiement calculPaiementAss( @RequestBody Acte userForm) {


		return calculDroit.calculPaiement(userForm);

	}

    @PostMapping("/ajoutActe")
	public Acte ajoutActe( @Valid @RequestBody Acte userForm) {
		System.out.println(userForm.getDateActe().toString());

		Acte userSearch = acteDao.findOne(userForm.getId());


		if (userSearch == null) {

//			Date date = new Date();
			userForm.setPenalite(calculDroit.calculPaiement(userForm).getPenalite());
			userForm.setAmende(calculDroit.calculPaiement(userForm).getAmende());
			userForm.setDroitSimple(calculDroit.calculPaiement(userForm).getDroitSimple());
			userForm.setManqueeGain(calculDroit.calculPaiement(userForm).getManqueeGain());
			userForm.setRedevance(calculDroit.calculPaiement(userForm).getRedevance());
			userForm.setDateActe(LocalDate.parse(userForm.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
			userForm.setDateEnregistrement(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
			Double mtDu = (Double) calculDroit.calculPaiement(userForm).getAmende()+
					calculDroit.calculPaiement(userForm).getDroitSimple()+
					calculDroit.calculPaiement(userForm).getRedevance()+
					calculDroit.calculPaiement(userForm).getPenalite();
			userForm.setMontantDu(mtDu);
			userForm.setMontantPaye(0.0);
//			workingOnController.addTache(new WorkingOn(userForm, LocalDate.now(),userForm.getAgentLiquidateur()));
			System.out.println(workingOnController.getAll());
			acteDao.saveAndFlush(userForm);
		} else {

			userForm.setId(userForm.getId());
			userForm.setPenalite(calculDroit.calculPaiement(userForm).getPenalite());
			userForm.setAmende(calculDroit.calculPaiement(userForm).getAmende());
			userForm.setDroitSimple(calculDroit.calculPaiement(userForm).getDroitSimple());
			userForm.setManqueeGain(calculDroit.calculPaiement(userForm).getManqueeGain());
			userForm.setRedevance(calculDroit.calculPaiement(userForm).getRedevance());
			userForm.setDateActe(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
			userForm.setDateEnregistrement(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));

//			workingOnController.addTache(new WorkingOn(userForm, LocalDate.now(),userForm.getAgentLiquidateur()));
			System.out.println(workingOnController.getAll());
			Double mtDu = (Double) calculDroit.calculPaiement(userForm).getAmende()+
					calculDroit.calculPaiement(userForm).getDroitSimple()+
					calculDroit.calculPaiement(userForm).getRedevance()+
					calculDroit.calculPaiement(userForm).getPenalite();
			mtDu +=  userForm.getMontantDu();
			Double mtPaye = mtDu - userForm.getMontantPaye();
			userForm.setMontantDu(mtDu);
//			userForm.setMontantPaye(mtDu);

			acteDao.saveAndFlush(userForm);
			throw new RuntimeException(userSearch + "Acte inexistant");
		}

		return userForm;

	}

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






//	//////////Méthode de calcul
//
//
//    public  Double CalculPenaliteActeSS(EntrePaiement acte){
//
//
//        String communeActe;
//        Long dureeActe;
//        Double majoration = 0.0;
//        Double droitSimple = 0.0;
//        Double penalite = 0.0;
//
////        LocalDate dateFormatter = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
//        LocalDate dateActe =  LocalDate.parse(acte.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
//        LocalDate dateEnregistrement =  LocalDate.parse(acte.getDateEnregistrement().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
//        System.out.println(acte.getNatureActe().getId());
//        NatureActe natureActe = natureActeDao.findOne(acte.getNatureActe().getId());
//
//
//        if(natureActe != null) {
//            dureeActe = ChronoUnit.DAYS.between(dateActe, dateEnregistrement);
//            System.out.println("--->Durée entre date acte et date enrégistrement " + dureeActe);
//            JourFerieService jourFerie = new JourFerieService();
//
//            if (acte.getCommuneActe().getCentreEnregistrement() == true) {
//                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "J") {
//                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.DAYS);
//                }
//
//                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "M") {
//                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.MONTHS);
//                }
//                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "A") {
//                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.YEARS);
//                }
//            } else {
//                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "J") {
//                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.DAYS);
//                }
//                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "M") {
//                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.MONTHS);
//                }
//                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "A") {
//                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.YEARS);
//                }
//
//            }
//            System.out.println("--->Date de l'acte" + acte.getDateActe());
//            System.out.println("--->Date de l'Enregistrement" + acte.getDateEnregistrement());
//            //Jour Férié
//            if (jourFerie.isFerie(acte.getDateActe().toString()) == true) {
//
//                dateActe = dateActe.plus(1, ChronoUnit.DAYS);
////            acte.setDateActe(acte.getDateActe().plus(1, ChronoUnit.DAYS));
//                System.out.println("---------Ferie" + dateActe);
//                if (jourFerie.isSamedi(acte.getDateActe().toString()) == true) {
//
//                    dateActe = dateActe.plus(2, ChronoUnit.DAYS);
////                acte.setDateActe(acte.getDateActe().plus(2, ChronoUnit.DAYS));
//                    System.out.println("---------FerieSamedi" + dateActe);
//                }
//                if (jourFerie.isDimanche(acte.getDateActe().toString()) == true) {
//
//                    dateActe = dateActe.plus(1, ChronoUnit.DAYS);
////                acte.setDateActe(acte.getDateActe().plus(1, ChronoUnit.DAYS));
//                    System.out.println("---------FerieDimanche" + dateActe);
//                }
//            }
//
//            dureeActe = ChronoUnit.DAYS.between(dateActe, dateEnregistrement);
//            System.out.println("---> date de l'acte apres férié" + dateActe + "---durée" + dureeActe);
//
//            if (dureeActe <= 0) {
//                penalite = 0.0;
//
//            } else {
//
//
//                System.out.println(acte.getNatureActe().getCode());
//                switch (natureActe.getCode()) {
//
//                    case "404_409": {
//
//                        penalite = droitSimple * 1; // taux a rechercher * droit simple
//                        break;
//                    }
//                    case "409BIS_1": {
//                        LocalDate dateMajoration = dateActe.plus(3, ChronoUnit.MONTHS);
//                        while (ChronoUnit.DAYS.between(dateMajoration, dateEnregistrement) > 0) {
//
//                            majoration += natureActe.getTypePenaliteAmende().getMtPenaliteAmende();
//                            dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
//                        }
//
//                        penalite = natureActe.getTypePenaliteAmende().getMtPenaliteEcheance() + majoration;
//                        break;
//                    }
//                    case "409BIS_2": {
//                        penalite = natureActe.getTypePenaliteAmende().getMtPenaliteEcheance();
//                        break;
//
//                    }
//                    case "FIXE": {
//
//                        penalite = natureActe.getMtFixePenalite();
//                        break;
//
//                    }
//                    case "TAUXFIXE": {
//
//                        penalite = natureActe.getTauxFixePenalite() * droitSimple; //Droit simple
//
//                        break;
//
//                    }
//                    case "SUC_MUT": {
//
//
//                        break;
//
//                    }
//
//
//                    default:
//
//                        return penalite;
////                    System.out.println("Calcul non pris en compte");
//
//                }
//            }
//            if (penalite < natureActe.getTypePenaliteAmende().getMtMaximum()) {
//
//            }
//            if (natureActe.getTypePenaliteAmende().getMtMaximum() == 0) {
//            } else {
//                if (penalite > acte.getNatureActe().getTypePenaliteAmende().getMtMaximum()) {
//
//                    penalite = natureActe.getTypePenaliteAmende().getMtMaximum();
//                }
//            }
//        }else{
//            System.out.println("Valeur Non Trouvé");
//        }
//        return penalite;
//    }



}
