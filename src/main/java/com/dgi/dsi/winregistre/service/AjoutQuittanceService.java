package com.dgi.dsi.winregistre.service;//package com.dgi.dsi.winregistre.service.SauvService;
//

import com.dgi.dsi.winregistre.api.ActeApi;
import com.dgi.dsi.winregistre.api.AuthAgentApi;
import com.dgi.dsi.winregistre.dao.*;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.exception.CustomException;
import com.dgi.dsi.winregistre.sec.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.envers.Audited;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    private BordereauActeDao bordereauActeDao;
    @Autowired
    private ActeDao acteDao;
    @Autowired
    private AvoirStatutDao avoirStatutDao;


    public Quittance ajoutQuittance(HttpServletRequest req, Quittance userForm) {

        System.out.println("Début service ----------");
        if(userForm.getBordereauCaisse()!=null) {

            System.out.println("userForm.getBordereauActe() Non null  et id" + userForm.getBordereauCaisse().getId());
            BordereauCaisse bordereauCaisseId = new BordereauCaisse();
            
//            if (userForm.getBordereauCaisse().getId() == 0L) {
//                System.out.println("--------Les numero de bordereau et de Id-------" + userForm.getBordereauCaisse().getNumero() + "----------" + userForm.getBordereauCaisse().getId() + "-------------------------");
//                System.out.println("-------------------------1111111111111111111111111-------------------------");
//                bordereauCaisseId = bordereauCaisseDao.saveAndFlush(userForm.getBordereauCaisse());
//                userForm.setBordereauCaisse(bordereauCaisseId);
////                System.out.println("---------bordereauCaisse.getId()--------->" + bordereauCaisseId.getId());
//            } else {
//                System.out.println("------------------------------222222222222222222-------------------------");
//                bordereauCaisseId = userForm.getBordereauCaisse();
////                userForm.setBordereauCaisse(bordereauCaisseId);
//                System.out.println("---------bordereauActe.getId()--------->" + bordereauCaisseId.getId());
//            }



//
//                System.out.println("-----------acteQuittance envoyé---------"+userForm.getActeQuittance());
//
//                System.out.println("-------------userForm.getBordereauCaisse()----------------------"+userForm.getBordereauCaisse());
//                System.out.println("-------------bordereauCaisseId----------------------"+bordereauCaisseId);

//                List<Quittance> quittancesAjout = new ArrayList<>();
//                Acte acteQuittanceAjoute = ajoutActe(userForm.getActeQuittance(), quittancesAjout );
            
            
            
//            A revoir pourquoi c'est utile 
//                Acte acteQuittanceAjoute = ajoutActe( userForm.getActeQuittance(), null) ;

            Double droitSimple = 0.;
            Double penalite = 0.;
            Double amende = 0.;
            Double redevance = 0.;
            Double montantPaye = 0.;
            Double plusvalueImmobiliere = 0.;

            if(userForm.getActeQuittance() != null) {
            	
            	 droitSimple = userForm.getActeQuittance().getDroitSimple() == null ? 0. : userForm.getActeQuittance().getDroitSimple();
                 penalite = userForm.getActeQuittance().getPenalite() == null ? 0. : userForm.getActeQuittance().getPenalite();
                 amende = userForm.getActeQuittance().getAmende() == null ? 0. : userForm.getActeQuittance().getAmende();
                 redevance = userForm.getActeQuittance().getRedevance() == null ? 0. : userForm.getActeQuittance().getRedevance();
                 montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();

                 plusvalueImmobiliere = userForm.getActeQuittance().getPlusValueImmobiliere() == null ? 0. : userForm.getActeQuittance().getPlusValueImmobiliere();

            	
            }
            
            
            BordereauCaisse bordereauCaisseRecherche =  bordereauCaisseDao.findByNumeroEquals(userForm.getBordereauCaisse().getNumero());
            System.out.println("---------bordereauCaisseRecherche-------"+bordereauCaisseRecherche);
            if ( bordereauCaisseRecherche == null ) {
                bordereauCaisseId = bordereauCaisseDao.saveAndFlush(userForm.getBordereauCaisse());

                userForm.setBordereauCaisse(bordereauCaisseId);
            }
            Quittance quittance = new Quittance();
            try{

            quittance = quittanceDao.saveAndFlush(userForm);


            if (droitSimple != 0.) {
                DetailQuittance detailQuittance = new DetailQuittance();
                System.out.println("droitSimple----------" + droitSimple+"---------"+userForm);
                detailQuittance.setMontantDQ(droitSimple);
                detailQuittance.setQuittance(quittance);
                detailQuittance.setNatureImpot("Droit Simple");
                detailQuittance.setEncodeur(userForm.getEncodeur());
                detailQuittanceDao.saveAndFlush(detailQuittance);

            }
            if (penalite != 0.) {

                DetailQuittance detailQuittance = new DetailQuittance();
                System.out.println("penalite-----------" + penalite);
                detailQuittance.setMontantDQ(penalite);
                detailQuittance.setQuittance(quittance);
                detailQuittance.setNatureImpot("Pénalité");
                detailQuittance.setEncodeur(userForm.getEncodeur());
                detailQuittanceDao.saveAndFlush(detailQuittance);
            }
            if (amende != 0.) {
                DetailQuittance detailQuittance = new DetailQuittance();
                System.out.println("amende-------------" + amende);
                detailQuittance.setMontantDQ(amende);
                detailQuittance.setQuittance(quittance);
                detailQuittance.setNatureImpot("Amende");
                detailQuittance.setEncodeur(userForm.getEncodeur());
                detailQuittanceDao.saveAndFlush(detailQuittance);
            }
            if (redevance != 0.) {

                DetailQuittance detailQuittance = new DetailQuittance();
                System.out.println("redevance----------------" + redevance);
                detailQuittance.setMontantDQ(redevance);
                detailQuittance.setQuittance(quittance);
                detailQuittance.setNatureImpot("Redevance");
                detailQuittance.setEncodeur(userForm.getEncodeur());
                detailQuittanceDao.saveAndFlush(detailQuittance);
            }

            if (plusvalueImmobiliere != 0.) {

                DetailQuittance detailQuittance = new DetailQuittance();
                System.out.println("Plus Value----------------" + redevance);
                detailQuittance.setMontantDQ(plusvalueImmobiliere);
                detailQuittance.setQuittance(quittance);
                detailQuittance.setNatureImpot("Plus Value Immobilière");
                detailQuittance.setEncodeur(userForm.getEncodeur());
                detailQuittanceDao.saveAndFlush(detailQuittance);
            }

            }catch (Exception e){
                System.out.println("-------Erreur à l'ajout de Quittance et Détail Quittance"+e.getMessage());
            }

            BordereauCaisse bordereauCaisse = quittance.getBordereauCaisse();

            Double totalAmendeAncien =  0.;
            Double totalAutrePaiementAncien =  0.;
            Double totalPenaliteAncien =  0. ;
            Double totalDroitSimpleAncien =  0. ;
            Double totalRedevanceAncien =  0.;
            Double autrePaiementAncien =  0. ;
            Integer nombreQuittanceAncien =  0 ;
            Double totalPlusvalueImmobiliereAncien = 0.;

            
            if(bordereauCaisse == null) {
                System.out.println("----- La Quittance est sans bordereau caisse----"+bordereauCaisse);


            }else {
                System.out.println("----- La Quittance est avec bordereau caisse----"+bordereauCaisse);

                 totalAmendeAncien = bordereauCaisse.getTotalAmende() == null ? 0. : bordereauCaisse.getTotalAmende();
                 totalAutrePaiementAncien = bordereauCaisse.getTotalAutrePaiement() == null ? 0. : bordereauCaisse.getTotalAutrePaiement();
                 totalPenaliteAncien = bordereauCaisse.getTotalPenalite() == null ? 0. : bordereauCaisse.getTotalPenalite();
                 totalDroitSimpleAncien = bordereauCaisse.getTotalDroitSimple() == null ? 0. : bordereauCaisse.getTotalDroitSimple();
                 totalRedevanceAncien = bordereauCaisse.getTotalRedevance() == null ? 0. : bordereauCaisse.getTotalRedevance();
                 autrePaiementAncien = userForm.getMontantAutrePaiement() == null ? 0. : userForm.getMontantAutrePaiement();
                 nombreQuittanceAncien = bordereauCaisse.getNbreQuittance() == null ? 0 : bordereauCaisse.getNbreQuittance();
                 totalPlusvalueImmobiliereAncien = bordereauCaisse.getTotalPlusValueImobiliere() == null ? 0 : bordereauCaisse.getTotalPlusValueImobiliere();

            }
            
           
            System.out.println("-----------BordereauCaisse bordereauCaisse = userForm.getBordereauCaisse();--------"+ bordereauCaisse);

            Acte acte = userForm.getActeQuittance();

            System.out.println("----- Deb bordereauCaisse----"+bordereauCaisse);
//            System.out.println(userForm.getActeQuittance().getNumero() +"-userForm.getActeQuittance().getNumero()---"+acteQuittanceAjoute+"---acteQuittanceAjoute----");
            Acte acteRecherche = acteDao.findByNumeroActe(acte.getNumero()); //acteQuittanceAjoute
//          System.out.println("-----------------azertyuiop--------------->"+acteRecherche.getId());

          
            bordereauCaisse.setTotalAmende(totalAmendeAncien + amende);
            bordereauCaisse.setTotalDroitSimple(totalDroitSimpleAncien + droitSimple);
            bordereauCaisse.setTotalPenalite(totalPenaliteAncien + penalite);
            bordereauCaisse.setTotalRedevance(totalRedevanceAncien + redevance);
            bordereauCaisse.setNbreQuittance(nombreQuittanceAncien + 1);
            System.out.println("------------nombreQuittanceAncien"+nombreQuittanceAncien +"------nombreQuittanceNouveau"+bordereauCaisse.getNbreQuittance());
            bordereauCaisse.setTotalAutrePaiement(totalAutrePaiementAncien + autrePaiementAncien);
            bordereauCaisse.setTotalPlusValueImobiliere(totalPlusvalueImmobiliereAncien + plusvalueImmobiliere);

            
//            bordereauCaisse.setId(bordereauCaisseId.getId());
            
            try{
                
            	bordereauCaisseDao.saveAndFlush(bordereauCaisse);

            }catch (Exception e){
                System.out.println("-------Erreur à l'ajout de Bordereau Caisse"+e.getMessage());
            }

            userForm.setActeQuittance(acteRecherche);
            bordereauCaisse.getQuittances().add(quittance);


            Double mtPayeActeAncien = acte.getMontantPaye() == null ? 0. : acte.getMontantPaye();
            Double mtDuActeAncien = acte.getMontantDu() == null ? 0. : acte.getMontantDu();

            if(userForm.getActeQuittance().getNumero().contains("APA") || userForm.getActeQuittance().getNumero().contains("AUTNP")){
                System.out.println("-----------APPPPPPPPPPPPPPPAAAAAAAAAAAAAAa---------");
            }else{
                acte.setMontantPaye(mtPayeActeAncien + montantPaye);
                acte.setMontantDu(mtDuActeAncien - montantPaye);
            }


            System.out.println("service ----------" + acte.getMontantPaye() + "  " + acte.getMontantDu());
            acte.setStatutCourant("QT");
            acte.setAgentCaisse(authAgentApi.whoami(req));


//        ((Acte) userForm.getActeQuittance()).getQuittances().add(userForm);
            Acte acteEnregistre = acteDao.saveAndFlush(acte);


            quittance.setActeQuittance(acteEnregistre);
            System.out.println(quittance.getId() + "----------------------id quittance-------------------------");
            quittanceDao.saveAndFlush(quittance);
            System.out.println(acteEnregistre.getId() + "----------------------id acte-------------------------");

            acte.getQuittances().add(quittance);


            AvoirStatut avoirStatut = new AvoirStatut();
            avoirStatut.setAgent(acte.getAgentLiquidateur());
            avoirStatut.setNumero(acte.getNumero());
            avoirStatut.setStatutString("QT");
            avoirStatut.setDesignation(acte.getDesignation());
            avoirStatut.setCommentaire("Acte Quittancer");

            avoirStatut.setDateModification(new Date());
            avoirStatut.setDateDebut(new Date());
            avoirStatut.setDateFin(new Date());
            try{
                avoirStatutDao.saveAndFlush(avoirStatut);
            }catch (Exception e){
                System.out.println("-------Erreur à l'ajout de AvoirStatut"+e.getMessage());
            }


            return quittance;
        }else{
            System.out.println("77777777777777777777777777777777777777777777777777777777");
            return null;
        }

    }


    
    
    
    
    
    
    

	@Autowired
	private AuthAgentApi authAgentApi;
	
	 public Acte ajoutActeOfficier( Acte userForm, List<Quittance> quittances) {

			


	        System.out.println("Début service ajoutQuittance----------");
	        System.out.println("Objet Acte ----------"+ userForm.getId());

	        Acte acteBordereauStandard = new Acte();


	        if(quittances!=null){

	            quittances.forEach(quittance->{
	                userForm.getQuittances().add(quittance);
	                quittance.setActeQuittance(userForm);
//	                quittance.setCaissier(authAgentApi.whoami(req));

	                quittanceDao.saveAndFlush(quittance);

	            });
	        }
	        BordereauActe bordereauActeRecherche  = userForm.getBordereauActe() == null ? null : userForm.getBordereauActe();
	        if(bordereauActeRecherche!=null){

	            System.out.println("userForm.getBordereauActe() Non null  et id"+userForm.getBordereauActe().getId());
	        BordereauActe bordereauActeId;
	        if(userForm.getBordereauActe().getId()==0L){
	            System.out.println("--------Les numero de bordereau et de Id-------"+userForm.getBordereauActe().getNumero()+"----------"+userForm.getBordereauActe().getId()+"-------------------------");
	            System.out.println("-------------------------1111111111111111111111111-------------------------");
	            bordereauActeId = bordereauActeDao.saveAndFlush(userForm.getBordereauActe());
	            System.out.println("---------bordereauActe.getId()--------->"+bordereauActeId.getId());
	        }else{
	            System.out.println("------------------------------222222222222222222-------------------------");
	            bordereauActeId = userForm.getBordereauActe();
	            System.out.println("---------bordereauActe.getId()--------->"+bordereauActeId.getId());
	        }




	        Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
	        Double plusValueImmobiliere = userForm.getPlusValueImmobiliere() == null ? 0. : userForm.getPlusValueImmobiliere();

	        
	        Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
	        Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
	        Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
	        Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
	        Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
	        Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
	        Double montantTimbre = nombreTimbre * ValeurTimbre;


	        BordereauActe bordereauActe = userForm.getBordereauActe();


	        Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();
	        Double totalPlusValueImmobiliereAncien = bordereauActe.getTotalPlusValueImobiliere() == null ? 0. : bordereauActe.getTotalPlusValueImobiliere();


	        Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();

	        Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();

	        Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();

	        Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();

	        Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();


	        bordereauActe.setTotalAmende(totalAmendeAncien + amende);
	        bordereauActe.setTotalPlusValueImobiliere(totalPlusValueImmobiliereAncien + plusValueImmobiliere);

	        
	        bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple);
	        bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite);
	        bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance);
	        bordereauActe.setNbreActe(nombreActeAncien + 1);
	        bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre);
	        bordereauActe.setValidated(false);
	        bordereauActe.setSourceInterne(false);

	        bordereauActe.setId(bordereauActeId.getId());


	        BordereauActe borActe = bordereauActeDao.saveAndFlush(bordereauActe);

	        userForm.setBordereauActeContrib(borActe);
//	        userForm.setBordereauActe(bordereauActe);
	            userForm.setStatutCourant("PRE");
//	            System.out.println("-------------------->"+userForm.getNumero());
	            Acte acte = new Acte();
	            Acte acteREcherche = acteDao.findActeByNumeroEquals(userForm.getNumero());
	            if(acteREcherche!=null) {
	                if (acteREcherche.getId() == 0. || acteREcherche.getId() == null) {
	                    acte = acteDao.saveAndFlush(userForm);
	                } else {
	                    userForm.setId(acteREcherche.getId());
	                    acte = acteDao.saveAndFlush(userForm);
	                }
	            }else{
	                  acte = acteDao.saveAndFlush(userForm);
	            }

//	           Acte  acte = acteDao.saveAndFlush(userForm);
//	            System.out.println("userForm.getId()" + userForm.getId());


	        bordereauActe.getActesBordereau().add(acte);

	        AvoirStatut avoirStatut = new AvoirStatut();
			  avoirStatut.setAgent(acte.getAgentLiquidateur());
			  avoirStatut.setNumero(acte.getNumero());
			  avoirStatut.setStatutString(acte.getStatutCourant());
			  avoirStatut.setDesignation(acte.getDesignation());
			  avoirStatut.setStatutString("PRE");
			  avoirStatut.setCommentaire("Acte Préliquidé");

			  avoirStatut.setDateModification(new Date());
			  avoirStatut.setDateDebut(new Date());
			  avoirStatut.setDateFin(new Date());
	        avoirStatutDao.saveAndFlush(avoirStatut);


	        }
	        
	        return userForm;

	        
	        
	        
	        
	        
	        
	        
//	        else{
//	            System.out.println("----------------Avant ---------------------- "+userForm);
//
//
//
//	            BordereauActe borActe = new BordereauActe();
//	            BordereauActe borActeAjouter = new BordereauActe();
//	            BordereauActe borActeRecherche  = bordereauActeDao.findByNumeroEquals("ASSAUTDGICONT");
//
//	            if(borActeRecherche == null) {
//	                System.out.println("----------------Milieu ---------------------- ");
//	                borActe.setNumero("ASSAUTDGICONT");
//	                borActeRecherche = bordereauActeDao.saveAndFlush(borActe);
//	                System.out.println(borActeAjouter+"--------borActeAjouter---------");
//
//	            System.out.println("Deb----------Ajout RRRRRRRRRRRRRR------------");
//
//	            }
//
//	            userForm.setBordereauActeContrib(borActeRecherche);
////	            userForm.setAgentLiquidateur(authAgentApi.whoami(req));
//
//	            userForm.setBordereauActe(borActeRecherche);
//
//
//	            Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
//	            Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
//	            Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
//	            Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
//	            Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
//	            Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
//	            Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
//	            Double montantTimbre = nombreTimbre * ValeurTimbre;
//
//
//	            BordereauActe bordereauActe = userForm.getBordereauActe();
//
//
//	            Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();
//
//	            Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();
//
//	            Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();
//
//	            Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();
//
//	            Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();
//
//	            Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();
//
//
//	            bordereauActe.setTotalAmende(totalAmendeAncien + amende);
//	            bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple);
//	            bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite);
//	            bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance);
//	            bordereauActe.setNbreActe(nombreActeAncien + 1);
//	            bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre);
//	            bordereauActe.setId(borActeRecherche.getId());
//
//	            System.out.println(borActeRecherche.getId()+"--------borActeRecherche.getId()---------");
//
//	            acteBordereauStandard = acteDao.saveAndFlush(userForm);
//
//	            System.out.println("Fin----------Ajout RRRRRRRRRRRRRR------------");
//	            borActeRecherche.getActesBordereau().add(acteBordereauStandard);
//
//	            AvoirStatut avoirStatut = new AvoirStatut();
//	            avoirStatut.setAgent(acteBordereauStandard.getAgentLiquidateur());
//	            avoirStatut.setNumero(acteBordereauStandard.getNumero());
//
//	            avoirStatut.setStatutString("SS");
//	            avoirStatut.setCommentaire("Acte Saisie");
//	            avoirStatut.setDesignation(acteBordereauStandard.getDesignation());
//	            avoirStatut.setDateModification(new Date());
//	            avoirStatut.setDateDebut(new Date());
//	            avoirStatut.setDateFin(new Date());
//	            avoirStatutDao.saveAndFlush(avoirStatut);
//
//
//	            System.out.println("----------------Non Ajout--Le numéro de ");
//
//
//
//
//	            return acteBordereauStandard;
//	        }

	    }

	 @Autowired
	 private  HttpServletRequest request;
	 
	  public Acte ajoutActeContribuable( Acte userForm) {

			


	        System.out.println("Début service ajoutQuittance----------");
	        System.out.println("Objet Acte ----------"+ userForm.getId());

	        Acte acteBordereauStandard = new Acte();


//	        if(quittances!=null){
//
//	            quittances.forEach(quittance->{
//	                userForm.getQuittances().add(quittance);
//	                quittance.setActeQuittance(userForm);
//	                quittance.setCaissier(authAgentApi.whoami(request));
//
//	                quittanceDao.saveAndFlush(quittance);
//
//	            });
//	        }
//	        
	        
//	        BordereauActe bordereauActeRecherche  = userForm.getBordereauActe() == null ? null : userForm.getBordereauActe();
//	        if(bordereauActeRecherche!=null){
//
//	        
//	        System.out.println("userForm.getBordereauActe() Non null  et id"+userForm.getBordereauActe().getId());
//	        BordereauActe bordereauActeId;
//	        if(userForm.getBordereauActe().getId()==0L){
//	            System.out.println("--------Les numero de bordereau et de Id-------"+userForm.getBordereauActe().getNumero()+"----------"+userForm.getBordereauActe().getId()+"-------------------------");
//	            System.out.println("-------------------------1111111111111111111111111-------------------------");
//	            bordereauActeId = bordereauActeDao.saveAndFlush(userForm.getBordereauActe());
//	            System.out.println("---------bordereauActe.getId()--------->"+bordereauActeId.getId());
//	        }else{
//	            System.out.println("------------------------------222222222222222222-------------------------");
//	            bordereauActeId = userForm.getBordereauActe();
//	            System.out.println("---------bordereauActe.getId()--------->"+bordereauActeId.getId());
//	        }
//
//	        Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
//	        Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
//	        Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
//	        Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
//	        Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
//	        Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
//	        Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
//	        Double montantTimbre = nombreTimbre * ValeurTimbre;
//
//	        BordereauActe bordereauActe = userForm.getBordereauActe();
//	        Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();
//	        Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();
//	        Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();
//	        Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();
//	        Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();
//	        Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();
//
//
//	        bordereauActe.setTotalAmende(totalAmendeAncien + amende);
//	        bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple);
//	        bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite);
//	        bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance);
//	        bordereauActe.setNbreActe(nombreActeAncien + 1);
//	        bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre);
//	        bordereauActe.setValidated(true);
//	        bordereauActe.setSourceInterne(true);
//
//	        bordereauActe.setId(bordereauActeId.getId());
//
//
//	        BordereauActe borActe = bordereauActeDao.saveAndFlush(bordereauActe);
//
//	        userForm.setBordereauActeContrib(borActe);
//
//	            userForm.setStatutCourant("SS");
//
//	            Acte acte = new Acte();
//	            Acte acteREcherche = acteDao.findActeByNumeroEquals(userForm.getNumero());
//	            if(acteREcherche!=null) {
//	                if (acteREcherche.getId() == 0. || acteREcherche.getId() == null) {
//	                    acte = acteDao.saveAndFlush(userForm);
//	                } else {
//	                    userForm.setId(acteREcherche.getId());
//	                    acte = acteDao.saveAndFlush(userForm);
//	                }
//	            }else{
//	                  acte = acteDao.saveAndFlush(userForm);
//	            }
//
//
//
//
//	        bordereauActe.getActesBordereau().add(acte);
//
//	        AvoirStatut avoirStatut = new AvoirStatut();
//			  avoirStatut.setAgent(acte.getAgentLiquidateur());
//			  avoirStatut.setNumero(acte.getNumero());
//			  avoirStatut.setStatutString(acte.getStatutCourant());
//			  avoirStatut.setDesignation(acte.getDesignation());
//			  avoirStatut.setStatutString("SS");
//			  avoirStatut.setCommentaire("Acte Pre liquidé");
//
//			  avoirStatut.setDateModification(new Date());
//			  avoirStatut.setDateDebut(new Date());
//			  avoirStatut.setDateFin(new Date());
//	        avoirStatutDao.saveAndFlush(avoirStatut);
//
//	        return userForm;
//
//	        }else{
	        	
	            System.out.println("----------------Avant ---------------------- "+userForm);
	            BordereauActe borActe = new BordereauActe();
	            BordereauActe borActeAjouter = new BordereauActe();
	            BordereauActe borActeRecherche  = bordereauActeDao.findByNumeroEquals("ASSAUTDGICONT");

	            if(borActeRecherche == null) {
	                System.out.println("----------------Milieu ---------------------- ");
	                borActe.setNumero("ASSAUTDGICONT");
	                borActeRecherche = bordereauActeDao.saveAndFlush(borActe);
	                System.out.println(borActeAjouter+"--------borActeAjouter---------");

	            System.out.println("Deb----------Ajout RRRRRRRRRRRRRR------------");

	            }

	            userForm.setBordereauActeContrib(borActeRecherche);
//	            userForm.setAgentLiquidateur(authAgentApi.whoami(req));

	            userForm.setBordereauActe(borActeRecherche);


	            Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
	            Double plusValueImmobiliere = userForm.getPlusValueImmobiliere() == null ? 0. : userForm.getPlusValueImmobiliere();

	            
	            Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
	            Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
	            Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
	            Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
	            Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
	            Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
	            Double montantTimbre = nombreTimbre * ValeurTimbre;

	            
	            userForm.setStatutCourant("PRE");
	            BordereauActe bordereauActe = userForm.getBordereauActe();


	            Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();

	            Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();

	            Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();

	            Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();
	            Double totalPlusValueImmobiliereAncien = bordereauActe.getTotalPlusValueImobiliere() == null ? 0. : bordereauActe.getTotalPlusValueImobiliere();


	            Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();

	            Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();


	            bordereauActe.setTotalAmende(totalAmendeAncien + amende);
	            bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple);

	            bordereauActe.setTotalPlusValueImobiliere(totalPlusValueImmobiliereAncien + plusValueImmobiliere);

	            bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite);
	            bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance);
	            bordereauActe.setNbreActe(nombreActeAncien + 1);
	            bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre);
	            bordereauActe.setId(borActeRecherche.getId());

	            System.out.println(borActeRecherche.getId()+"--------borActeRecherche.getId()---------");

	            acteBordereauStandard = acteDao.saveAndFlush(userForm);

	            System.out.println("Fin----------Ajout RRRRRRRRRRRRRR------------");
	            borActeRecherche.getActesBordereau().add(acteBordereauStandard);

	            AvoirStatut avoirStatut = new AvoirStatut();
	            avoirStatut.setAgent(acteBordereauStandard.getAgentLiquidateur());
	            avoirStatut.setNumero(acteBordereauStandard.getNumero());

	            avoirStatut.setStatutString("PRE");
	            avoirStatut.setCommentaire("Acte Pre liquidé");
	            avoirStatut.setDesignation(acteBordereauStandard.getDesignation());
	            avoirStatut.setDateModification(new Date());
	            avoirStatut.setDateDebut(new Date());
	            avoirStatut.setDateFin(new Date());
	            avoirStatutDao.saveAndFlush(avoirStatut);


	            System.out.println("----------------Non Ajout--Le numéro de ");




	            return acteBordereauStandard;
//	        }

	    }


	    public Acte ajoutActePourQuittance( Acte userForm, List<Quittance> quittances) {

			


	        System.out.println("Début service ajoutQuittance----------");
	        System.out.println("Objet Acte ----------"+ userForm.getId());

	        Acte acteBordereauStandard = new Acte();


	        if(quittances!=null){

	            quittances.forEach(quittance->{
	                userForm.getQuittances().add(quittance);
	                quittance.setActeQuittance(userForm);
	                quittance.setCaissier(authAgentApi.whoami(request));

	                quittanceDao.saveAndFlush(quittance);

	            });
	        }
	        
	        BordereauActe bordereauActeRecherche  = userForm.getBordereauActe() == null ? null : userForm.getBordereauActe();
	        if(bordereauActeRecherche!=null){

	            System.out.println("userForm.getBordereauActe() Non null  et id"+userForm.getBordereauActe().getId());
	        BordereauActe bordereauActeId;
	        if(userForm.getBordereauActe().getId()==0L){
	            System.out.println("--------Les numero de bordereau et de Id-------"+userForm.getBordereauActe().getNumero()+"----------"+userForm.getBordereauActe().getId()+"-------------------------");
	            System.out.println("-------------------------1111111111111111111111111-------------------------");
	            bordereauActeId = bordereauActeDao.saveAndFlush(userForm.getBordereauActe());
	            System.out.println("---------bordereauActe.getId()--------->"+bordereauActeId.getId());
	        }else{
	            System.out.println("------------------------------222222222222222222-------------------------");
	            bordereauActeId = userForm.getBordereauActe();
	            System.out.println("---------bordereauActe.getId()--------->"+bordereauActeId.getId());
	        }




	        Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
	        Double plusValueImmobiliere = userForm.getPlusValueImmobiliere() == null ? 0. : userForm.getPlusValueImmobiliere();

	        Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
	        Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
	        Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
	        Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
	        Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
	        Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
	        Double montantTimbre = nombreTimbre * ValeurTimbre;

	        BordereauActe bordereauActe = userForm.getBordereauActe();
	        Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();
	        Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();
	        Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();
	        Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();
	        Double totalPlusValueImmobiliereAncien = bordereauActe.getTotalPlusValueImobiliere() == null ? 0. : bordereauActe.getTotalPlusValueImobiliere();

	        
	        Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();
	        Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();


	        bordereauActe.setTotalAmende(totalAmendeAncien + amende);
	        bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple);
	        bordereauActe.setTotalPlusValueImobiliere(totalPlusValueImmobiliereAncien + plusValueImmobiliere);

	        bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite);
	        bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance);
	        bordereauActe.setNbreActe(nombreActeAncien + 1);
	        bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre);
	        bordereauActe.setValidated(true);
	        bordereauActe.setSourceInterne(true);

	        bordereauActe.setId(bordereauActeId.getId());

	        BordereauActe borActe = bordereauActeDao.saveAndFlush(bordereauActe);
	        userForm.setBordereauActeContrib(borActe);

	            userForm.setStatutCourant("SS");

	            Acte acte = new Acte();
	            Acte acteREcherche = acteDao.findActeByNumeroEquals(userForm.getNumero());
	            if(acteREcherche!=null) {
	                if (acteREcherche.getId() == 0. || acteREcherche.getId() == null) {
	                    acte = acteDao.saveAndFlush(userForm);
	                } else {
	                    userForm.setId(acteREcherche.getId());
	                    acte = acteDao.saveAndFlush(userForm);
	                }
	            }else{
	                  acte = acteDao.saveAndFlush(userForm);
	            }




	        bordereauActe.getActesBordereau().add(acte);

	        AvoirStatut avoirStatut = new AvoirStatut();
			  avoirStatut.setAgent(acte.getAgentLiquidateur());
			  avoirStatut.setNumero(acte.getNumero());
			  avoirStatut.setStatutString(acte.getStatutCourant());
			  avoirStatut.setDesignation(acte.getDesignation());
			  avoirStatut.setStatutString("SS");
			  avoirStatut.setCommentaire("Acte Saisie");

			  avoirStatut.setDateModification(new Date());
			  avoirStatut.setDateDebut(new Date());
			  avoirStatut.setDateFin(new Date());
	        avoirStatutDao.saveAndFlush(avoirStatut);


	        return userForm;
	        }else{
	            System.out.println("----------------Avant ---------------------- "+userForm);

	            BordereauActe borActe = new BordereauActe();
	            BordereauActe borActeAjouter = new BordereauActe();
	            BordereauActe borActeRecherche  = bordereauActeDao.findByNumeroEquals("ASSAUTDGICONT");

	            if(borActeRecherche == null) {
	                System.out.println("----------------Milieu ---------------------- ");
	                borActe.setNumero("ASSAUTDGICONT");
	                borActeRecherche = bordereauActeDao.saveAndFlush(borActe);
	                System.out.println(borActeAjouter+"--------borActeAjouter---------");

	            System.out.println("Deb----------Ajout RRRRRRRRRRRRRR------------");

	            }

	            userForm.setBordereauActeContrib(borActeRecherche);
//	            userForm.setAgentLiquidateur(authAgentApi.whoami(req));

	            userForm.setBordereauActe(borActeRecherche);


	            Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
	            Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
	            Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
	            Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
	            Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
	            Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
	            Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
	            Double montantTimbre = nombreTimbre * ValeurTimbre;


	            BordereauActe bordereauActe = userForm.getBordereauActe();


	            Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();

	            Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();

	            Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();

	            Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();

	            Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();

	            Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();


	            bordereauActe.setTotalAmende(totalAmendeAncien + amende);
	            bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple);
	            bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite);
	            bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance);
	            bordereauActe.setNbreActe(nombreActeAncien + 1);
	            bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre);
	            bordereauActe.setId(borActeRecherche.getId());

	            System.out.println(borActeRecherche.getId()+"--------borActeRecherche.getId()---------");

	            acteBordereauStandard = acteDao.saveAndFlush(userForm);

	            System.out.println("Fin----------Ajout RRRRRRRRRRRRRR------------");
	            borActeRecherche.getActesBordereau().add(acteBordereauStandard);

	            AvoirStatut avoirStatut = new AvoirStatut();
	            avoirStatut.setAgent(acteBordereauStandard.getAgentLiquidateur());
	            avoirStatut.setNumero(acteBordereauStandard.getNumero());

	            avoirStatut.setStatutString("PRE");
	            avoirStatut.setCommentaire("Acte Pre liquidé");
	            avoirStatut.setDesignation(acteBordereauStandard.getDesignation());
	            avoirStatut.setDateModification(new Date());
	            avoirStatut.setDateDebut(new Date());
	            avoirStatut.setDateFin(new Date());
	            avoirStatutDao.saveAndFlush(avoirStatut);


	            System.out.println("----------------Non Ajout--Le numéro de ");




	            return acteBordereauStandard;
	        }

	    }

	  
    
    public Acte ajoutActe( Acte userForm, List<Quittance> quittances) {

		


        System.out.println("Début service ajoutQuittance----------");
        System.out.println("Objet Acte ----------"+ userForm.getId());

        Acte acteBordereauStandard = new Acte();


        if(quittances!=null){

            quittances.forEach(quittance->{
                userForm.getQuittances().add(quittance);
                quittance.setActeQuittance(userForm);
                quittance.setCaissier(authAgentApi.whoami(request));

                quittanceDao.saveAndFlush(quittance);

            });
        }
        
        BordereauActe bordereauActeRecherche  = userForm.getBordereauActe() == null ? null : userForm.getBordereauActe();
        if(bordereauActeRecherche!=null){

            System.out.println("userForm.getBordereauActe() Non null  et id"+userForm.getBordereauActe().getId());
        BordereauActe bordereauActeId;
        if(userForm.getBordereauActe().getId()==0L){
            System.out.println("--------Les numero de bordereau et de Id-------"+userForm.getBordereauActe().getNumero()+"----------"+userForm.getBordereauActe().getId()+"-------------------------");
            System.out.println("-------------------------1111111111111111111111111-------------------------");
            bordereauActeId = bordereauActeDao.saveAndFlush(userForm.getBordereauActe());
            System.out.println("---------bordereauActe.getId()--------->"+bordereauActeId.getId());
        }else{
            System.out.println("------------------------------222222222222222222-------------------------");
            bordereauActeId = userForm.getBordereauActe();
            System.out.println("---------bordereauActe.getId()--------->"+bordereauActeId.getId());
        }




        Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
        Double totalPlusValueImmobiliere = userForm.getPlusValueImmobiliere() == null ? 0. : userForm.getPlusValueImmobiliere();

        Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
        Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
        Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
        Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
        Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
        Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
        Double montantTimbre = nombreTimbre * ValeurTimbre;

        BordereauActe bordereauActe = userForm.getBordereauActe();
        Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();
        Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();
        Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();
        Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();
        Double totalPlusValueImmobiliereAncien = bordereauActe.getTotalPlusValueImobiliere() == null ? 0. : bordereauActe.getTotalPlusValueImobiliere();

        Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();
        Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();


        bordereauActe.setTotalAmende(totalAmendeAncien + amende);
        bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple);
        bordereauActe.setTotalPlusValueImobiliere(totalPlusValueImmobiliere + totalPlusValueImmobiliere);

        bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite);
        bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance);
        bordereauActe.setNbreActe(nombreActeAncien + 1);
        bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre);
        bordereauActe.setValidated(true);
        bordereauActe.setSourceInterne(true);

        bordereauActe.setId(bordereauActeId.getId());


        BordereauActe borActe = bordereauActeDao.saveAndFlush(bordereauActe);

        userForm.setBordereauActeContrib(borActe);

            userForm.setStatutCourant("SS");

            Acte acte = new Acte();
            Acte acteREcherche = acteDao.findActeByNumeroEquals(userForm.getNumero());
            if(acteREcherche!=null) {
                if (acteREcherche.getId() == 0. || acteREcherche.getId() == null) {
                    acte = acteDao.saveAndFlush(userForm);
                } else {
                    userForm.setId(acteREcherche.getId());
                    acte = acteDao.saveAndFlush(userForm);
                }
            }else{
                  acte = acteDao.saveAndFlush(userForm);
            }




        bordereauActe.getActesBordereau().add(acte);

        AvoirStatut avoirStatut = new AvoirStatut();
		  avoirStatut.setAgent(acte.getAgentLiquidateur());
		  avoirStatut.setNumero(acte.getNumero());
		  avoirStatut.setStatutString(acte.getStatutCourant());
		  avoirStatut.setDesignation(acte.getDesignation());
		  avoirStatut.setStatutString("SS");
		  avoirStatut.setCommentaire("Acte Saisie");

		  avoirStatut.setDateModification(new Date());
		  avoirStatut.setDateDebut(new Date());
		  avoirStatut.setDateFin(new Date());
        avoirStatutDao.saveAndFlush(avoirStatut);


        return userForm;
        }else{
            System.out.println("----------------Avant ---------------------- "+userForm);

            BordereauActe borActe = new BordereauActe();
            BordereauActe borActeAjouter = new BordereauActe();
            BordereauActe borActeRecherche  = bordereauActeDao.findByNumeroEquals("ASSAUTDGICONT");

            if(borActeRecherche == null) {
                System.out.println("----------------Milieu ---------------------- ");
                borActe.setNumero("ASSAUTDGICONT");
                borActeRecherche = bordereauActeDao.saveAndFlush(borActe);
                System.out.println(borActeAjouter+"--------borActeAjouter---------");

            System.out.println("Deb----------Ajout RRRRRRRRRRRRRR------------");

            }

            userForm.setBordereauActeContrib(borActeRecherche);
//            userForm.setAgentLiquidateur(authAgentApi.whoami(req));

            userForm.setBordereauActe(borActeRecherche);


            Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
            Double plusValueImmobiliere = userForm.getPlusValueImmobiliere() == null ? 0. : userForm.getPlusValueImmobiliere();

            Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
            Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
            Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
            Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
            Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
            Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
            Double montantTimbre = nombreTimbre * ValeurTimbre;


            BordereauActe bordereauActe = userForm.getBordereauActe();


            Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();

            Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();

            Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();

            Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();
            Double totalPlusValueImmobiliereAncien = bordereauActe.getTotalPlusValueImobiliere() == null ? 0. : bordereauActe.getTotalPlusValueImobiliere();


            Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();

            Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();


            bordereauActe.setTotalAmende(totalAmendeAncien + amende);
            bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple);
            bordereauActe.setTotalPlusValueImobiliere(totalPlusValueImmobiliereAncien + plusValueImmobiliere);

            bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite);
            bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance);
            bordereauActe.setNbreActe(nombreActeAncien + 1);
            bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre);
            bordereauActe.setId(borActeRecherche.getId());

            System.out.println(borActeRecherche.getId()+"--------borActeRecherche.getId()---------");

            acteBordereauStandard = acteDao.saveAndFlush(userForm);

            System.out.println("Fin----------Ajout RRRRRRRRRRRRRR------------");
            borActeRecherche.getActesBordereau().add(acteBordereauStandard);

            AvoirStatut avoirStatut = new AvoirStatut();
            avoirStatut.setAgent(acteBordereauStandard.getAgentLiquidateur());
            avoirStatut.setNumero(acteBordereauStandard.getNumero());

            avoirStatut.setStatutString("PRE");
            avoirStatut.setCommentaire("Acte Pre liquidé");
            avoirStatut.setDesignation(acteBordereauStandard.getDesignation());
            avoirStatut.setDateModification(new Date());
            avoirStatut.setDateDebut(new Date());
            avoirStatut.setDateFin(new Date());
            avoirStatutDao.saveAndFlush(avoirStatut);


            System.out.println("----------------Non Ajout--Le numéro de ");




            return acteBordereauStandard;
        }

    }



    public Acte modifieActe(Acte userForm) {

//        contrôler si la modification est possible en fonction de l'occurence du statut de l'acte


        System.out.println("Début service modifieActe----------");


        Acte acteRecherche = acteDao.findByIdIs(userForm.getId());
        System.out.println("userForm.getId()" + userForm.getId());

        Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
        Double plusVAlueImmobiliere = userForm.getPlusValueImmobiliere() == null ? 0. : userForm.getPlusValueImmobiliere();

        
        Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
        Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
        Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
        Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
        Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
        Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
        Double montantTimbre = nombreTimbre * ValeurTimbre;

        Double droitSimpleBase = acteRecherche.getDroitSimple() == null ? 0. : acteRecherche.getDroitSimple();
        Double plusValueImmobiliereBase = acteRecherche.getPlusValueImmobiliere() == null ? 0. : acteRecherche.getPlusValueImmobiliere();

        Double penaliteBase = acteRecherche.getPenalite() == null ? 0. : acteRecherche.getPenalite();
        Double amendeBase = acteRecherche.getAmende() == null ? 0. : acteRecherche.getAmende();
        Double redevanceBase = acteRecherche.getRedevance() == null ? 0. : acteRecherche.getRedevance();
        Double montantPayeBase = acteRecherche.getMontantPaye() == null ? 0. : acteRecherche.getMontantPaye();
        Double ValeurTimbreBase = acteRecherche.getValeurTimbre() == null ? 0. : acteRecherche.getValeurTimbre();
        Double nombreTimbreBase = acteRecherche.getNombreTimbre() == null ? 0. : acteRecherche.getNombreTimbre();
        Double montantTimbreBase = nombreTimbre * ValeurTimbre;


        BordereauActe bordereauActe = userForm.getBordereauActe();


        Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();

        Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();
        Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();
        Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();
        Double totalPlusValueImmobiliereAncien = bordereauActe.getTotalPlusValueImobiliere() == null ? 0. : bordereauActe.getTotalPlusValueImobiliere();

        
        Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();

        Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();

        bordereauActe.setTotalAmende(totalAmendeAncien + amende - amendeBase);
        bordereauActe.setTotalDS(totalDroitSimpleAncien + droitSimple - droitSimpleBase);
        bordereauActe.setTotalPlusValueImobiliere(totalPlusValueImmobiliereAncien + plusVAlueImmobiliere - plusValueImmobiliereBase);

        
        bordereauActe.setTotalPenalite(totalPenaliteAncien + penalite - penaliteBase);
        bordereauActe.setTotalRedevance(totalRedevanceAncien + redevance - redevanceBase);
//        bordereauActe.setNbreActe(nombreActeAncien + 1);
        bordereauActe.setTotalTimbre(totalTimbreAncien + montantTimbre -montantTimbreBase);

        bordereauActeDao.saveAndFlush(bordereauActe);

        Acte acte = acteDao.saveAndFlush(userForm);
        bordereauActe.getActesBordereau().add(acte);

        AvoirStatut avoirStatut = new AvoirStatut();
		  avoirStatut.setAgent(acte.getAgentLiquidateur());
		  avoirStatut.setNumero(acte.getNumero());
		  avoirStatut.setStatutString(acte.getStatutCourant());
		  avoirStatut.setDesignation(acte.getDesignation());
//		  avoirStatut.setMotifrejetString(acte.getmo);
		  avoirStatut.setCommentaire(acte.getObservation());
		  avoirStatut.setDateDebut(new Date());
		  avoirStatut.setDateFin(new Date());
        avoirStatutDao.saveAndFlush(avoirStatut);


        return userForm;

    }


//    public Acte updateActe(Acte userForm) {
//
//        System.out.println("Début service ajoutQuittance----------");
//
//        if (userForm.getId() != null) {
//
//            Acte acte = acteDao.saveAndFlush(userForm);
//
//            System.out.println("userForm.getId()" + userForm.getId());
//
//            Double droitSimple = userForm.getDroitSimple() == null ? 0. : userForm.getDroitSimple();
//            Double penalite = userForm.getPenalite() == null ? 0. : userForm.getPenalite();
//            Double amende = userForm.getAmende() == null ? 0. : userForm.getAmende();
//            Double redevance = userForm.getRedevance() == null ? 0. : userForm.getRedevance();
//            Double montantPaye = userForm.getMontantPaye() == null ? 0. : userForm.getMontantPaye();
//            Double ValeurTimbre = userForm.getValeurTimbre() == null ? 0. : userForm.getValeurTimbre();
//            Double nombreTimbre = userForm.getNombreTimbre() == null ? 0. : userForm.getNombreTimbre();
//            Double montantTimbre = nombreTimbre * ValeurTimbre;
//
//
//            BordereauActe bordereauActe = userForm.getBordereauActe();
//
//
//            Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();
//
//            Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();
//            Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();
//            Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();
//            Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0. : bordereauActe.getTotalRedevance();
//
//            Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();
//
//            bordereauActe.setTotalAmende(totalAmendeAncien - amende);
//            bordereauActe.setTotalDS(totalDroitSimpleAncien - droitSimple);
//            bordereauActe.setTotalPenalite(totalPenaliteAncien - penalite);
//            bordereauActe.setTotalRedevance(totalRedevanceAncien - redevance);
////            bordereauActe.setNbreActe(nombreActeAncien + 1);
//            bordereauActe.setTotalTimbre(totalTimbreAncien - montantTimbre);
//            bordereauActeDao.saveAndFlush(bordereauActe);
//
//
//            AvoirStatut avoirStatut = new AvoirStatut();
////		  avoirStatut.set
//            avoirStatutDao.saveAndFlush(avoirStatut);
//        }
//
//        return userForm;
//
//    }





}
