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
public  class CalculDroit {

    @Autowired
    private  NatureActeDao natureActeDao;

    @Autowired
    private TarifDao tarifDao;

    @Autowired
    private ActeDao acteDao;

    @Autowired
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




    public SortiePaiement calculPaiement(Acte acte ) {
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
//            valeurTimbre =  acte.getValeurTimbre();
//            nombreTimbre =  acte.getNombreTimbre();
            Double sommeTimbre = valeurTimbre*nombreTimbre;
            sortiePaiement = new SortiePaiement(
                    penalite, amende, redevance, droitSimple, manqueGain
            );
        }else{
            System.out.println("La Nature choisie est incorrecte");
        }
        return sortiePaiement;
    }



}