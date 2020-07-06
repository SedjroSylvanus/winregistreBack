package com.dgi.dsi.winregistre.api;


import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;

import com.dgi.dsi.winregistre.dao.TarifDao;
import com.dgi.dsi.winregistre.dao.TypePenaliteAmendeDao;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.Tarif;
import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
import com.dgi.dsi.winregistre.payload.EntrePaiement;
import com.dgi.dsi.winregistre.payload.SortiePaiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


@RestController
@CrossOrigin("*")
public class CalculPenaliteASS {

  @Autowired
  private  NatureActeDao natureActeDao;

  @Autowired
  private TypePenaliteAmendeDao typePenaliteAmendeDao;

   @Autowired
  private ActeDao acteDao;




    public Double CalculPenaliteActeSS(Acte acte){

        System.out.println("-------------------CalculPenalitéActe----" + acte);
        String communeActe;
        Long dureeActe;
        Double majoration = 0.0;
        Double droitSimple = 0.0;
        Double penalite = 0.0;
        LocalDate dateMajoration = LocalDate.now();

//        LocalDate dateFormatter = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate dateActe =  LocalDate.parse(acte.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate dateEnregistrement =  LocalDate.parse(acte.getDateEnregistrement().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        System.out.println(acte.getNatureActe().getId());
        NatureActe natureActe = natureActeDao.findOne(acte.getNatureActe().getId());

         System.out.println("-----je suis ici- début récupération---");


        if(natureActe != null) {

        Double montantMinNatureActe = natureActe.getMtMinimum() == null?0.0:natureActe.getMtMinimum();
        Double montantMinNatureActeTypePenAmende = natureActe.getTypePenaliteAmende().getMtMinimum() == null?0.0:natureActe.getTypePenaliteAmende().getMtMinimum();
        Double montantMaxNatureActeTypePenAmende = natureActe.getTypePenaliteAmende().getMtMaximum() == null?0.0:natureActe.getTypePenaliteAmende().getMtMaximum();

        TypePenaliteAmende typePenaliteAmende = typePenaliteAmendeDao.findOne( natureActe.getTypePenaliteAmende().getId());
        Double montantPenaliteAmendeNatureActeTypePenaliteAmende = typePenaliteAmende.getMtPenaliteAmende()==null?0.0:typePenaliteAmende.getMtPenaliteAmende();
        Double montantPenaliteEcheanceNatureActeTypePenaliteAmende = typePenaliteAmende.getMtPenaliteEcheance()==null?0.0:typePenaliteAmende.getMtPenaliteEcheance();


        Double montantFixePenaliteNatureActe = natureActe.getMtFixePenalite()==null?0.0:natureActe.getMtFixePenalite();
        Double tauxFixePenaliteNatureActe = natureActe.getTauxFixePenalite()==null?0.0:natureActe.getTauxFixePenalite();
        Boolean acteCommuneCentreEnregistrement = acte.getCommuneActe().getCentreEnregistrement()==null?Boolean.FALSE:Boolean.TRUE;
        String actenatureActeTypePenAmendeJourMoisAnnee = typePenaliteAmende.getJourMoisAnnee()==null? "T" :typePenaliteAmende.getJourMoisAnnee();

        System.out.println("-----je suis ici- fin récupération---");

            //Durée plus 1 pour prendre un piquet + 2 pour les deux piquets
            dureeActe = ChronoUnit.DAYS.between( dateEnregistrement, dateActe)+1;

            System.out.println("--->Durée entre date acte et date enrégistrement " + dureeActe);
//            dureeActe = dureeActe+2;
            System.out.println("--->Durée entre date acte et date enrégistrement Bis " + dureeActe);

            JourFerieService jourFerie = new JourFerieService();
            System.out.println("------acteCommuneCentreEnregistrement--------"+acteCommuneCentreEnregistrement );
            if (acteCommuneCentreEnregistrement == true) {
                System.out.println(actenatureActeTypePenAmendeJourMoisAnnee);
                if (actenatureActeTypePenAmendeJourMoisAnnee == "J") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.DAYS);
                }

                if (actenatureActeTypePenAmendeJourMoisAnnee == "M") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.MONTHS);
                }
                if (actenatureActeTypePenAmendeJourMoisAnnee == "A") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.YEARS);
                }
            } else {
                if (actenatureActeTypePenAmendeJourMoisAnnee == "J") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.DAYS);
                }
                if (actenatureActeTypePenAmendeJourMoisAnnee == "M") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.MONTHS);
                }
                if (actenatureActeTypePenAmendeJourMoisAnnee == "A") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.YEARS);
                }

            }

            System.out.println("--->Date de l'acte" + acte.getDateActe());
            System.out.println("--->Date de l'Enregistrement" + acte.getDateEnregistrement());
            //Jour Férié
            if (jourFerie.isFerie(acte.getDateEnregistrement().toString()) == true) {

                dateEnregistrement = dateEnregistrement.plus(1, ChronoUnit.DAYS);
//            acte.setDateActe(acte.getDateActe().plus(1, ChronoUnit.DAYS));
                System.out.println("---------Ferie" + dateEnregistrement);
                if (jourFerie.isSamedi(acte.getDateEnregistrement().toString()) == true) {

                    dateEnregistrement = dateEnregistrement.plus(2, ChronoUnit.DAYS);
//                acte.setDateActe(acte.getDateActe().plus(2, ChronoUnit.DAYS));
                    System.out.println("---------FerieSamedi" + dateEnregistrement);
                }
                if (jourFerie.isDimanche(acte.getDateEnregistrement().toString()) == true) {

                    dateEnregistrement = dateEnregistrement.plus(1, ChronoUnit.DAYS);
//                acte.setDateActe(acte.getDateActe().plus(1, ChronoUnit.DAYS));
                    System.out.println("---------FerieDimanche" + dateEnregistrement);
                }
            }

            dureeActe = ChronoUnit.DAYS.between(dateEnregistrement, dateActe)+1;
            System.out.println("---> date de l'acte apres férié" + dateActe + "---durée" + dureeActe);

            if (dureeActe <= 0) {
                penalite = 0.0;

            } else {


                System.out.println("Avant le Switch de CalculPenAss  "+natureActe.getCode());
                switch (natureActe.getCode()) {

                    case "404_409": {

//                        ////////////////consultation de la grille ///////////////////////////
//                        duReretard est un numérique= DateDifférence(dAteecheance,SAI_Act_DateEnr)
//                        anneretard est un entier= ArrondiSupérieur(duReretard/365)
//                        ///// recherche du taux///////////////////////////////////////////////
//                        HExécuteRequête(REQ_SelectTauxPenSUS,hRequêteDéfaut,anneretard)
//                        HLitPremier(REQ_SelectTauxPenSUS)
//                        SI PAS HEnDehors(REQ_SelectTauxPenSUS) ALORS
//                        taux est un réel=REQ_SelectTauxPenSUS.taux
//                        penalite= taux* SAI_Act_DS
//                        FIN

                        penalite = droitSimple * 1; // taux a rechercher * droit simple
                        break;
                    }
                    case "409BIS_1": {
                        dateMajoration = dateActe.plus(3, ChronoUnit.MONTHS);
                        while (ChronoUnit.DAYS.between(dateMajoration, dateEnregistrement) > 0) {

                            majoration += montantPenaliteAmendeNatureActeTypePenaliteAmende;
                            dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
                        }

                        penalite = montantPenaliteAmendeNatureActeTypePenaliteAmende + majoration;
                        break;
                    }
                    case "409BIS_2": {
                        penalite = montantPenaliteEcheanceNatureActeTypePenaliteAmende;
                        break;

                    }
                    case "FIXE": {

                        penalite = montantFixePenaliteNatureActe;
                        break;

                    }
                    case "TAUXFIXE": {

                        penalite = tauxFixePenaliteNatureActe * droitSimple; //Droit simple

                        break;

                    }
                    case "SUC_MUT": {


                        Double montantMajoration = 0.;
                         ////////pénalité pour les mutations par succession///////////
                        if(droitSimple == montantMinNatureActe){
                            while(ChronoUnit.DAYS.between(dateMajoration, dateEnregistrement) > 0){
                                montantMajoration = montantMajoration + natureActe.getTypePenaliteAmende().getMtPenaliteAmende();
                                dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
                            }
                        }else{
                            while(ChronoUnit.DAYS.between(dateMajoration, dateEnregistrement) > 0){
                                montantMajoration = droitSimple/100;
                                dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
                            }
                        }


                        penalite=montantMajoration;
                        if(penalite < montantMinNatureActe){
                            penalite = natureActe.getMtMinimum();
                        }

                        if(droitSimple/2 < penalite){
                            penalite = droitSimple/2;
                        }



                        break;

                    }


                    default:

                        return 0.;
//                    System.out.println("Calcul non pris en compte");

                }
            }
            if (penalite < montantMinNatureActeTypePenAmende) {

            }
            if (montantMaxNatureActeTypePenAmende == 0.) {
            } else {
                if (penalite > montantMaxNatureActeTypePenAmende) {

                    penalite = montantMaxNatureActeTypePenAmende;
                }
            }
        }else{
            System.out.println("Valeur Non Trouvé");
        }
            return penalite;
        }


}






