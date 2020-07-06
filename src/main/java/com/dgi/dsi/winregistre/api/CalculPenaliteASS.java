package com.dgi.dsi.winregistre.api;


import com.dgi.dsi.winregistre.dao.*;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.GrilleActeSousSeingPrive;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
import com.dgi.dsi.winregistre.service.JourFerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


//@RestController
//@CrossOrigin("*")
@Service
public class CalculPenaliteASS {

  @Autowired
  private  NatureActeDao natureActeDao;

  @Autowired
  private TypePenaliteAmendeDao typePenaliteAmendeDao;

   @Autowired
  private ActeDao acteDao;
   @Autowired
  private GrilleActeSousSeingPriveDao grilleActeSousSeingPriveDao;


    private String communeActe;
    private Long dureeActe;
    private Double majoration = 0.0;
    private Double droitSimple = 0.0;
    private Double penalite = 0.0;
    private LocalDate dateActe;
    private LocalDate dateMajoration;
    private Boolean ferie = Boolean.FALSE;

    @Autowired
    private JourFerieService jourFerie; // = new JourFerieService();

//    jourFerie.
//
//    = Lists.newArrayList(
//            LocalDate.of(year, Month.JANUARY, 1));


    
//
//    public LocalDate dateEcheance(String numeroActe) {
//    	
//    	
//    	return LocalDate.now();
//    }
//    
//    
    
    


    public Double CalculPenaliteActeSS(Acte acte){

        System.out.println("-------------------CalculPenalitéActe----" + acte);

        dateActe =  LocalDate.parse(acte.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        dateMajoration = dateActe;

//        LocalDate dateFormatter = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate dateEcheance = dateActe;  // =  LocalDate.parse(acte.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate dateEnregistrement =  LocalDate.parse(acte.getDateEnregistrement().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
//        System.out.println(acte.getNatureActe().getId());
//        if(acte.getNatureActe()!=null) {

            NatureActe natureActe = acte.getNatureActe()== null ? null :acte.getNatureActe(); //natureActeDao.findByCodeEquals(acte.getNatureActe().getCode());
            if (natureActe != null) {
                System.out.println("Nature Acte <> null");
                Double montantMinNatureActe = natureActe.getMtMinimum() == null ? 0.0 : natureActe.getMtMinimum();
                Double montantMinNatureActeTypePenAmende = natureActe.getTypePenaliteAmende().getMtMinimum() == null ? 0.0 : natureActe.getTypePenaliteAmende().getMtMinimum();
                Double montantMaxNatureActeTypePenAmende = natureActe.getTypePenaliteAmende().getMtMaximum() == null ? 0.0 : natureActe.getTypePenaliteAmende().getMtMaximum();
                droitSimple = acte.getDroitSimple() == null ? 0.0 : acte.getDroitSimple();

                System.out.println("Step 1");

                TypePenaliteAmende typePenaliteAmende = natureActe.getTypePenaliteAmende(); //typePenaliteAmendeDao.findByCodeEquals(natureActe.getTypePenaliteAmende().getCode());
                String coodeTypePenaliteAmende = typePenaliteAmende == null ? null : typePenaliteAmende.getCode();
                Double montantPenaliteAmendeNatureActeTypePenaliteAmende = typePenaliteAmende == null ? 0.0 : typePenaliteAmende.getMtPenaliteAmende();
                Double montantPenaliteEcheanceNatureActeTypePenaliteAmende = typePenaliteAmende == null ? 0.0 : typePenaliteAmende.getMtPenaliteEcheance();

                System.out.println("Step 2");
                System.out.println("<<<<<<<<<<>>>>>>>>>>>"+natureActe.getTypePenaliteAmende());

                Double periodicite = typePenaliteAmende.getMtPeriode() == null ? 0.: typePenaliteAmende.getMtPeriode();
                Integer echeance =  typePenaliteAmende.getDelaiEcheance() == null ? 0: typePenaliteAmende.getDelaiEcheance();


                System.out.println("step 3"+acte.getCommuneActe());

                Double montantFixePenaliteNatureActe =  natureActe.getMtFixePenalite()== null? 0. : natureActe.getMtFixePenalite();
                majoration =  typePenaliteAmende.getMtPenaliteEcheance()== null? 0. : typePenaliteAmende.getMtPenaliteEcheance();
                Double tauxFixePenaliteNatureActe =  natureActe.getTauxFixePenalite()== null? 0. : natureActe.getTauxFixePenalite();
//               voir en base la valuer de ses montants puis en interface
                Boolean acteCommuneCentreEnregistrement = acte.getCommuneActe() == null ? Boolean.FALSE : acte.getCommuneActe().getCentreEnregistrement();
                System.out.println("step 4");

                String actenatureActeTypePenAmendeJourMoisAnnee = typePenaliteAmende == null ? "N/A" : typePenaliteAmende.getJourMoisAnnee();

                System.out.println("-----je suis ici- fin récupération---");



                System.out.println("------acteCommuneCentreEnregistrement--------" + acteCommuneCentreEnregistrement);

                if (acteCommuneCentreEnregistrement == true) {
                    System.out.println(actenatureActeTypePenAmendeJourMoisAnnee);

                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("J")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(typePenaliteAmende.getDelaiEcheance(), ChronoUnit.DAYS);
                        System.out.println("JJJJ--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }

                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("M")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(typePenaliteAmende.getDelaiEcheance(), ChronoUnit.MONTHS);
                        System.out.println("MMMMM--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);
                    }
                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("A")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(typePenaliteAmende.getDelaiEcheance(), ChronoUnit.YEARS);
                        System.out.println("AAAA--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }
                } else {
                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("J")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(2 * (typePenaliteAmende.getDelaiEcheance()), ChronoUnit.DAYS);
                        System.out.println("JJJJ Fasle--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }
                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("M")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(2 * (typePenaliteAmende.getDelaiEcheance()), ChronoUnit.MONTHS);
                        System.out.println("MMM Fasle--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }
                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("A")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(2 * (typePenaliteAmende.getDelaiEcheance()), ChronoUnit.YEARS);
                        System.out.println("AAAA Fasle--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }

                }

                System.out.println("--->Date de l'acte" + acte.getDateActe());
                System.out.println("--->Date de l'Echéance" + dateEcheance);
                System.out.println("--->Date de l'Enregistrement" + acte.getDateEnregistrement());

                //Jour Férié
                
                dateEcheance = operationFerie(dateEcheance);

                while (ferie.equals(Boolean.TRUE)){
                    dateEcheance = operationFerie(dateEcheance);
                }
                
//                do {
//                    dateEcheance = operationFerie(dateEcheance);
//
//				} while (ferie.equals(true));
						


						

//                if (jourFerie.isFerie(dateEcheance.toString()) == true) {
//                    dateEcheance = dateEcheance.plus(1, ChronoUnit.DAYS);
//
//                    System.out.println("---------Ferie" + dateEcheance);
//                    if (jourFerie.isSamedi(dateEcheance.toString()) == true) {
//
//                        dateEcheance = dateEcheance.plus(2, ChronoUnit.DAYS);
//
//                        System.out.println("---------FerieSamedi" + dateEcheance);
//                    }
//                    if (jourFerie.isDimanche(dateEcheance.toString()) == true) {
//
//                        dateEcheance = dateEcheance.plus(1, ChronoUnit.DAYS);
//
//                        System.out.println("---------FerieDimanche" + dateEnregistrement);
//                    }
//                }

                //Durée plus 1 pour prendre un piquet + 2 pour les deux piquets
                Long dureeActeJour = ChronoUnit.DAYS.between(dateEnregistrement, dateActe) ;
                Long dureeActeJourEcheance = ChronoUnit.DAYS.between(dateEnregistrement, dateEcheance) ;
                System.out.println("--->Durée Jour entre date acte et date enrégistrement " + dureeActeJour);
                System.out.println("--->Durée Jour entre date Echéance et date enrégistrement " + dureeActeJourEcheance);


                dureeActe = ChronoUnit.MONTHS.between(dateEnregistrement, dateEcheance); // + 2;
                Long dureeActeAnnee = ChronoUnit.YEARS.between(dateEcheance, dateEnregistrement); // + 2;
                System.out.println("---> date de l'acte apres férié" + dateEcheance + "---durée  " + dureeActe);
                System.out.println("---> Durée en mois" + dureeActe + "---durée en année  " + dureeActeAnnee);

                if (dureeActeJourEcheance >= 0) {
                    System.out.println("(((((((((((((((((000000000)))))))))))))))))))))");
                    penalite = 0.0;

                } else {

//                    if(EnumNatActeCode.ACTE_CODE_404_409.getCode().equalsIgnoreCase(natureActe.getCode())){
//
//                    }else {
//                        if(EnumNatActeCode.ACTE_CODE_409BIS_1.getCode().equalsIgnoreCase(natureActe.getCode())){
//
//                        }else {
//                            if(EnumNatActeCode.ACTE_CODE_409BIS_2.getCode().equalsIgnoreCase(natureActe.getCode())){
//
//                            }else {
//                                if(EnumNatActeCode.ACTE_CODE_FIXE.getCode().equalsIgnoreCase(natureActe.getCode())){
//
//                                }else {
//                                    if(EnumNatActeCode.ACTE_CODE_TAUXFIXE.getCode().equalsIgnoreCase(natureActe.getCode())){
//
//                                    }else {
//                                        if(EnumNatActeCode.ACTE_CODE_SUC_MUT.getCode().equalsIgnoreCase(natureActe.getCode())){
//
//                                        }else {
//
//                                        }
//
//                                    }
//
//                                }
//
//                            }
//
//                        }
//
//                    }


                        switch (coodeTypePenaliteAmende) {

                        case "404_409": {

                            //                        ////////////////consultation de la grille ///////////////////////////
//                        duReretard est un numérique= DateDifférence(dAteecheance,SAI_Act_DateEnr)
//                        anneretard est un entier= ArrondiSupérieur(duReretard/365)
//                        ///// recherche du taux///////////////////////////////////////////////
//                        HExécuteRequête(REQ_SelectTauxPenSUS,hRequêteDéfaut,anneretard)
//                        HLitPremier(REQ_SelectTauxPenSUS)
//                        SI PAS HEnDehors(REQ_SelectTauxPenSUS) ALORS
//                        taux est un réel=REQ_SelectTauxPenSUS.taux
//                        penalite= taux * SAI_Act_DS
//                        FIN

//                        recherche de taux dans grille acte sous seing privé

                            if(dureeActeAnnee < 0L){
                                dureeActeAnnee = ChronoUnit.YEARS.between(dateEnregistrement, dateEcheance);
                            }

                                GrilleActeSousSeingPrive GrilleSSP = grilleActeSousSeingPriveDao.findByDelaiMaxGreaterThanAndDelaiMinLessThanEqual(Integer.parseInt(dureeActeAnnee.toString()), Integer.parseInt(dureeActeAnnee.toString()));
                                System.out.println("((((((((((((((((()))))))))))))))))))))"+GrilleSSP);
                                penalite = droitSimple * (GrilleSSP == null? 0.: GrilleSSP.getTaux());



                               break;
                        }
                        case "409BIS_1": {

                            dateMajoration = dateEcheance.plus(3, ChronoUnit.MONTHS);
                            while (majoration <= montantMaxNatureActeTypePenAmende
                                    & ChronoUnit.MONTHS.between(dateMajoration, dateEnregistrement) > 0L
                                    & ChronoUnit.YEARS.between(dateMajoration, dateEnregistrement) >= 0L
                                    ) {

                                System.out.println("--- 409BIS_1 Boucle -------->"+ ChronoUnit.MONTHS.between(dateMajoration, dateEnregistrement));
                                majoration += montantPenaliteAmendeNatureActeTypePenaliteAmende;

                                System.out.println("--- 409BIS_1 majoration --------> "+ majoration);
//                                Integer recu = Integer.parseInt(periodicite.toString());
                                dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
                                System.out.println("--- 409BIS_1 dateMajoration ----->" + dateMajoration);

                            }

                            penalite = montantPenaliteAmendeNatureActeTypePenaliteAmende + majoration;
                            break;
                        }
                        case "409BIS_2": {
                            penalite = montantPenaliteEcheanceNatureActeTypePenaliteAmende;
                            break;

                        }
                        case "FIXE": {

                            penalite = /*natureActe.getMtFixePenalite()== null? 0. : natureActe.getMtFixePenalite();*/
                                    montantFixePenaliteNatureActe;
                            break;

                        }
                        case "TAUXFIXE": {

                            penalite = tauxFixePenaliteNatureActe  /*(natureActe.getTauxFixePenalite()== null? 0. : natureActe.getTauxFixePenalite())*/
                                    * droitSimple; //Droit simple

                            break;

                        }
                        case "SUC_MUT": {


                            Double montantMajoration = 0.;
                            ////////pénalité pour les mutations par succession///////////
                            if (droitSimple == montantMinNatureActe) {
                                while (ChronoUnit.MONTHS.between(dateMajoration, dateEnregistrement) < 0) {
                                    montantMajoration +=  typePenaliteAmende.getMtPenaliteAmende();
                                    dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
                                }
                            } else {
                                while (ChronoUnit.MONTHS.between(dateMajoration, dateEnregistrement) < 0) {
                                    montantMajoration = droitSimple / 100;
                                    dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
                                }
                            }


                            penalite = montantMajoration;
                            if(penalite!=0.) {
                                if (penalite < montantMinNatureActe) {
                                    penalite = natureActe.getMtMinimum();
                                }

                                if (droitSimple / 2 < penalite) {
                                    penalite = droitSimple / 2;
                                }
                            }

                            break;

                        }


                        default: {
                            System.out.println("Calcul de Pénalité non pris en compte CalculPenaliteActeSS");
                            return 0.;
                        }

                    }
                }
                if(penalite!=0.) {
                    if (penalite < montantMinNatureActeTypePenAmende) {

                        penalite = montantMinNatureActeTypePenAmende;
                    }
                    if (montantMaxNatureActeTypePenAmende == 0.) {
                    } else {
                        if (penalite > montantMaxNatureActeTypePenAmende) {

                            penalite = montantMaxNatureActeTypePenAmende;
                        }
                    }
                }
            } else {
                System.out.println("La nature d'acte est null");
            }


            return penalite;

        }


    
    

    public LocalDate dateEcheance(Acte acte){

        System.out.println("-------------------dateEchéance----" + acte);

        dateActe =  LocalDate.parse(acte.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        dateMajoration = dateActe;

        LocalDate dateEcheance = dateActe;  // =  LocalDate.parse(acte.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate dateEnregistrement =  LocalDate.parse(acte.getDateEnregistrement().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));

            NatureActe natureActe = acte.getNatureActe()== null ? null :acte.getNatureActe(); //natureActeDao.findByCodeEquals(acte.getNatureActe().getCode());
            if (natureActe != null) {
                
            	System.out.println("Nature Acte <> null");

                TypePenaliteAmende typePenaliteAmende = natureActe.getTypePenaliteAmende(); //typePenaliteAmendeDao.findByCodeEquals(natureActe.getTypePenaliteAmende().getCode());

                Double periodicite = typePenaliteAmende.getMtPeriode() == null ? 0.: typePenaliteAmende.getMtPeriode();
                Integer echeance =  typePenaliteAmende.getDelaiEcheance() == null ? 0: typePenaliteAmende.getDelaiEcheance();

                System.out.println("---------echeance---------------> "+ echeance);

                System.out.println("step 3"+acte.getCommuneActe());


//               voir en base la valuer de ses montants puis en interface
                Boolean acteCommuneCentreEnregistrement = acte.getCommuneActe() == null ? Boolean.FALSE : acte.getCommuneActe().getCentreEnregistrement();
                System.out.println("step 4");

                String actenatureActeTypePenAmendeJourMoisAnnee = typePenaliteAmende == null ? "N/A" : typePenaliteAmende.getJourMoisAnnee();


                if (acteCommuneCentreEnregistrement == true) {
                    System.out.println(actenatureActeTypePenAmendeJourMoisAnnee);

                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("J")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(typePenaliteAmende.getDelaiEcheance(), ChronoUnit.DAYS);
                        System.out.println("JJJJ--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }

                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("M")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(typePenaliteAmende.getDelaiEcheance(), ChronoUnit.MONTHS);
                        System.out.println("MMMMM--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);
                    }
                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("A")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(typePenaliteAmende.getDelaiEcheance(), ChronoUnit.YEARS);
                        System.out.println("AAAA--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }
                } else {
                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("J")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(2 * (typePenaliteAmende.getDelaiEcheance()), ChronoUnit.DAYS);
                        System.out.println("JJJJ Fasle--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }
                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("M")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(2 * (typePenaliteAmende.getDelaiEcheance()), ChronoUnit.MONTHS);
                        System.out.println("MMM Fasle--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }
                    if (actenatureActeTypePenAmendeJourMoisAnnee.equalsIgnoreCase("A")) { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                        dateEcheance = dateActe.plus(2 * (typePenaliteAmende.getDelaiEcheance()), ChronoUnit.YEARS);
                        System.out.println("AAAA Fasle--- "+dateEcheance+" actenatureActeTypePenAmendeJourMoisAnnee "+actenatureActeTypePenAmendeJourMoisAnnee);

                    }

                }

                System.out.println("--->Date de l'acte" + acte.getDateActe());
                System.out.println("--->Date de l'Echéance" + dateEcheance);
                System.out.println("--->Date de l'Enregistrement" + acte.getDateEnregistrement());

                //Jour Férié
                
                dateEcheance = operationFerie(dateEcheance);

                while (ferie.equals(Boolean.TRUE)){
                    dateEcheance = operationFerie(dateEcheance);
                }
                


            } else {
                System.out.println("La nature d'acte est null");
            }


            return dateEcheance;

        }

    

        public LocalDate operationFerie(LocalDate ld){

            //Jour Férié
            if (jourFerie.isFerie(ld.toString()) == true) {
                ld = ld.plus(1, ChronoUnit.DAYS);

                ferie = Boolean.TRUE;
                System.out.println("---------Ferie" + ld);
                if (jourFerie.isSamedi(ld.toString()) == true) {

                    ld = ld.plus(2, ChronoUnit.DAYS);
                    ferie = Boolean.TRUE;
                    System.out.println("---------FerieSamedi" + ld);
                }else{
                    ferie = Boolean.FALSE;
                }
                if (jourFerie.isDimanche(ld.toString()) == true) {

                    ld = ld.plus(1, ChronoUnit.DAYS);
                    ferie = Boolean.TRUE;
                    System.out.println("---------FerieDimanche" + ld);
                }else{
                    ferie = Boolean.FALSE;
                }
            }else{
                ferie = Boolean.FALSE;
            }
            return  ld;


        }


}






