package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;
import com.dgi.dsi.winregistre.dao.TypePenaliteAmendeDao;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
import com.dgi.dsi.winregistre.service.ZXingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//import org.jfree.util.Log;

@RestController
@CrossOrigin("*")
public class GlobalApi {

//	@Autowired
//	private agentDao agentDao;

	@Autowired
	private ActeDao acteDao;

	@Autowired
	private CalculDroit calculDroit;

	@Autowired
	private WorkingOnController workingOnController;


    @Autowired
    private NatureActeDao natureActeDao;

    @Autowired
    private TypePenaliteAmendeDao typePenaliteAmendeDao;



    @RequestMapping(value = "qrcode/{id}", method = RequestMethod.GET)
    public void qrcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(ZXingHelper.getQRCodeImage(id, 200, 200));
        outputStream.flush();
        outputStream.close();
    }


    @GetMapping(value = "/dateEcheanceActe/{numeroActe}")
    public ResponseEntity<?> getDateEcheanceActe(@PathVariable String numeroActe) {


        System.out.println("------------Début-------getDateEcheanceActe----" );

        Acte acte = acteDao.findActeByNumeroEquals(numeroActe);
        System.out.println("OKAY");

        Map<String, LocalDate> map = new HashMap<>();

        String communeActe;
        Long dureeActe;
        Double majoration = 0.0;
        Double droitSimple = 0.0;
        Double penalite = 0.0;
        LocalDate dateMajoration = LocalDate.now();
        LocalDate dateEcheance = LocalDate.now();

//        LocalDate dateFormatter = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate dateActe = LocalDate.parse(acte.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        LocalDate dateEnregistrement = LocalDate.parse(acte.getDateEnregistrement().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        System.out.println(acte.getNatureActe().getId());
        NatureActe natureActe = natureActeDao.findByIdIs(acte.getNatureActe().getId());

        System.out.println("-----je suis ici- début récupération---");


        if (natureActe != null) {

            TypePenaliteAmende typePenaliteAmende = typePenaliteAmendeDao.findByIdIs(natureActe.getTypePenaliteAmende().getId());
            Boolean acteCommuneCentreEnregistrement = acte.getCommuneActe().getCentreEnregistrement() == null ? Boolean.FALSE : Boolean.TRUE;
            String actenatureActeTypePenAmendeJourMoisAnnee = typePenaliteAmende.getJourMoisAnnee() == null ? "T" : typePenaliteAmende.getJourMoisAnnee();
            System.out.println("-----je suis ici- fin récupération---");
            //Durée plus 1 pour prendre un piquet + 2 pour les deux piquets
            dureeActe = ChronoUnit.DAYS.between(dateEnregistrement, dateActe) + 1;

            System.out.println("--->Durée entre date acte et date enrégistrement " + dureeActe);
//            dureeActe = dureeActe+2;
            System.out.println("--->Durée entre date acte et date enrégistrement Bis " + dureeActe);

            JourFerieService jourFerie = new JourFerieService();
            System.out.println("------acteCommuneCentreEnregistrement--------" + acteCommuneCentreEnregistrement);
            System.out.println("----------actenatureActeTypePenAmendeJourMoisAnnee-------"+actenatureActeTypePenAmendeJourMoisAnnee);
            if (acteCommuneCentreEnregistrement == true) {
                System.out.println(actenatureActeTypePenAmendeJourMoisAnnee);
                if (actenatureActeTypePenAmendeJourMoisAnnee == "J") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.DAYS);
                }

                System.out.println(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance());
                if (actenatureActeTypePenAmendeJourMoisAnnee == "M") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.MONTHS);
                }
                if (actenatureActeTypePenAmendeJourMoisAnnee == "A") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance(), ChronoUnit.YEARS);
                }
            } else {
                if (actenatureActeTypePenAmendeJourMoisAnnee == "J") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.DAYS);
                }
                if (actenatureActeTypePenAmendeJourMoisAnnee == "M") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
                    dateActe.plus(2 * (acte.getNatureActe().getTypePenaliteAmende().getDelaiEcheance()), ChronoUnit.MONTHS);
                }
                if (actenatureActeTypePenAmendeJourMoisAnnee == "A") { //acte.getNatureActe().getTypePenaliteAmende().getJourMoisAnnee()
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

            dureeActe = ChronoUnit.DAYS.between(dateEnregistrement, dateActe) + 1;
            System.out.println("---> date de l'acte apres férié" + dateActe + "---durée" + dureeActe);

             if (dureeActe <= 0) {
                dateEcheance = dateEnregistrement;


             }else{
                dateEcheance = dateActe;


             }

        }

        map.put("dateEcheance", dateEcheance );


        return ResponseEntity.ok().body(map);

    }

	@GetMapping(value = "/dateServeur")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
//    ResponseEntity<?>
	public ResponseEntity<?> getDateServeur() {



//		return LocalDate.parse(LocalDate.now().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.FRANCE));
        Map<String, String> map = new HashMap();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

        map.put("dateServeur",dt1.format(new Date()));

        return ResponseEntity.ok()
                .body(map);

	}


    @GetMapping(value = "/isSamedi/{dateTest}")
    public Boolean isSamedi(@PathVariable String dateTest) {
        JourFerieService testFonctionJF = new JourFerieService();

        return testFonctionJF.isSamedi(dateTest);


    }


    @GetMapping(value = "/isDimanche/{date}")
    public Boolean isDimanche(@PathVariable String date) {
        JourFerieService testFonctionJF = new JourFerieService();
	    return testFonctionJF.isDimanche(date);
    }


    @GetMapping(value = "/isFerie/{date}")
    public Boolean isFerie(@PathVariable String date) {
        JourFerieService testFonctionJF = new JourFerieService();
	    return testFonctionJF.isFerie(date);
    }








}
