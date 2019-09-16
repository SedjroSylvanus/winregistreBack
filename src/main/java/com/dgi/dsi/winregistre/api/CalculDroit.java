package com.dgi.dsi.winregistre.api;


import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;

import com.dgi.dsi.winregistre.dao.TarifDao;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.Tarif;
import com.dgi.dsi.winregistre.payload.EntrePaiement;
import com.dgi.dsi.winregistre.payload.SortiePaiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Service
@Transactional
public class CalculPenaliteASS {

  @Autowired
  private  NatureActeDao natureActeDao;

   @Autowired
  private ActeDao acteDao;




    public Double CalculPenaliteActeSS(EntrePaiement acte){


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

    @Service
    @Transactional
    public static class CalculDroit {

      @Autowired
      private  NatureActeDao natureActeDao;

      @Autowired
      private TarifDao tarifDao;

      @Autowired
      private ActeDao acteDao;

      @Resource
      private CalculPenaliteASS calculPenaliteASS;



        private Double droitSimple = 0.0;
      private Double penalite = 0.0;
      private Double amende = 0.0;
      private Double redevance = 0.0;
      private Double droitEnregistrement = 0.0;
      private Double netAPayer = 0.0;
      private Double valeurTimbre = 1.;//A créer dans acte
      private Double nombreTimbre = 1.;//A créer dans acte
      private Double manqueGain = 0.;
    //  private Acte acte;
    //  private LocalDate dateEnregistrement;
    //  private LocalDate dateActe;




      public SortiePaiement calculPaiement(EntrePaiement acte ) {
          CalculbaseImpoSucc calculbaseImpoSucc = new CalculbaseImpoSucc();

          SortiePaiement sortiePaiement = new SortiePaiement();
    //      CalculPenaliteASS calculPenaliteASS = new CalculPenaliteASS();


    //      Acte acteSearch = acteDao.findOne(acte.);

          NatureActe NatureActe = natureActeDao.findOne(acte.getNatureActe().getId());
          System.out.println(NatureActe);

          if (NatureActe != null) {

              System.out.println(NatureActe.getTarif().getCode());
              System.out.println(NatureActe);


                Tarif tarif = tarifDao.findOne(NatureActe.getTarif().getId());
                System.out.println(tarif);

              switch (tarif.getCode()) {

                  case "Fx": {
                      droitSimple = NatureActe.getMtFixe();
                      System.out.println("droit simple Fx "+droitSimple);
    //          if(droitSimple<acte.getNatureActe().getMtMinimum()){
    //              droitSimple = acte.getNatureActe().getMtMinimum();
    //          }
                      break;
                  }
                  case "TAUX ET MIN": {
                      droitSimple = acte.getMontantActe() * NatureActe.getTaux();
                      if (droitSimple < NatureActe.getMtMinimum()) {
                          droitSimple = NatureActe.getMtMinimum();
                      }
                      break;
                  }
                  case "TAUX": {

                      droitSimple = acte.getMontantActe() * NatureActe.getTaux();

                      break;

                  }
                  case "TAUX PROGRESSIF": {


                      Double cumulDroitSimple =0.;
                      Double montantBaseImp = acte.getMontantActe();
                      Integer nombreEnfant = 1;
                      if(0<calculbaseImpoSucc.baseImpotSucc(montantBaseImp,nombreEnfant)){
                          montantBaseImp = calculbaseImpoSucc.baseImpotSucc(montantBaseImp,nombreEnfant);
                      }


    //                  HExécuteRequête(REQ_Tranchecorrespondante,hRequêteDéfaut,COMBO_T_DEGRESUCCESSION,moBaseimp)
    //                  HLitPremier(REQ_Tranchecorrespondante)
    //                  TANTQUE PAS HEnDehors(REQ_Tranchecorrespondante)
    //                  SI moBaseimp > REQ_Tranchecorrespondante.TR_BMax ALORS
    //                  cumulds+= (REQ_Tranchecorrespondante.TR_BMax-REQ_Tranchecorrespondante.TR_BMin)* REQ_Tranchecorrespondante.Pourcentage
    //                  SINON
    //                  cumulds += (moBaseimp-REQ_Tranchecorrespondante.TR_BMin)*REQ_Tranchecorrespondante.Pourcentage
    //                  FIN
    //                  HLitSuivant(REQ_Tranchecorrespondante)
    //                  FIN

                      droitSimple = montantBaseImp;
                      break;

                  }


                  default: {

                      System.out.println("Calcul Droit non pris en compte");
                      return sortiePaiement;
                  }


              }

              if (droitSimple == 0 || NatureActe.getGratis() == true) {
                  penalite = 0.0;
                  amende = calculPenaliteASS.CalculPenaliteActeSS(acte);
              } else {
                  penalite = calculPenaliteASS.CalculPenaliteActeSS(acte);
                  amende = 0.0;
              }

              System.out.println("retour Calcul Droit");
              redevance = acte.getMontantActe() * NatureActe.getTauxRedevance();
              if (redevance < NatureActe.getMinRedevance()) {
                  redevance = NatureActe.getMinRedevance();
              }

    //          droitEnregistrement = droitSimple + amende + penalite;
    //          netAPayer = droitEnregistrement + redevance + (valeurTimbre * nombreTimbre);

              if (NatureActe.getGratis() == true) {
                  manqueGain = droitSimple;
                  droitSimple = 0.0;

    //          HLitPremier(T_Parametre_Global)
    //          SI PAS HEnDehors(T_Parametre_Global) ALORS
    //          SI PAS T_Parametre_Global.DateLoiFin="" ALORS
    //          SI T_Parametre_Global.DateLoiFin>SAI_Act_Date ALORS
    //                  SAI_Act_Pen=0
    //          SAI_Act_Amende=0
    //          FIN
    //                  FIN
    //          FIN

    //              droitEnregistrement = droitSimple + amende + penalite;
    //              netAPayer = droitEnregistrement + redevance + (valeurTimbre * nombreTimbre);

                  if (NatureActe.getAncienPA() == null) {
                      manqueGain = calculPenaliteASS.CalculPenaliteActeSS(acte);//méthode de calcul de pénalité
                  }


                  System.out.println();
              }
              valeurTimbre =  acte.getTimbre().getValeurTimbre();
              nombreTimbre =  acte.getTimbre().getNombreTimbre();
              Double sommeTimbre = valeurTimbre*nombreTimbre;
              sortiePaiement = new SortiePaiement(
                      penalite, amende, redevance, droitSimple, manqueGain, sommeTimbre
              );
          }else{
              System.out.println("La Nature choisie est incorrecte");
          }
       return sortiePaiement;
      }



    }
}






