package com.dgi.dsi.winregistre.api;


import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;

import com.dgi.dsi.winregistre.dao.TarifDao;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.Tarif;
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
  private ActeDao acteDao;




    public Double CalculPenaliteActeSS(Acte acte){


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


        if(natureActe != null) {
            dureeActe = ChronoUnit.DAYS.between( dateEnregistrement, dateActe);
            System.out.println("--->Durée entre date acte et date enrégistrement " + dureeActe);
            JourFerieService jourFerie = new JourFerieService();

            if (acte.getCommuneActe().getCentreEnregistrement() == true) {
                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "J") {
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.DAYS);
                }

                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "M") {
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.MONTHS);
                }
                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "A") {
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.YEARS);
                }
            } else {
                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "J") {
                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.DAYS);
                }
                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "M") {
                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.MONTHS);
                }
                if (acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee() == "A") {
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

            dureeActe = ChronoUnit.DAYS.between(dateEnregistrement, dateActe);
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

                            majoration += natureActe.getTypePenaliteAmende().getMtPenaliteAmende();
                            dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
                        }

                        penalite = natureActe.getTypePenaliteAmende().getMtPenaliteEcheance() + majoration;
                        break;
                    }
                    case "409BIS_2": {
                        penalite = natureActe.getTypePenaliteAmende().getMtPenaliteEcheance();
                        break;

                    }
                    case "FIXE": {

                        penalite = natureActe.getMtFixePenalite();
                        break;

                    }
                    case "TAUXFIXE": {

                        penalite = natureActe.getTauxFixePenalite() * droitSimple; //Droit simple

                        break;

                    }
                    case "SUC_MUT": {


                        Double montantMajoration = 0.;
                        ////////pénalité pour les mutations par succession///////////
                        if(droitSimple == natureActe.getMtMinimum()){
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
                        if(penalite < natureActe.getMtMinimum()){
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
            if (penalite < natureActe.getTypePenaliteAmende().getMtMaximum()) {

            }
            if (natureActe.getTypePenaliteAmende().getMtMaximum() == 0) {
            } else {
                if (penalite > acte.getNatureActe().getTypePenaliteAmende().getMtMaximum()) {

                    penalite = natureActe.getTypePenaliteAmende().getMtMaximum();
                }
            }
        }else{
            System.out.println("Valeur Non Trouvé");
        }
            return penalite;
        }


}






