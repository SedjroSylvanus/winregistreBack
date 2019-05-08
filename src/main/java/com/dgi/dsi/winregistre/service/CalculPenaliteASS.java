package com.dgi.dsi.winregistre.service;


import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;

import com.dgi.dsi.winregistre.payload.EntrePaiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgi.dsi.winregistre.entites.Acte;
import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Service
@Transactional
public class CalculPenaliteASS {

  @Autowired
  private NatureActeDao natureActeDao;

   @Autowired
  private ActeDao acteDao;




    public static Double CalculPenaliteActeSS(EntrePaiement acte){


        String communeActe;
        Long dureeActe;
        Double majoration = 0.0;
        Double droitSimple = 0.0;
        Double penalite = 0.0;

//        LocalDate dateFormatter = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate d1 =  LocalDate.parse(acte.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate d2 =  LocalDate.parse(acte.getDateEnregistrement().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));


        dureeActe = ChronoUnit.DAYS.between(acte.getDateActe(), acte.getDateEnregistrement());
        System.out.println("--->Durée entre date acte et date enrégistrement"+dureeActe);
        JourFerieService jourFerie = new JourFerieService();

        if(acte.getCommuneActe().getCentreEnregistrement() == true)
        {
            if(acte.getTypePenaliteAmende().getJourMoisAnnee() == "J"){
                acte.getDateActe().plus(acte.getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.DAYS);
            }

            if(acte.getTypePenaliteAmende().getJourMoisAnnee() == "M"){
                acte.getDateActe().plus(acte.getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.MONTHS);
            }
            if(acte.getTypePenaliteAmende().getJourMoisAnnee() == "A"){
                acte.getDateActe().plus(acte.getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.YEARS);
            }
        }else{
            if(acte.getTypePenaliteAmende().getJourMoisAnnee() == "J"){
                acte.getDateActe().plus(2*(acte.getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.DAYS);
            }
            if(acte.getTypePenaliteAmende().getJourMoisAnnee() == "M"){
                acte.getDateActe().plus(2*(acte.getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.MONTHS);
            }
            if(acte.getTypePenaliteAmende().getJourMoisAnnee() == "A"){
                acte.getDateActe().plus(2*(acte.getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.YEARS);
            }

        }
        System.out.println("--->Date de l'acte"+ acte.getDateActe());
        //Jour Férié
        if(jourFerie.isFerie(acte.getDateActe().toString()) == true)
        {
            acte.setDateActe(acte.getDateActe().plus(1, ChronoUnit.DAYS));

            if(jourFerie.isSamedi(acte.getDateActe().toString()) == true){
                acte.setDateActe(acte.getDateActe().plus(2, ChronoUnit.DAYS));
            }
            if(jourFerie.isDimanche(acte.getDateActe().toString()) == true){
                acte.setDateActe(acte.getDateActe().plus(1, ChronoUnit.DAYS));
            }
        }

        System.out.println("---> date de l'acte apres férié"+acte.getDateActe());

        if(ChronoUnit.DAYS.between(acte.getDateActe(), acte.getDateEnregistrement())>=0)
        {
            penalite = 0.0;

        }else{

            switch (acte.getNatureActe().getCode())

            {

                case "404_409":

                {

                    penalite = droitSimple * 1; // taux a rechercher * droit simple
                    break;
                }
                case "409BIS_1":
                {
                    LocalDate dateMajoration = acte.getDateActe().plus(3, ChronoUnit.MONTHS);
                    while(ChronoUnit.DAYS.between(dateMajoration,acte.getDateEnregistrement())>0){

                        majoration += acte.getTypePenaliteAmende().getMtPenaliteAmende();
                        dateMajoration = dateMajoration.plus(1, ChronoUnit.MONTHS);
                    }

                    penalite = acte.getTypePenaliteAmende().getMtPenaliteEcheance() + majoration;
                    break;
                }
                case "409BIS_2":

                {
                    penalite = acte.getTypePenaliteAmende().getMtPenaliteEcheance();
                    break;

                }
                case "FIXE":

                {

                    penalite = acte.getNatureActe().getMtFixePenalite();
                    break;

                }
                case "TAUXFIXE":

                {

                    penalite = acte.getNatureActe().getTauxFixePenalite()*droitSimple; //Droit simple

                    break;

                }
                case "SUC_MUT":

                {


                    break;

                }


                default:

                    return penalite;
//                    System.out.println("Calcul non pris en compte");

            }
            }
            if(penalite<acte.getTypePenaliteAmende().getMtMaximum()){

            }
            if(acte.getTypePenaliteAmende().getMtMaximum()==0){}else{
                if(penalite>acte.getTypePenaliteAmende().getMtMaximum()) {

                    penalite = acte.getTypePenaliteAmende().getMtMaximum();
                }
            }
            return penalite;
        }

    }






