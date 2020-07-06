package com.dgi.dsi.winregistre.service;//package com.dgi.dsi.winregistre.service.SauvService;
//
import com.dgi.dsi.winregistre.dao.*;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.exception.CustomException;
import com.dgi.dsi.winregistre.sec.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class AjoutQuittanceService {



  @Autowired
  private AuthenticationManager authenticationManager;


  @Autowired
  private AccountService accountService;

  @Autowired
  private QuittanceDao quittanceDao;
  @Autowired
  private DetailQuittanceDao detailQuittanceDao;
  @Autowired
  private BordereauCaisseDao bordereauCaisseDao;
  @Autowired
  private ActeDao acteDao;



  public Quittance ajoutQuittance(Quittance userForm) {

        System.out.println("Début service ----------");

		 DetailQuittance detailQuittance = new DetailQuittance();
        System.out.println("Début service ----------"+userForm);

		  Double droitSimple = userForm.getActeQuittance().getDroitSimple()==null?0.:userForm.getActeQuittance().getDroitSimple();
          Double penalite = userForm.getActeQuittance().getPenalite()==null?0.:userForm.getActeQuittance().getPenalite();
		  Double amende = userForm.getActeQuittance().getAmende()==null?0.:userForm.getActeQuittance().getAmende();
          Double redevance = userForm.getActeQuittance().getRedevance()==null?0.:userForm.getActeQuittance().getRedevance();
          Double montantPaye = userForm.getMontantPaye()==null?0.:userForm.getMontantPaye();

      Quittance quittance = quittanceDao.saveAndFlush(userForm);

      System.out.println("userForm.getId()"+userForm.getId());

      if (droitSimple !=0.){
          System.out.println("droitSimple----------"+droitSimple);
		    detailQuittance.setMontantDQ(droitSimple);
		    detailQuittance.setQuittance(quittance);
		    detailQuittance.setNatureImpot("Droit Simple");
		    detailQuittance.setEncodeur(quittance.getEncodeur());
            detailQuittanceDao.saveAndFlush(detailQuittance);

          }
		  if (penalite !=0.){

          System.out.println("penalite-----------"+penalite);
		     detailQuittance.setMontantDQ(penalite);
		    detailQuittance.setQuittance(quittance);
		    detailQuittance.setNatureImpot("Pénalité");
		    detailQuittance.setEncodeur(quittance.getEncodeur());
            detailQuittanceDao.saveAndFlush(detailQuittance);
          }
		  if (amende !=0.){
              System.out.println("amende-------------"+amende);
		     detailQuittance.setMontantDQ(amende);
		    detailQuittance.setQuittance(quittance);
		    detailQuittance.setNatureImpot("Amende");
		    detailQuittance.setEncodeur(quittance.getEncodeur());
            detailQuittanceDao.saveAndFlush(detailQuittance);
          }
		  if (redevance !=0.){

              System.out.println("redevance----------------"+redevance);
		      detailQuittance.setMontantDQ(redevance);
		    detailQuittance.setQuittance(quittance);
		    detailQuittance.setNatureImpot("Redevance");
		    detailQuittance.setEncodeur(quittance.getEncodeur());
            detailQuittanceDao.saveAndFlush(detailQuittance);
          }


		  BordereauCaisse bordereauCaisse = userForm.getBordereauCaisse();
		  Acte acte = userForm.getActeQuittance();

		  Double totalAmendeAncien = bordereauCaisse.getTotalAmende()==null?0.:bordereauCaisse.getTotalAmende();
		  Double totalAutrePaiementAncien = bordereauCaisse.getTotalAutrePaiement()==null?0.:bordereauCaisse.getTotalAutrePaiement();
		  Double totalPenaliteAncien = bordereauCaisse.getTotalPenalite()==null?0.:bordereauCaisse.getTotalPenalite();
		  Double totalDroitSimpleAncien = bordereauCaisse.getTotalDroitSimple()==null?0.:bordereauCaisse.getTotalDroitSimple();
		  Double totalRedevanceAncien = bordereauCaisse.getTotalRedevance()==null?0.:bordereauCaisse.getTotalRedevance();
		  Double autrePaiementAncien = userForm.getMontantAutrePaiement()==null?0.:userForm.getMontantAutrePaiement();
		  Integer nombreQuittanceAncien = bordereauCaisse.getNbreQuittance()==null?0:bordereauCaisse.getNbreQuittance();

            bordereauCaisse.setTotalAmende(totalAmendeAncien+amende);
            bordereauCaisse.setTotalDroitSimple(totalDroitSimpleAncien+droitSimple);
            bordereauCaisse.setTotalPenalite(totalPenaliteAncien+penalite);
            bordereauCaisse.setTotalRedevance(totalRedevanceAncien+redevance);
            bordereauCaisse.setNbreQuittance(nombreQuittanceAncien+1);
            bordereauCaisse.setTotalAutrePaiement(totalAutrePaiementAncien+autrePaiementAncien);
		  bordereauCaisseDao.saveAndFlush(bordereauCaisse);

		  Double mtPayeActeAncien = acte.getMontantPaye()==null?0.:acte.getMontantPaye();
		  Double mtDuActeAncien = acte.getMontantDu()==null?0.:acte.getMontantDu();

          acte.setMontantPaye(mtPayeActeAncien+montantPaye);
          acte.setMontantDu(mtDuActeAncien-montantPaye);
            System.out.println("service ----------"+acte.getMontantPaye()+ "  "+acte.getMontantDu());
          acte.setStatutCourant("QT");
		  acteDao.saveAndFlush(acte);

//
//      Quittance quittanceeee = userForm;
      Acte acteeee = userForm.getActeQuittance();
      ((Acte) acteeee).getListQuittancesActe().add(userForm);


		return userForm;

	}

}
