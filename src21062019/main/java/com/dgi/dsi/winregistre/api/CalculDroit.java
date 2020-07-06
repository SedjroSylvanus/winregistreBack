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
    private Double valeurTimbre = 0.;//A créer dans acte
    private Double nombreTimbre = 1.;//A créer dans acte
    private Double manqueGain = 0.;
    //  private Acte acte;
    //  private LocalDate dateEnregistrement;
    //  private LocalDate dateActe;




    public SortiePaiement calculPaiement(Acte acte ) throws Exception {

         System.out.println("----------------DÉBUT calculPaiement-----------------"+acte);
        CalculbaseImpoSucc calculbaseImpoSucc = new CalculbaseImpoSucc();

        SortiePaiement sortiePaiement = new SortiePaiement();
        //      CalculPenaliteASS calculPenaliteASS = new CalculPenaliteASS();


        //      Acte acteSearch = acteDao.findOne(acte.);

        if (acte.getNatureActe() != null) {
        NatureActe NatureActe = natureActeDao.findOne(acte.getNatureActe().getId());
        System.out.println(NatureActe);

//        if (NatureActe != null) {

            valeurTimbre = acte.getValeurTimbre()==null?0.0:acte.getValeurTimbre();

            nombreTimbre = acte.getNombreTimbre()==null?0.0:acte.getNombreTimbre();


            System.out.println(NatureActe.getTarif().getCode());
            System.out.println(NatureActe);


            Tarif tarif = tarifDao.findOne(NatureActe.getTarif().getId());
            System.out.println(tarif);

            //éviter les valeur par défaut null des types numériques
            Double montantActe = acte.getMontantActe()==null?0.0:acte.getMontantActe();
            Double tauxNatureActe =  NatureActe.getTaux()==null?0.0:NatureActe.getTaux();
            Double montantFixeNatureActe =  NatureActe.getMtFixe()==null?0.0:NatureActe.getMtFixe();
            Double montantMinNatureActe = NatureActe.getMtMinimum() == null?0.0:NatureActe.getMtMinimum();
            Boolean gratisNatureActe = NatureActe.getGratis() == null?Boolean.FALSE:Boolean.TRUE;
            Double tauxRedevanceNatureActe = acte.getMontantActe()==null?0.0:acte.getMontantActe();


            System.out.println("Fin Initialisation dans calculPaiement---------------");


            switch (tarif.getCode()) {

                case "FIXE": {
                    droitSimple = montantFixeNatureActe;
                    System.out.println("droit simple Fx "+droitSimple);
                    //          if(droitSimple<acte.getNatureActe().getMtMinimum()){
                    //              droitSimple = acte.getNatureActe().getMtMinimum();
                    //          }
                    break;
                }
                case "TAUX ET MIN": {
                     droitSimple = montantActe*tauxNatureActe;
                     if (droitSimple < montantMinNatureActe) {
                        droitSimple = montantMinNatureActe;
                    }
                    break;
                }
                case "TAUX": {

                    droitSimple = montantActe * tauxNatureActe;

                    break;

                }
                case "TAUX PROGRESSIF": {


                    Double cumulDroitSimple =0.;
                    Double montantBaseImp = montantActe;
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


            if (droitSimple == 0 || gratisNatureActe == true) {
                penalite = 0.0;
                amende = calculPenaliteASS.CalculPenaliteActeSS(acte);
            } else {
                penalite = calculPenaliteASS.CalculPenaliteActeSS(acte);
                amende = 0.0;
            }

            System.out.println("retour Calcul Droit");


            redevance = montantActe * tauxRedevanceNatureActe;
            if (redevance < (NatureActe.getMinRedevance()==null? 0.0:NatureActe.getMinRedevance())) {
                redevance = (NatureActe.getMinRedevance()==null? 0.0:NatureActe.getMinRedevance());
            }

            System.out.println("retour Calcul Droit 1");

            //          droitEnregistrement = droitSimple + amende + penalite;
            //          netAPayer = droitEnregistrement + redevance + (valeurTimbre * nombreTimbre);

            if (gratisNatureActe == true) {
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

            System.out.println("retour Calcul Droit 2");
//            valeurTimbre =  acte.getValeurTimbre();
//            nombreTimbre =  acte.getNombreTimbre();
            Double sommeTimbre = valeurTimbre*nombreTimbre;
            Double montantPaye = acte.getMontantPaye() == null? 0.0:acte.getMontantPaye();
            sortiePaiement = new SortiePaiement(
                    penalite, amende, redevance, droitSimple, manqueGain, valeurTimbre, nombreTimbre, montantPaye
            );
        }else{
            System.out.println("La Nature choisie est incorrecte");
        }
        return sortiePaiement;
    }



}