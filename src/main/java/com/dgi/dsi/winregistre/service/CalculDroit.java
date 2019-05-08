package com.dgi.dsi.winregistre.service;

//
import com.dgi.dsi.winregistre.dao.AgentDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.exception.CustomException;
import com.dgi.dsi.winregistre.payload.EntrePaiement;
import com.dgi.dsi.winregistre.payload.SortiePaiement;
import com.dgi.dsi.winregistre.sec.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.jpa.internal.schemagen.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CalculDroit {

//  @Autowired
//  private NatureActeDao natureActeDao;

  private Double droitSimple = 0.0;
  private Double penalite = 0.0;
  private Double amende = 0.0;
  private Double redevance = 0.0;
  private Double droitEnregistrement = 0.0;
  private Double netAPayer = 0.0;
  private Double valeurTimbre;//A créer dans acte
  private Double nombreTimbre;//A créer dans acte
  private Double manqueGain;
//  private Acte acte;
//  private LocalDate dateEnregistrement;
//  private LocalDate dateActe;




  public SortiePaiement calculPaiement(EntrePaiement acte ) {

      SortiePaiement sortiePaiement = new SortiePaiement();

      System.out.println(acte.getNatureActe().getTarif().getCode());
      System.out.println(acte.getNatureActe());

      switch (acte.getNatureActe().getTarif().getCode())

      {

        case "FIXE":

        {
          droitSimple = acte.getNatureActe().getMtFixe();
//          if(droitSimple<acte.getNatureActe().getMtMinimum()){
//              droitSimple = acte.getNatureActe().getMtMinimum();
//          }
           break;
        }
       case "TAUX ET MIN":

        {
          droitSimple = acte.getMontantActe()*acte.getNatureActe().getTaux();
          if(droitSimple<acte.getNatureActe().getMtMinimum())
          {
            droitSimple = acte.getNatureActe().getMtMinimum();
          }
           break;
        }
        case "TAUX":

        {

          droitSimple = acte.getMontantActe()*acte.getNatureActe().getTaux();

           break;

        }
         case "TAUX PROGRESSIF":

        {

            System.out.println("TAUX PROGRESSIF");

           break;

        }


        default:
        {

            System.out.println("Calcul Droit non pris en compte");
            return sortiePaiement;
        }


      }

      if (droitSimple == 0 || acte.getNatureActe().getGratis() == true){
        penalite = 0.0;
        amende = CalculPenaliteASS.CalculPenaliteActeSS(acte);
      }else{
         penalite = CalculPenaliteASS.CalculPenaliteActeSS(acte);
        amende = 0.0;
      }



      redevance = acte.getMontantActe() * acte.getNatureActe().getTauxRedevance();
      if(redevance < acte.getNatureActe().getMinRedevance() ){
          redevance = acte.getNatureActe().getMinRedevance();
      }

      droitEnregistrement = droitSimple + amende + penalite;
      netAPayer = droitEnregistrement + redevance + (valeurTimbre*nombreTimbre);

      if(acte.getNatureActe().getGratis() == true){
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

          droitEnregistrement = droitSimple + amende + penalite;
          netAPayer = droitEnregistrement + redevance + (valeurTimbre*nombreTimbre);

          if(acte.getNatureActe().getAncienPA() == null){
              manqueGain =  CalculPenaliteASS.CalculPenaliteActeSS(acte);//méthode de calcul de pénalité
          }


          System.out.println();
      }
      sortiePaiement = new SortiePaiement(
              penalite,amende,redevance,droitSimple,manqueGain
      );
   return sortiePaiement;
  }



}
