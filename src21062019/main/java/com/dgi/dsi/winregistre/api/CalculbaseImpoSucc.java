package com.dgi.dsi.winregistre.api;


import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;
import com.dgi.dsi.winregistre.entites.Acte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


@RestController
@CrossOrigin("*")
public class CalculbaseImpoSucc {

  @Autowired
  private NatureActeDao natureActeDao;

   @Autowired
  private ActeDao acteDao;

    private Double montantBaseimpofem = 0.;
    private Double montantBaseimpoenf  = 0.;
    private Double montantResteabfem =0.;
//    private Integer NbreEnfantSucc;
//    private Double montantActe;
    private Double montantAbfem =6000000.;
    private Double montantAbenf  =4000000.;
    private Integer nbrEnfmax =6;

   public Double baseImpotSucc(Double montantActe, Integer NbreEnfantSucc ){


        Double partFem = montantActe*1/4;
        Double partEnf = montantActe*3/4;


       if(partFem<=montantAbfem) {
           montantBaseimpofem = 0.;
           montantResteabfem=partFem - montantAbfem;
       }else{
           montantBaseimpofem = partFem - montantAbfem;
       }


       if(nbrEnfmax < NbreEnfantSucc){
           montantAbenf = montantAbenf * nbrEnfmax/NbreEnfantSucc;
       }

               montantBaseimpoenf = partEnf -(montantAbenf * NbreEnfantSucc + montantResteabfem ); // partenf-( moAbenf*xNbre + moResteabfem)



       return montantBaseimpoenf+montantResteabfem;
   }






    }






