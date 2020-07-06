package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.*;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.payload.ActeWithQuittance;
import com.dgi.dsi.winregistre.payload.MentionPayload;
import com.dgi.dsi.winregistre.payload.SortiePaiement;

//import org.jfree.util.Log;
import com.dgi.dsi.winregistre.service.AjoutQuittanceService;
import com.dgi.dsi.winregistre.service.JourFerieService;
import com.github.royken.converter.FrenchNumberToWords;
import com.ibm.icu.text.SimpleDateFormat;

import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.core.io.ResourceLoader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@Transactional
public class ActeApi {

//	@Autowired
//	private agentDao agentDao;

	@Autowired
	private ActeDao acteDao;

	@Autowired
	private NatureActeDao natureActeDao;

	@Autowired
	private CalculDroit calculDroit;

	@Autowired
	private WorkingOnController workingOnController;
	

	@Autowired
	private AuthAgentApi authAgentApi;


	@Autowired
	private AgentDao agentDao;


	@Autowired
	private ActePageableDao actePageableDao;
	@Autowired
	private BordereauActeDao bordActeDao;
	@Autowired
	private BordereauCaisseDao bordereauCaisseDao;
	@Autowired
	private QuittanceDao quittanceDao;
	@Autowired
	private DepartementDao departementDao;

	@Autowired
	private ContribuableDao contribuableDao;
	@Autowired
	private BordereauActeDao bordereauActeDao;
	@Autowired
	private NumeroOrdreDao numeroOrdreDao;

	@Autowired
	private AvoirStatutDao avoirStatutDao;

	@Autowired
	private DetailQuittanceDao detailQuittanceDao;

	@Autowired
	private AjoutQuittanceService ajoutQuittanceService;

	private static final String EXTERNAL_FILE_PATH = "C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\jasper\\reportQuittance\\quittance.jrxml";
//    private static final String EXTERNAL_FILE_PATH = "/opt/tomcat/apache-tomcat-9.0.14/webapps/jasper/reportQuittance/quittance.jrxml";
	private static final String EXTERNAL_FILE_PATHBis = "/opt/tomcat/apache-tomcat-9.0.14/webapps/jasper/reportSommier/reportSommierAss.jrxml";
//	private static final String EXTERNAL_FILE_PATH = "/home/dgi/winregistre/src/main/resources/jasper/reportQuittance/quittance.jasper";

	public List<Map<String, Object>> report(String numeroQuittance) {
		System.out.println(
				"------------------------numeroQuittance One -------------------> " + numeroQuittance);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		Quittance quittanceee  = null;
		try {
			quittanceee = quittanceDao.getQuittanceByNumeroQuittance(numeroQuittance);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("------------------------numeroQuittance-----------XXXXXXXXX " + quittanceee.getNumeroQuittance());

		Map<String, Object> item = new HashMap<String, Object>();


		if(quittanceee!=null) {
			System.out.println("-----------------------> " );
			Integer montant = quittanceee.getMontantPaye().intValue();
			String nomCaissier = quittanceee.getEncodeur();
			item.put("numeroQuittance", quittanceee.getNumeroQuittance());
			item.put("dateQuittance", quittanceee.getDateQuittance());
			item.put("montantPaye", quittanceee.getMontantPaye());
			item.put("montantPaye", quittanceee.getModePaiement());
			List<DetailQuittance> detailQuittances = detailQuittanceDao
					.findDetailQuittanceByQuittance_Id(quittanceee.getId());
			
			item.put("quittancesDetail", detailQuittances);
			result.add(item);
			System.out.println("------------------------numeroQuittance 111-------------------> FIn Source de Donnees");

		}else {
			System.out.println("Quittance est null");
			System.out.println("------------------------numeroQuittance 222-------------------> FIn Source de Donnees");

		}
		

		return result;
	}

	


	
	
	
	
	public List<Map<String, Object>> sourcePreliquidation(String numeroActe) {

		System.out.println("-------------------------------------------------------->>>" + numeroActe);

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new HashMap<String, Object>();

		Acte acte = acteDao.findByNumeroActe(numeroActe);
		



		if (acte != null) {
			System.out.println(1);
			item.put("numeroActe", acte.getNumero());
			System.out.println(numeroActe);


		} else {
			System.out.println(2);

			item.put("numeroActe", null);

		}

		result.add(item);

		return result;
	}

	@GetMapping(value = "/requestReportSommier/{case}/{volume}/{folio}/{codeServiceAgent}")
	public List<Acte> sourceSommier(String caseSommier, String volumeSommier, String folioSommier,
			String codeServiceAgent) {

		List<Acte> result = new ArrayList<>();
		result = acteDao.listActeAuSommierCaseVolumeFolio(caseSommier.toUpperCase(), volumeSommier.toUpperCase(),
				folioSommier.toUpperCase(), codeServiceAgent.toUpperCase());
		return result;
	}

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private GrilleActeSousSeingPriveDao grilleActeSousSeingPriveDao;

	@GetMapping(value = "/listGrille")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public GrilleActeSousSeingPrive getGrille() {

		return grilleActeSousSeingPriveDao.findByDelaiMaxGreaterThanAndDelaiMinLessThanEqual(5, 5);

	}




	@GetMapping(value = "/listActesContribuables")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Acte> getActesContribuable() {

		List<Acte> actes = acteDao.getActeContribuables(false, "QT");

//        actes.get(0).getq
		return actes;
	}

	
	@GetMapping(value = "/listActeAuSommier/{codeService}")
	public List<Acte> getListActeAuSommier(@PathVariable String codeService) {

		if (codeService.isEmpty() || codeService == null) {
			return null;
		} else {
			List<Acte> actes = acteDao.listActeAuSommier( "%APP%", codeService.toUpperCase());
			return actes;
		}

	}

	@GetMapping(value = "/listActeAudit/{numero}")
	public List<AvoirStatut> getListActeAudit(@PathVariable String numero) {

		List<AvoirStatut> avoirSTatut = acteDao.listActeAuditAvoirStatut(numero);

		return avoirSTatut;

	}
	

	@GetMapping(value = "/listActeAuSommier")
	public List<Acte> getlistActeSommier(HttpServletRequest req) {

			String codeService = authAgentApi.whoami(req).getService().getCode();
//			List<Acte> actes = acteDao.listActeAMentionner("%VA%", "%TRV%", "%QT%", "%SS%", codeService.toUpperCase());
			List<Acte> actes = acteDao.listActeAuSommier( "%SOM%", codeService.toUpperCase());


			return actes;

	}

	
	@GetMapping(value = "/listActeDuJourAuSommier/{dateduSommier}")
	public List<Acte> getListActeDuJourAuSommier(HttpServletRequest req, @PathVariable LocalDate dateduSommier) {
		
		String codeService = authAgentApi.whoami(req).getService().getCode();

		if (codeService.isEmpty() || codeService == null) {
			return null;
		} else {
//			List<Acte> actes = acteDao.listActeAMentionner("%VA%", "%TRV%", "%QT%", "%SS%", codeService.toUpperCase());
			List<Acte> actes = acteDao.listActeDujourAuSommier( "%SOM%", codeService.toUpperCase(), dateduSommier);

			return actes;
		}

	}

	
	@GetMapping(value = "/listActesSommier")
	public List<Acte> getlistActesSommier(HttpServletRequest req) {



			String codeService = authAgentApi.whoami(req).getService().getCode();

//			List<Acte> actes = acteDao.listActeAMentionner("%VA%", "%TRV%", "%QT%", "%SS%", codeService.toUpperCase());
			List<Acte> actes = acteDao.listActeAuSommier( "%SOM%", codeService.toUpperCase());

			return actes;


	}
	


	
	@GetMapping(value = "/listActesMentionByDate/{dateSommier}")
	public List<Acte> getlistActesMentionByDate(HttpServletRequest req, @PathVariable String dateSommier) {


//		String pattern = "yyyy-MM-dd";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		Date date = simpleDateFormat.parse(dateSommier);
		

		 LocalDate dateFormatter = LocalDate.parse(dateSommier, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
		    System.out.println("----------> dateFormatter "+ dateFormatter);
		  


		if ( dateSommier == null) {
			return null;
		} else {
			
		     String codeService = authAgentApi.whoami(req).getService().getCode();

//			List<Acte> actes = acteDao.listActeAMentionner("%VA%", "%TRV%", "%QT%", "%SS%", codeService.toUpperCase());
			List<Acte> actes = acteDao.listActeAuSommierParDate( "%SOM%", codeService.toUpperCase(), dateFormatter);

			return actes;
		}

	}

	
	@GetMapping(value = "/listActeAMentionner/{codeService}")
	public List<Acte> getListActeAMentionner(@PathVariable String codeService) {

		if (codeService.isEmpty() || codeService == null) {
			return null;
		} else {
//			List<Acte> actes = acteDao.listActeAMentionner("%VA%", "%TRV%", "%QT%", "%SS%", codeService.toUpperCase());
			List<Acte> actes = acteDao.listActeAuSommier( "%APP%", codeService.toUpperCase());

			return actes;
		}

	}

	@GetMapping(value = "/getActesInTransfertLot/{numeroTransfert}")
	public List<Acte> getActesInTransfertLot(@PathVariable String numeroTransfert) {
		System.out.println(
				"---------------> numeroTRansfert " + numeroTransfert + "  miniscule " + numeroTransfert.toLowerCase());

		if (numeroTransfert.isEmpty() || numeroTransfert == null) {
			return null;
		} else {
			List<Acte> actes = acteDao.getActesInTransfertLotDao(numeroTransfert.toUpperCase(), "TRV");
			return actes;
		}

	}

	@GetMapping(value = "/listNumeroTransfertActeStatut")
	public List<Acte> getListNumeroTransfertActeStatut() {

		List<Acte> actes = actePageableDao.listeTransfererStatutB("%TRV%");
		return actes;
	}

	@GetMapping(value = "/listNumeroTransfertActeStatutB/{page}/{size}")
	public Page<Acte> getListNumeroTransfertActeStatutB(@PathVariable int page, @PathVariable int size) {

		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		Page<Acte> actes = actePageableDao.listeTransfererStatut("%TRV%", firstPageWithTwoElements);
		return actes;
	}

	
	
//	@GetMapping(value = "/listContribuablesByPage")
//	public Page<ContribuableBis> listContribuablesByPage(@RequestParam(value="page", required=true)  int page, @RequestParam(value="size", required=true)  int size, @RequestParam(value="keyWord", required=false)  String keyWord) {
//
//		Page<ContribuableBis> contribuableBis;
//		if(keyWord == null || keyWord.isEmpty()) {
//			Pageable firstPageWithTwoElements = PageRequest.of(page, size);
//			contribuableBis = contribuableDao.listeConstriByPage("%"+""+"%", firstPageWithTwoElements);
//
//		}else {
//			Pageable firstPageWithTwoElements = PageRequest.of(page, size);
//			contribuableBis = contribuableDao.listeConstriByPage("%"+keyWord.toUpperCase()+"%", firstPageWithTwoElements);
//
//		}
//			
//				return contribuableBis;
//	}
//	
	
	@GetMapping(value = "/listActesAquittancer")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Acte> getActesAquittancer() {

		LocalDate dateReference = LocalDate.now().plus(-1, ChronoUnit.MONTHS);

				
		List<Acte> actes = acteDao.findActeByMontantDuIsAfterAndDateEnregistrementAfter(0., dateReference);

		return actes;
	}

	@GetMapping(value = "/oneBordereauActe/{numeroActe}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public BordereauActe getBordereauActe(@PathVariable String numeroActe) {

		System.out.println(numeroActe);

		if (numeroActe.isEmpty() || numeroActe == null) {
			return null;
		} else {
			Acte acte = acteDao.findByNumeroActe(numeroActe);
			if (acte != null) {
				BordereauActe bordActe0 = bordActeDao.findByIdIs(acte.getBordereauActe().getId());

				if (bordActe0 == null) {
					return null;
				} else {
					System.out.println(bordActe0);
					return bordActe0;
				}
			} else {
				return null;
			}
		}

	}

	@GetMapping(value = "/oneActeByNumero/{numeroActe}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public Acte getActeByNumero(@PathVariable String numeroActe) {

		System.out.println(numeroActe);
		if (numeroActe.isEmpty() || numeroActe == null) {
			return null;
		} else {
//            Acte acte = acteDao.findByNumeroActe(numeroActe);
			Acte acte = acteDao.findActeByNumero(numeroActe);
			return acte;

		}

	}

	@GetMapping(value = "/listQuittancesActe/{numeroActe}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Quittance> getQuittancesActeByNumero(@PathVariable String numeroActe) {

		System.out.println("------------getQuittanceActeByNumero-------------" + numeroActe);
//        getActeByNumero(numeroActe).getId()
		if (numeroActe.isEmpty() || numeroActe == null) {
			return null;
		} else {
			List<Quittance> quittances = acteDao.findByNumeroQuittance(numeroActe);
			return quittances;
		}

	}

	@GetMapping(value = "/listActeBordereau/{numBordereau}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Acte> getlistActesBordereau(@PathVariable String numBordereau) {

		System.out.println(numBordereau);
		if (numBordereau.isEmpty() || numBordereau == null) {
			return null;
		} else {
			BordereauActe bordActe0 = bordActeDao.findByNumeroEquals(numBordereau);

			if (bordActe0 == null) {
				return null;
			} else {
				return acteDao.listActeBordereau(bordActe0.getId());
			}

		}

//        List<Acte> listActe = acteDao.findOne(bordActe0.getId());

	}

	@GetMapping(value = "/lastNumberActesBordereau/{numBordereau}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public String getNumLastActesBordereau(@PathVariable String numBordereau) {

		System.out.println("----------------numBordereau-------------------" + numBordereau);
		if (numBordereau.isEmpty() || numBordereau == null) {
			return null;
		} else {

			System.out.println("Avant");
			BordereauActe bordActe0 = bordActeDao.findByNumeroEquals(numBordereau.toUpperCase());
			System.out.println("Apres " + bordActe0);

			if (bordActe0 != null) {
				System.out.println("-----------bordActe0.getId()----------" + bordActe0.getId());
				Long idBordereauActe = acteDao.maxIdByBordereauContrib(bordActe0.getId());

				System.out.println("----------------idBordereauActe-------------------" + idBordereauActe);

				if (idBordereauActe != null ) {
					Acte bordActe = acteDao.findByIdIs(idBordereauActe);

					String[] decoupe = bordActe.getNumero().split("_");

					System.out.println("-----------> Numero Bordereau" + bordActe.getNumero());
					System.out.println("---> decoupe " + (decoupe.length - 1));
					return decoupe[decoupe.length - 1];
				} else {

					return null;
				}

			} else {
				return null;
			}
		}

	}

	@GetMapping(value = "/lastNumberActesBordereauAutresPaiements/{suffixActe}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public String lastNumberActesBordereauAutresPaiements(@PathVariable String suffixActe) {

		System.out.println("----------------numBordereau-------------------" + "ASSAUTDGICONT");

		BordereauActe bordActe0 = bordActeDao.findByNumeroEquals("ASSAUTDGICONT".toUpperCase());

		if (bordActe0 != null) {
			System.out.println("-----------bordActe0.getId()----------" + bordActe0.getId());
			Long idBordereauActe = acteDao.maxIdByBordereauAndSuffixNumActe(bordActe0.getId(), "%" + suffixActe + "%");
//        idBordereauActe = idBordereauActe==null ?1L: idBordereauActe;
			System.out.println("----------------idBordereauActe-------------------" + idBordereauActe);

			if (idBordereauActe != null) {
				Acte bordActe = acteDao.findByIdIs(idBordereauActe);

				String[] decoupe = bordActe.getNumero().split("_");

				System.out.println("----------->" + bordActe.getNumero());
				System.out.println("--->" + (decoupe.length - 1));
				return decoupe[decoupe.length - 1];
			} else {
				return null;
			}

		} else {
			return null;
		}

	}
	


	@GetMapping(value = "/quittance/{numeroQuittance}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public Quittance getQuittance(@PathVariable String numeroQuittance) {

		Quittance quittanceee = quittanceDao.getQuittanceByNumeroQuittance(numeroQuittance);

		return quittanceee;
	}

	
	@GetMapping(value = "/listActes")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Acte> getActes() {

		return acteDao.findAll();
	}

	@PostMapping("/calculPaiementAss")
	public SortiePaiement calculPaiementAss(@RequestBody Acte userForm) throws Exception {

		System.out.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>> " + userForm + " -----------------------");
		if (userForm == null) {
			return null;
		} else {
			return calculDroit.calculPaiement(userForm);

		}

	}

//	@Transactional
	@PostMapping("/validerActeAss")
	public Boolean validerActeAss(@RequestBody Acte acte) throws Exception {

		System.out.println(acte + "-----------------------");
		if (acte == null) {
			return false;
		} else {

			acte.setStatutCourant("VA");

			acteDao.saveAndFlush(acte);

			AvoirStatut avoirStatut = new AvoirStatut();
			avoirStatut.setAgent(acte.getAgentLiquidateur());
			avoirStatut.setNumero(acte.getNumero());
			avoirStatut.setStatutString("VA");
			avoirStatut.setDesignation(acte.getDesignation());

			avoirStatut.setCommentaire("Acte Validé");
			avoirStatut.setDateModification(new Date());
			avoirStatut.setDateDebut(new Date());
			avoirStatut.setDateFin(new Date());
			avoirStatutDao.saveAndFlush(avoirStatut);

			return true;
		}

	}
	

	@Autowired
	private CalculPenaliteASS calculPenaliteASS;



//	@Transactional
	@PostMapping("/validerActe")
	public Boolean validerActe(@RequestBody List<Acte> actes) throws Exception {

		System.out.println(actes + "-----------------------");
		if (actes == null) {
			return false;
		} else {
			actes.forEach(acte -> {
				acte.setStatutCourant("VA");
				acteDao.saveAndFlush(acte);

				AvoirStatut avoirStatut = new AvoirStatut();
				avoirStatut.setAgent(acte.getAgentLiquidateur());
				avoirStatut.setNumero(acte.getNumero());
				avoirStatut.setStatutString("VA");
				avoirStatut.setDesignation(acte.getDesignation());

				avoirStatut.setCommentaire("Acte Validé");
				avoirStatut.setDateModification(new Date());
				avoirStatut.setDateDebut(new Date());
				avoirStatut.setDateFin(new Date());
				avoirStatutDao.saveAndFlush(avoirStatut);
			}

			);

			return true;

		}
	}

	@PostMapping("/ajoutMentionASS/{codeService}")
	public void ajoutMentionASS(@RequestBody Acte userForm, @PathVariable String codeService) throws Exception {

		acteDao.saveAndFlush(userForm);
		NumeroOrdre numeroOrdre = numeroOrdreDao.numeroOrdreServiceObjet(codeService);
		numeroOrdre.setNumeroOrdre(numeroOrdre.getNumeroOrdre() + 1);
		numeroOrdreDao.saveAndFlush(numeroOrdre);

	}



	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.username}")
	private String login;
	@Value("${spring.datasource.driver-class-name}")
	private String driver;

	@Value("${file.logo.dgi}")
	private String logoDgi;

	@Value("${file.logo.mfe}")
	private String logoMfe;

	@Value("${file.rapport.windows}")
	private String rapportPathWindows;

	@Value("${file.rapport.reportPre}")
	private String rapportPathWindowsP;
	
	@Value("${file.rapport.reportTitrePre}")
	private String rapportPathWindowsTP;
	
	@Value("${file.rapport.journalCaisse}")
	private String rapportPathWindowsJc;
	

	@Value("${file.rapport.linux}")
	private String rapportPathLinux;

	@Value("${spring.datasource.jdbc-url}")
	private String url;

//	public String getUplodsDir() {
//		return uplodsDir;
//	}
	

    @Autowired
    private ResourceLoader resourceLoader;
    
	
    private InputStream getStream(String fileName) throws IOException {
        InputStream stream = new ClassPathResource(fileName).getInputStream();
        return stream;
    }
	

    private String getResourcePath(String fileName) throws IOException {
        return resourceLoader.getResource("classpath:" + fileName).getFile().getAbsolutePath();
    }

	   
	
    public void printer(HttpServletResponse response, Map<String, Object> parameters, InputStream stream, List<?> data) throws JRException, IOException {

        String fileName = "tolode";


        try {
        	
        	Connection conn = null;
        	Class.forName("org.postgresql.Driver");
    		conn = DriverManager.getConnection(url, login, password);

//            JRDataSource dataSource = new JRBeanCollectionDataSource(data, false);


              

                parameters.put("logoMEF", "jasper/images/logo_mef-transparent.png"); 
        		parameters.put("logoDGI", "jasper/images/logo-left.png");
                JRDataSource dataSource = new JRBeanCollectionDataSource(data, false);

//                JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parameters, dataSource);
//                JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parameters, conn);
                JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parameters, conn);


                
                System.out.println("OK----------------------------------------------->");
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName + ".pdf"));
                SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
                reportConfig.setSizePageToContent(true);
                reportConfig.setForceLineBreakPolicy(false);
                SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
                exportConfig.setMetadataAuthor("TOMBOLA");
                exportConfig.setEncrypted(true);
                exportConfig.setAllowedPermissionsHint("PRINTING");
                exporter.setConfiguration(reportConfig);
                exporter.setConfiguration(exportConfig);
                exporter.exportReport();

                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", String.format("attachment; filename=\""+fileName+".pdf\""));
                OutputStream out = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    
    
    @PostMapping("/reportJournalCaisse}")
    @ApiOperation(value = "Imprimer le procès verbal du tirage.")
    @Transactional
    public void reportJournalCaisse(HttpServletResponse response,  @RequestBody String dateBordereauJour, @RequestParam String userNameAgent ) {


        try {

            Map<String, Object> parameters = new HashMap();




                InputStream stream = null;
                try {


                	String pattern = "yyyy-MM-dd";
            		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            		Date date = simpleDateFormat.parse(dateBordereauJour);



            		parameters.put("dateBordereau", date);
            		parameters.put("userName", userNameAgent);

                    stream = getStream("jasper/reportEnregistrement/journalCaisse.jasper");

                     
                    printer(response, parameters, stream, null);

                } catch (IOException e) {
                    e.printStackTrace();
                }catch (JRException e){
                    e.printStackTrace();
                }




        }catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new ApiResponse(false, "Erreur.", HttpStatus.INTERNAL_SERVER_ERROR, e.getStackTrace()), HttpStatus.OK);
        }
    }
	
	


	
//	@GetMapping(value = "/dateEcheance")
////	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
////    ResponseEntity<?>
//	public ResponseEntity<?> getDateEcheance() {
//
//
//		Map<String, String> map = new HashMap();
//		LocalDate dateEcheanceCalcule = calculPenaliteASS.dateEcheance(acte);
//
//		map.put("dateServeur", dateEcheanceCalcule);
//
//		return ResponseEntity.ok().body(map);
//
//	}

	@PostMapping(value = "/reportPreliquidation")
	public void reportSqlPreliquidationPdf2(HttpServletResponse response, @Valid @RequestBody String numeroActe)
			throws Exception, JRException, SQLException {


		System.out.println("--------------------> 1" +numeroActe);
		
		List<Map<String, Object>> source = sourcePreliquidation(numeroActe);

		JRDataSource conn = new JRBeanCollectionDataSource(source);

		// - Chargement et compilation du rapport
		System.out.println("--------------------> 11" +numeroActe);

        InputStream stream = null;
		stream = getStream("jasper/reportEnregistrement/fichePreliquidation.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(stream);

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		System.out.println("--------------------> 111" +numeroActe);

		// - Paramètres à envoyer au rapport
		Map<String, Object> parametersMap = new HashMap();

		Acte acte = acteDao.findByNumeroActe(numeroActe);

		System.out.println("--------------------> 1111" +numeroActe);

		if(acte!=null) {
			System.out.println("--------------------> 21" +numeroActe);
			LocalDate dateEcheanceCalcule =  acte.getDateActe().plus(1, ChronoUnit.MONTHS);

					calculPenaliteASS.dateEcheance(acte);
			System.out.println("--------------------> 21111111111" +numeroActe);

			String numeroActeP = acte == null ? null : acte.getNumero();
			String ifu = acte.getContribuableBeneficiaire() == null ? null : acte.getContribuableBeneficiaire().getContImmatr();
			String natureActe = acte.getNatureActe() == null ? null : acte.getNatureActe().getDesignation();




			Double ds = acte.getDroitSimple() == null ? 0. : acte.getDroitSimple();

			Double pen = acte.getPenalite() == null ? 0. : acte.getPenalite();
			Double amende = acte.getAmende() == null ? 0. : acte.getAmende();
			Double redevance = acte.getRedevance() == null ? 0. : acte.getRedevance();


			String dateSignature = acte.getDateActe() == null ? null
					: acte.getDateActe().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.FRENCH))
							.toString();

			LocalDate DateEcheanceDate = dateEcheanceCalcule;
			System.out.println("------------------> "+ DateEcheanceDate);
			String dateEcheance = acte == null ? null
					: "Passé le "
							+ DateEcheanceDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.FRENCH))
							+ ", cette fiche de préliquidation ne sera plus valable ";

			Double nbreTimbre = acte.getNombreTimbre() == null ? 0. :  acte.getNombreTimbre();
			Double valeurTimbre = acte.getValeurTimbre() == null ? 0. : acte.getValeurTimbre() ;

			Double timbre = nbreTimbre * valeurTimbre;

			parametersMap.put("ifu", ifu);
			parametersMap.put("natureActe", natureActe);
			parametersMap.put("numeroActe", numeroActeP);
			parametersMap.put("ds", ds);
			parametersMap.put("pen", pen);
			parametersMap.put("amende", amende);
			parametersMap.put("redevance", redevance);

			parametersMap.put("logoMEF", "jasper/images/logo_mef-transparent.png"); 
			parametersMap.put("logoDGI", "jasper/images/logo-left.png");
     		
			parametersMap.put("dateSignature", dateSignature);
			parametersMap.put("dateEcheance", dateEcheance);
			parametersMap.put("timbre", timbre);
		}else {
			System.out.println("Acte est null");
		}
		

		System.out.println("---------------------------->Ok");

		// - Execution du rapport
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);

		// - Création du rapport au format PDF

		System.out.println("--------------------> 2" +numeroActe);

		
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(getUplodsDir() + "/preliquidation_" + numeroActe + ".pdf"));
		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);
		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("QUITTANCE");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + "preliquidation_"+numeroActe + ".pdf\""));
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);

	}
	
	
	
	
	
	
	
	

	@PostMapping(value = "/reportTitrePerception")
	public void reportTitrePerceptionPdf(HttpServletResponse response, @Valid @RequestBody String numeroActe)
			throws Exception, JRException, SQLException {

		List<Map<String, Object>> source = sourcePreliquidation(numeroActe);

		JRDataSource conn = new JRBeanCollectionDataSource(source);

		// - Chargement et compilation du rapport
        InputStream stream = null;
		stream = getStream("jasper/reportEnregistrement/titrePerception.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(stream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		// - Paramètres à envoyer au rapport
		Map<String, Object> parametersMap = new HashMap();

		Acte acte = acteDao.findByNumeroActe(numeroActe);
		if(acte!=null) {
			
			String ifu = acte.getContribuableBeneficiaire() == null ? null : acte.getContribuableBeneficiaire().getContImmatr();
			String numeroActeP = acte.getNumero() == null ? null : acte.getNumero();
			String natureActe = acte.getNatureActe() == null ? null : acte.getNatureActe().getDesignation();

			Double ds = acte.getDroitSimple() == null ? 0. : acte.getDroitSimple();
			Double pen = acte.getPenalite() == null ? 0. : acte.getPenalite();
			Double amende = acte.getAmende() == null ? 0. : acte.getAmende();
			Double redevance = acte.getRedevance() == null ? 0. : acte.getRedevance();

			String dateSignature = acte.getDateActe() == null ? null
					: acte.getDateActe().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.FRENCH))
							.toString();

			LocalDate DateEcheanceDate = acte.getDateActe().plus(1, ChronoUnit.MONTHS);
			String dateEcheance = acte == null ? null
					: "Passé le "
							+ DateEcheanceDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.FRENCH))
							+ ", cette fiche de préliquidation ne sera plus valable ";

			Double nbreTimbre = acte.getNombreTimbre() == null ? 0. :  acte.getNombreTimbre();
			Double valeurTimbre = acte.getValeurTimbre() == null ? 0. : acte.getValeurTimbre() ;

			Double timbre = nbreTimbre * valeurTimbre;

			parametersMap.put("ifu", ifu);
			parametersMap.put("natureActe", natureActe);
			parametersMap.put("numeroActe", numeroActeP);
			parametersMap.put("ds", ds);
			parametersMap.put("pen", 0.);
			parametersMap.put("amende", 0.);
			parametersMap.put("redevance", redevance);
			parametersMap.put("logoMEF", "jasper/images/logo_mef-transparent.png"); 
			parametersMap.put("logoDGI", "jasper/images/logo-left.png");
     		
			parametersMap.put("dateSignature", null);
			parametersMap.put("dateEcheance", null);
			parametersMap.put("timbre", 0.);

			String test = "	Il est dû en République du Bénin (Bureau de l'Enregistrement de Cotonou) "
					+ "par la société immatriculée "
					+ ifu
					+ ", ayant son siège social à "
					+ "adresse"
					+ ", "
					+ " adresse"
					+ ", Bénin , la somme de "
					+ FrenchNumberToWords.convert(ds.intValue()) + " (" + ds + ")"+   " francs CFA,"
					+ " représentant le droit d'enregistrement et la redevance de régulation "
					+ FrenchNumberToWords.convert(redevance.intValue()) + " (" + redevance + ")"+   " francs CFA,"
					+ " des marchés publics dus sur le contrat N° "
					+ numeroActeP
					+ " du "
					+ dateSignature
					+ " relatif au"
					+ "Désignation du marché. \r\n" + 
					"";

			parametersMap.put("montantLettre", test);
			
			String test2 = "	En vertu des dispositions de l'article 304 du code Général des Impôts,"
					+ " le présent titre de perception est établi par nous, Directeur Départementale "
					+ "des Impôts de l'Atlantique et du Littoral (DDI-AL) et le chef du centre de "
					+ "l'enregistrement et du Timbre, en vue d'obtenir le paiement, par les voies "
					+ "de droit, de la somme de "
					+ FrenchNumberToWords.convert(ds.intValue()+redevance.intValue()) + " (" + (ds+redevance) + ")"+   " francs CFA,"
					+ " sur le contrat ci-dessus cité, sans préjudice "
					+ "de celles qui pourraient devenir ou qui seraient ultérieurement reconnues "
					+ "exigibles.";
			
			parametersMap.put("montantLettreB", test2);

			
//			parametersMap.put("montantLettre", "Arrêté la présente quittance à la somme de:  "
//					+ FrenchNumberToWords.convert(ds.intValue()) + "  (" + ds + "F)  " + " CFA");
			
		}else {
			System.out.println("Acte est null");
		}

		

		System.out.println("---------------------------->Ok");

		// - Execution du rapport
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);

		// - Création du rapport au format PDF

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(getUplodsDir() + "/titreperception_" + numeroActe + ".pdf"));
		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);
		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("TitrePerception");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + "titreperception_"+numeroActe + ".pdf\""));
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);

	}
	
	
	
	Integer sommeDetailQuittance = 0;
	
	


	@PostMapping(value = "/quittancePDF")
	public void reportSqlPdf(HttpServletResponse response, @Valid @RequestBody String numeroQuittance)
			throws Exception, JRException, SQLException {




		System.out.println("---------------------------->Deb Quittance");

		List<Map<String, Object>> source = report(numeroQuittance);

		JRDataSource conn = new JRBeanCollectionDataSource(source);

		// - Chargement et compilation du rapport
		 InputStream stream = null;
			stream = getStream("jasper/reportEnregistrement/quittance.jrxml");
//			String chemin = resourceLoader.getResource("classpath:" + "jasper/reportEnregistrement/quittance.jrxml").getFile().getAbsolutePath();
		JasperDesign jasperDesign = JRXmlLoader.load(stream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		// - Paramètres à envoyer au rapport
		Map<String, Object> parametersMap = new HashMap();

		Quittance quit = quittanceDao.findByNumeroActe(numeroQuittance);

		if(quit != null) {

			DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.FRENCH);
			DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
			decimalFormatSymbols.setGroupingSeparator(' ');
			decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
			
			Integer montantPaye = quit.getMontantPaye() == null ? null : quit.getMontantPaye().intValue();
			String ifu = quit.getActeQuittance() == null ? null
					: quit.getActeQuittance().getContribuableBeneficiaire().getContImmatr();
			String modePaiement = quit.getModePaiement() == null ? null : quit.getModePaiement().getDesignation();
			String natureActe = quit.getActeQuittance() == null ? null
					: quit.getActeQuittance().getNatureActe().getDesignation();
//			.getNatureActe()
			String encodeur = quit.getEncodeur() == null ? null : quit.getEncodeur();
			Integer montantdu = quit.getMontantPaye() == null ? null :  quit.getMontantPaye().intValue();
			
			System.out.println(decimalFormat.format(montantdu.doubleValue()));
//			montantdu = decimalFormat.format(montantdu.doubleValue());
			
			
		
			sommeDetailQuittance = 0;
			
			quit.getQuittancesDetail().forEach(detail ->
			
				sommeDetailQuittance = sommeDetailQuittance + detail.getMontantDQ().intValue()
			
			
					);

			parametersMap.put("montantLettre", "Arrêté la présente quittance à la somme de:  "
					+ FrenchNumberToWords.convert(sommeDetailQuittance) + "  (" + decimalFormat.format(sommeDetailQuittance.doubleValue()) + "F)  " + " CFA");
			parametersMap.put("nomCaissier", encodeur);
			parametersMap.put("ifu", ifu);
			parametersMap.put("natureActe", natureActe);
			parametersMap.put("modePaiement", modePaiement); // quit.getModePaiement().getDesignation()
			parametersMap.put("numeroQuittance", numeroQuittance);
			parametersMap.put("montantdu", decimalFormat.format(montantdu.doubleValue()));
			parametersMap.put("montantPaye", decimalFormat.format(montantPaye.doubleValue()));

		}else {
			System.out.println("La quittance n'existe pas");
		}
	
		System.out.println("---------------------------->Ok");

		// - Execution du rapport
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);

		// - Création du rapport au format PDF

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

//		exporter.setExporterOutput(
//				new SimpleOutputStreamExporterOutput(getUplodsDir() + "/quittance_" + numeroQuittance + ".pdf"));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);
		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("QUITTANCE");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"" + "quittance_"+numeroQuittance + ".pdf\""));
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
//		conn.close();

	}




	@GetMapping(value = "/repportSommier/{caseSommier}/{volume}/{folio}/{codeServiceAgent}")
	public @ResponseBody void rapportSommierPDF(HttpServletResponse response, @PathVariable String caseSommier,
			@PathVariable String volume, @PathVariable String folio, @PathVariable String codeServiceAgent) {
		try {

			File file = Paths.get(EXTERNAL_FILE_PATHBis).toFile();

			System.out.println(file.getAbsolutePath() + "------------>" + file.getPath());

			JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
			Map<String, Object> parametersMap = new HashMap<>();

			List<Acte> source = sourceSommier(caseSommier.toUpperCase(), volume.toUpperCase(), folio.toUpperCase(),
					codeServiceAgent.toUpperCase());
			source.forEach(s -> System.out
					.println("------------>" + s.getNumero() + "  " + s.getNatureActe().getCategorieActe().getCode()));
			JRDataSource jRDataSource = new JRBeanCollectionDataSource(source);

			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametersMap, jRDataSource);
			response.setContentType("application/pdf;charset=utf-8");
			response.setHeader("Content-Disposition", "inline: filename-quittance.pdf");

			final OutputStream outputStream = response.getOutputStream();
			System.out.println("---------OutputStream-------" + outputStream.toString());
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		} catch (JRException j) {
			System.out.println(j.getMessage());
			System.out.println("JRException");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Exception");

		}

	}

	@PostMapping("/ajoutActeSansBordereau")
	public Acte ajoutActeSansBordereau(@RequestBody Acte userForm) {

		System.out.println("1111111111111111&");
		Acte userSearch = acteDao.findByIdIs(userForm.getId());
		System.out.println("2222222222222222222222&");
		if (userSearch == null) {
			System.out.println("333333333333333333333&");
			acteDao.saveAndFlush(userForm);
		} else {
			throw new RuntimeException(userSearch + "Exercice inexistant");
		}

		return userForm;

	}
	int inumeroOrdre ;

	

	@PostMapping("/sommier")
	@Transactional
	public List<Acte> sommier(@RequestBody  MentionPayload mentionPayload, HttpServletRequest req) {

		System.out.println("1111111111111111&" + mentionPayload);

		//vérifier que la date mention renseigner n'a pas encore été attribuer a un acte au sommier si c'est le 
		// cas alors le volume case et folio renseigné doivent être égale et le numero d'ordre commence par le dernier
//		de la date concernée à la date d'avant sinon erreur
		String codeService = authAgentApi.whoami(req).getService().getCode();
		List<Acte> actes = getListActeAMentionner(codeService);

		inumeroOrdre = 1;
		if (actes != null) {

			actes.forEach(acte -> {
				acte.setStatutCourant("SOM");

				acte.setVolumeSommierAss(mentionPayload.getVolume());

				acte.setCaseSommierAss(mentionPayload.getCaseMention());
				acte.setFolioSommierAss(mentionPayload.getFolio());
				acte.setDateEnregistrementSommier(mentionPayload.getDateMention());
				acte.setAgentSommier(authAgentApi.whoami(req));
				acte.setNumeroOrdreSommierAss(inumeroOrdre);
				inumeroOrdre ++;
				acteDao.saveAndFlush(acte);

				AvoirStatut avoirStatut = new AvoirStatut();
				avoirStatut.setAgent(acte.getAgentLiquidateur());
				avoirStatut.setNumero(acte.getNumero());
				avoirStatut.setStatutString("SOM");
				avoirStatut.setDesignation(acte.getDesignation());

				avoirStatut.setCommentaire("Acte Sommier");
				avoirStatut.setDateModification(new Date());
				avoirStatut.setDateDebut(new Date());
				avoirStatut.setDateFin(new Date());
				avoirStatutDao.saveAndFlush(avoirStatut);
			});

		} else {
			throw new RuntimeException(actes + "Exercice inexistant");
		}

		return actes;

	}

	@PostMapping("/rejet")
	@Transactional
	public List<Acte> rejet(@RequestBody List<Acte> actes, HttpServletRequest req) {

		System.out.println("1111111111111111&");

		if (actes != null) {

			actes.forEach(acte -> {
				acte.setStatutCourant("ASS");
				
				acte.setAgentRejet(authAgentApi.whoami(req));
				acteDao.saveAndFlush(acte);

				AvoirStatut avoirStatut = new AvoirStatut();
				avoirStatut.setAgent(acte.getAgentLiquidateur());
				avoirStatut.setNumero(acte.getNumero());
				avoirStatut.setStatutString("REJ");
				avoirStatut.setDesignation(acte.getDesignation());

				avoirStatut.setCommentaire("Acte Rejeter");
				avoirStatut.setDateModification(new Date());
				avoirStatut.setDateDebut(new Date());
				avoirStatut.setDateFin(new Date());
				avoirStatutDao.saveAndFlush(avoirStatut);
			});

		} else {
			throw new RuntimeException(actes + "Exercice inexistant");
		}

		return actes;

	}


	@GetMapping("/userConnecte")
	public Agent testUserConnecte(HttpServletRequest req) {
		Agent agent = authAgentApi.whoami(req);
		System.out.println("------------------->"+agent);
		return agent;

	}


	@PostMapping("/approbation")
	@Transactional
	public List<Acte> approbation(@RequestBody List<Acte> actes, HttpServletRequest req) {
		

		System.out.println("1111111111111111&");

		if (actes != null) {

			actes.forEach(acte -> {
				acte.setStatutCourant("APP");

				acte.setAgentValidateurFinal(authAgentApi.whoami(req));
				acteDao.saveAndFlush(acte);

				AvoirStatut avoirStatut = new AvoirStatut();
				avoirStatut.setAgent(acte.getAgentLiquidateur());
				avoirStatut.setNumero(acte.getNumero());
				avoirStatut.setStatutString("APP");
				avoirStatut.setDesignation(acte.getDesignation());

				avoirStatut.setCommentaire("Acte Approuvé");
				avoirStatut.setDateModification(new Date());
				avoirStatut.setDateDebut(new Date());
				avoirStatut.setDateFin(new Date());
				avoirStatutDao.saveAndFlush(avoirStatut);
			});

		} else {
			throw new RuntimeException(actes + "Acte inexistant");
		}

		return actes;

	}

	@PostMapping("/transfert/{numtransfert}")
	public List<Acte> transfert(HttpServletRequest req, @RequestBody List<Acte> actes, @PathVariable String numtransfert) {

		System.out.println("1111111111111111&");

		if (actes != null) {

			actes.forEach(acte -> {
				acte.setStatutCourant("TRV");
				acte.setNumeroTransfert(numtransfert);

				acte.setAgentTransfert(authAgentApi.whoami(req));
				acteDao.saveAndFlush(acte);

				AvoirStatut avoirStatut = new AvoirStatut();
				avoirStatut.setAgent(acte.getAgentTransfert());
				avoirStatut.setNumero(acte.getNumero());
				avoirStatut.setStatutString("TRV");
				avoirStatut.setDesignation(acte.getDesignation());

				avoirStatut.setCommentaire("Acte Transféré");
				avoirStatut.setDateModification(new Date());
				avoirStatut.setDateDebut(new Date());
				avoirStatut.setDateFin(new Date());
				avoirStatutDao.saveAndFlush(avoirStatut);
			});

		} else {
			throw new RuntimeException(actes + "Acte inexistant");
		}

		return actes;

	}
	
	@PostMapping("/ajoutActeContribuable")
	public Acte ajoutActeContribuable(HttpServletRequest req, @Valid @RequestBody ActeWithQuittance object) {

//        ObjectMapper objectMapper = new ObjectMapper();
//        ActeWithQuittance post = objectMapper.readValue(object, ActeWithQuittance.class);
		System.out.println("--------------------------------" + object.getActe() + "--------------------------------"
				+ object.getQuittances());
		Acte userForm = object.getActe();
		List<Quittance> quittances = object.getQuittances();

		System.out.println("---------------------------------Ajout---------------------------------" + userForm);

		if (userForm == null) {
			return null;
		} else {
			

//			Revoir le Numero Acte Contribuable
			
			Long dernierNumero = Long.parseLong(getNumLastActesBordereau("ASSAUTDGICONT".toUpperCase()))+1;

			userForm.setNumero(userForm.getNumero()+ dernierNumero);

			Acte acte = ajoutQuittanceService.ajoutActeContribuable( userForm);
			return acte;
		}

	}

	@PostMapping("/ajoutActeOfficier")
	public Acte ajoutActeOfficier(HttpServletRequest req, @Valid @RequestBody ActeWithQuittance object) {

//        ObjectMapper objectMapper = new ObjectMapper();
//        ActeWithQuittance post = objectMapper.readValue(object, ActeWithQuittance.class);
		System.out.println("--------------------------------" + object.getActe() + "--------------------------------"
				+ object.getQuittances());
		Acte userForm = object.getActe();
		List<Quittance> quittances = object.getQuittances();

		System.out.println("---------------------------------Ajout---------------------------------" + userForm);

		if (userForm == null) {
			return null;
		} else {
	        userForm.setAgentLiquidateur(authAgentApi.whoami(req));
	        
			Acte acte = ajoutQuittanceService.ajoutActeOfficier(userForm, quittances);

			return acte;
		}

	}
	

	@PostMapping("/ajoutActe")
	public Acte ajoutActe(HttpServletRequest req, @Valid @RequestBody ActeWithQuittance object) {

//        ObjectMapper objectMapper = new ObjectMapper();
//        ActeWithQuittance post = objectMapper.readValue(object, ActeWithQuittance.class);
		System.out.println("--------------------------------" + object.getActe() + "--------------------------------"
				+ object.getQuittances());
		Acte userForm = object.getActe();
		List<Quittance> quittances = object.getQuittances();

		System.out.println("---------------------------------Ajout---------------------------------" + userForm);

		if (userForm == null) {
			return null;
		} else {
	        userForm.setAgentLiquidateur(authAgentApi.whoami(req));
			Acte acte = ajoutQuittanceService.ajoutActe( userForm, quittances);

			return acte;
		}

	}

	@PatchMapping("/validationActeAss/{numeroBordereau}")
	public Acte validationActeAss(HttpServletRequest req, @RequestBody Acte acte, @PathVariable String numeroBordereau) {

		System.out.println("-------------------> numeroBordereau " + numeroBordereau);

		Acte acteEn = new Acte();
		BordereauActe bordereauActe = bordActeDao.findByNumeroEquals(numeroBordereau);

		Double totalAmendeAncien = bordereauActe == null ? 0. : bordereauActe.getTotalAmende();

		Double totalPenaliteAncien = bordereauActe == null ? 0. : bordereauActe.getTotalPenalite();

		Double totalTimbreAncien = bordereauActe == null ? 0. : bordereauActe.getTotalTimbre();

		Double totalDroitSimpleAncien = bordereauActe == null ? 0. : bordereauActe.getTotalDS();

		Double totalRedevanceAncien = bordereauActe == null ? 0. : bordereauActe.getTotalRedevance();

		Integer nombreActeAncien = bordereauActe == null ? 0 : bordereauActe.getNbreActe();

		System.out.println("----------> bordereauActe " + bordereauActe);
		if (bordereauActe != null) {
			System.out.println("1");

			bordereauActe.setTotalAmende(totalAmendeAncien + acte.getAmende());
			bordereauActe.setTotalDS(totalDroitSimpleAncien + acte.getDroitSimple());
			bordereauActe.setTotalPenalite(totalPenaliteAncien + acte.getPenalite());
			bordereauActe.setTotalRedevance(totalRedevanceAncien + acte.getRedevance());
			bordereauActe.setNbreActe(nombreActeAncien + 1);
			bordereauActe.setTotalTimbre(totalTimbreAncien + acte.getNombreTimbre() * acte.getValeurTimbre());
			bordereauActe.setId(bordereauActe.getId());

			BordereauActe borActe = bordereauActeDao.saveAndFlush(bordereauActe);

			acte.setBordereauActe(borActe);
			acte.setStatutCourant("VA");
			acte.setAgentValidation(authAgentApi.whoami(req));
			acteEn = acteDao.saveAndFlush(acte);

			AvoirStatut avoirStatut = new AvoirStatut();
			avoirStatut.setAgent(acte.getAgentLiquidateur());
			avoirStatut.setNumero(acte.getNumero());

			avoirStatut.setStatutString("VA");
			avoirStatut.setCommentaire("Acte Validé");
			avoirStatut.setDesignation(acte.getDesignation());
			avoirStatut.setDateModification(new Date());
			avoirStatut.setDateDebut(new Date());
			avoirStatut.setDateFin(new Date());
			avoirStatutDao.saveAndFlush(avoirStatut);

		} else {

			System.out.println("2222222");

			BordereauActe borActe = new BordereauActe();
			BordereauActe borActeRecherche = new BordereauActe();

			borActe.setNumero(numeroBordereau);
			borActe.setTotalAmende(totalAmendeAncien + acte.getAmende());
			borActe.setTotalDS(totalDroitSimpleAncien + acte.getDroitSimple());
			borActe.setTotalPenalite(totalPenaliteAncien + acte.getPenalite());
			borActe.setTotalRedevance(totalRedevanceAncien + acte.getRedevance());
			borActe.setNbreActe(nombreActeAncien + 1);
			borActe.setTotalTimbre(totalTimbreAncien + acte.getNombreTimbre() * acte.getValeurTimbre());

			borActeRecherche = bordereauActeDao.saveAndFlush(borActe);

			acte.setBordereauActe(borActeRecherche);
//			acte.setAgentLiquidateur();
			acte.setAgentValidation(authAgentApi.whoami(req));
			acte.setStatutCourant("VA");
			acteEn = acteDao.saveAndFlush(acte);

			AvoirStatut avoirStatut = new AvoirStatut();
			avoirStatut.setAgent(acte.getAgentLiquidateur());
			avoirStatut.setNumero(acte.getNumero());

			avoirStatut.setStatutString("VA");
			avoirStatut.setCommentaire("Acte Validé");
			avoirStatut.setDesignation(acte.getDesignation());
			avoirStatut.setDateModification(new Date());
			avoirStatut.setDateDebut(new Date());
			avoirStatut.setDateFin(new Date());
			avoirStatutDao.saveAndFlush(avoirStatut);

		}

		return acteEn;

	}

	@DeleteMapping(value = "/deleteActe/{id}")
	public Boolean deleteActe(@PathVariable Long id) {
		// contactRepository.delete(id);

		if (id == 0. || id == null) {
			return null;
		} else {
			Acte acte = acteDao.findByIdIs(id);

			if (acte != null) {
				acteDao.delete(acte);
				return true;
			} else {

				return false;
			}
		}

	}

	@PutMapping(value = "/mergePActe/{numero}")
	public Acte updateAgent(@Valid @RequestBody Acte userForm, @PathVariable String numero) {

		if (userForm.getId() != null) {
			acteDao.save(userForm);
		}

		return userForm;
	}

	@PostMapping(value = "/mergeActe/{numeroBordereau}")
	public Acte updatePartielActe( HttpServletRequest req,  @Valid @RequestBody Acte acte, @PathVariable String numeroBordereau) {

		Acte acteEn = new Acte();
		BordereauActe bordereauActe = bordActeDao.findByNumeroEquals(numeroBordereau);

		Double totalAmendeAncien = bordereauActe.getTotalAmende() == null ? 0. : bordereauActe.getTotalAmende();

		Double totalPenaliteAncien = bordereauActe.getTotalPenalite() == null ? 0. : bordereauActe.getTotalPenalite();

		Double totalTimbreAncien = bordereauActe.getTotalTimbre() == null ? 0. : bordereauActe.getTotalTimbre();

		Double totalDroitSimpleAncien = bordereauActe.getTotalDS() == null ? 0. : bordereauActe.getTotalDS();

		Double totalRedevanceAncien = bordereauActe.getTotalRedevance() == null ? 0.
				: bordereauActe.getTotalRedevance();

		Integer nombreActeAncien = bordereauActe.getNbreActe() == null ? 0 : bordereauActe.getNbreActe();

		System.out.println("----------> bordereauActe " + bordereauActe);
		if (bordereauActe != null) {

			Acte acteRecherche = acteDao.findByIdIs(acte.getId());

			bordereauActe.setTotalAmende(totalAmendeAncien - acteRecherche.getAmende() + acte.getAmende());
			bordereauActe.setTotalDS(totalDroitSimpleAncien - acteRecherche.getDroitSimple() + acte.getDroitSimple());
			bordereauActe.setTotalPenalite(totalPenaliteAncien - acteRecherche.getPenalite() + acte.getPenalite());
			bordereauActe.setTotalRedevance(totalRedevanceAncien - acteRecherche.getRedevance() + acte.getRedevance());

			bordereauActe.setTotalTimbre(
					totalTimbreAncien - (acteRecherche.getNombreTimbre() * acteRecherche.getValeurTimbre())
							+ (acte.getNombreTimbre() * acte.getValeurTimbre()));
			bordereauActe.setId(bordereauActe.getId());

			BordereauActe borActe = bordereauActeDao.saveAndFlush(bordereauActe);

			acte.setBordereauActe(borActe);
			acte.setAgentLiquidateur(authAgentApi.whoami(req));
			acteEn = acteDao.saveAndFlush(acte);

			AvoirStatut avoirStatut = new AvoirStatut();
			avoirStatut.setAgent(acte.getAgentLiquidateur());
			avoirStatut.setNumero(acte.getNumero());

			avoirStatut.setStatutString("VA");
			acte.setAgentValidation(authAgentApi.whoami(req));

			avoirStatut.setCommentaire("Acte Validé");
			avoirStatut.setDesignation(acte.getDesignation());
			avoirStatut.setDateModification(new Date());
			avoirStatut.setDateDebut(new Date());
			avoirStatut.setDateFin(new Date());
			avoirStatutDao.saveAndFlush(avoirStatut);

		} else {
			System.out.println("Bordereau de l'Acte inexistant");
		}

		return acteEn;

	}

}
