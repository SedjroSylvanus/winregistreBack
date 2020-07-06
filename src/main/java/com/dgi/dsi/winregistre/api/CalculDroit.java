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
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


//@RestController
//@CrossOrigin("*")
@Service

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
    private Double droitSimpleAf = 0.0;
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

         System.out.println("----------------Début calculPaiement-----------------"+acte);
        CalculbaseImpoSucc calculbaseImpoSucc = new CalculbaseImpoSucc();
        SortiePaiement sortiePaiement = new SortiePaiement();



        if (acte.getNatureActe() != null) {
           
        System.out.println("Dedans<<<<<<<<<<<<------>>>>>>>>>>>" + acte.getNatureActe());
        NatureActe natureActe = acte.getNatureActe(); //natureActeDao.findByCodeEquals(acte.getNatureActe().getCode());

            valeurTimbre = acte.getValeurTimbre()==null?0.0:acte.getValeurTimbre();
            nombreTimbre = acte.getNombreTimbre()==null?0.0:acte.getNombreTimbre();

            //éviter les valeur par dateActeéfaut null des types numériques
            Double montantActe = acte.getMontantActe()==null?0.0:acte.getMontantActe();
            Integer nbreEnfantActe = acte.getNombreEnfantSuccession()==null?0 :acte.getNombreEnfantSuccession();

            Double tauxNatureActe =  natureActe.getTaux()==null?0.0:natureActe.getTaux();
            Double tauxInflation =  natureActe.getTauxInflation()==null?0.0:natureActe.getTauxInflation();
            Double tauxNotaire =  natureActe.getTauxFormaliteNotaire()==null?0.0:natureActe.getTauxFormaliteNotaire();
System.out.println("--------------tauxInflation---------> "+ tauxInflation);
            Double montantFixeNatureActe =  natureActe.getMtFixe()==null?0.0:natureActe.getMtFixe();
            Double montantMinNatureActe = natureActe.getMtMinimum() == null?0.0:natureActe.getMtMinimum();
            Boolean gratisNatureActe = natureActe.getGratis() == null?Boolean.FALSE:natureActe.getGratis();
            Double tauxRedevanceNatureActe = natureActe.getTauxRedevance()==null?0.0:natureActe.getTauxRedevance();


            System.out.println("Fin Initialisation dans calculPaiement---------------");

            switch (natureActe.getTarif()==null?null:natureActe.getTarif().getCode() ) {

                case "FIXE": {
                    droitSimple = montantFixeNatureActe;
                    System.out.println("droit simple Fx "+droitSimple);
//                              if(droitSimple<montantMinNatureActe){
//                                  droitSimple = montantMinNatureActe;
//                              }
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
                case "TAUX PROGESSIF": {


                    Double cumulDroitSimple =0.;
                    Double montantBaseImp = montantActe;
                    //Nombre d'enfant à prendre dans le front end
                    Integer nombreEnfant = nbreEnfantActe;
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

                    System.out.println("Calcul Droit non pris en compte Dans calcul Droit");
                    return sortiePaiement;
                }


            }


            if (droitSimple == 0 || gratisNatureActe == true) {
                penalite = 0.0;
                amende = calculPenaliteASS.CalculPenaliteActeSS(acte);
                System.out.println("------Amende--------"+amende);
            } else {
                penalite = calculPenaliteASS.CalculPenaliteActeSS(acte);
                amende = 0.0;
                System.out.println("------Pénalité--------"+penalite);
            }


            System.out.println("----tauxRedevanceNatureActe----------"+tauxRedevanceNatureActe);
            redevance = montantActe * tauxRedevanceNatureActe/100;
            if (redevance < (natureActe.getMinRedevance()==null? 0.0:natureActe.getMinRedevance())) {
                redevance = (natureActe.getMinRedevance()==null? 0.0:natureActe.getMinRedevance());
            }

            //          droitEnregistrement = droitSimple + amende + penalite;
            //          netAPayer = droitEnregistrement + redevance + (valeurTimbre * nombreTimbre);

            System.out.println(gratisNatureActe+" ---gratisNatureActe-------"+droitSimple+"------droitSimple----");
            droitSimpleAf = droitSimple;
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




                if (natureActe.getAncienPA() == null) {
                    manqueGain = calculPenaliteASS.CalculPenaliteActeSS(acte);//méthode de calcul de pénalité
                }

            }

            System.out.println("retour Calcul Droit 2 Ds ---------"+droitSimple);
//            valeurTimbre =  acte.getValeurTimbre();
//            nombreTimbre =  acte.getNombreTimbre();
            Double sommeTimbre = valeurTimbre*nombreTimbre;
            
             LocalDate dateAcquisitionActuelle = acte.getDateAcquisitionActuelle()==null? LocalDate.now(): acte.getDateAcquisitionActuelle();
             LocalDate ancienneDateAcquisition = acte.getAncienneDateAcquisition()==null? LocalDate.now(): acte.getAncienneDateAcquisition(); 
             Double montantAcquisitionActuelle = acte.getMontantAcquisitionActuelle()==null? 0.: acte.getMontantAcquisitionActuelle();
             Double ancienneMontantAcquisition = acte.getAncienMontantAcquisition()==null? 0.: acte.getAncienMontantAcquisition(); 
             
             
             System.out.println("retour Calcul Droit  Ds ---------333"+droitSimple);

             Period nombreAnnee = Period.between(ancienneDateAcquisition, dateAcquisitionActuelle);
             System.out.println("retour Calcul Droit  Ds ---------333OOO"+droitSimple);

             int nbreAnnee = nombreAnnee.getYears();
             System.out.println("retour Calcul Droit  Ds ---------444"+droitSimple);

            
            Double plusValueImmobiliere = (montantAcquisitionActuelle - (Math.pow((1+ tauxInflation), 2)*(ancienneMontantAcquisition*(1+tauxNotaire))))*0.05;
            System.out.println("retour Calcul Droit  Ds ---------plusvalueImmobiliere "+plusValueImmobiliere);

            Double montantPaye = acte.getMontantPaye() == null? 0.0:acte.getMontantPaye();

            System.out.println(plusValueImmobiliere + " plusvalueImmobiliere "+penalite+"  penalite   "+amende+"  amende   "+ redevance+"  Red   "+ droitSimple+"  Ds   "+manqueGain+"  Mg   "+valeurTimbre+"  Vt   "+nombreTimbre+"  nt   "+ montantPaye+"  Mt   ");



            sortiePaiement = new SortiePaiement(plusValueImmobiliere,
                    penalite, amende, redevance, droitSimple, manqueGain, valeurTimbre, nombreTimbre, montantPaye
            );
        }else{
            System.out.println("La Nature choisie est incorrecte");
        }
        return sortiePaiement;
    }



}