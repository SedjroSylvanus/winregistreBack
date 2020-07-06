package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.*;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.payload.EntrePaiement;
import com.dgi.dsi.winregistre.payload.SortiePaiement;


import com.dgi.dsi.winregistre.service.InvoiceService;
import com.github.royken.converter.FrenchNumberToWords;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.ResourceLoader;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;


//import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
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
	private BordereauActeDao bordereauActeDao;

	@Autowired
	private ContribuableDao contribuableDao;

	@Autowired
	private DepartementDao departementDao;


    @Autowired
    private ResourceLoader resourceLoader;

    private static final String EXTERNAL_FILE_PATH = "C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\jasper\\report\\quittance.jrxml";
//    private static final String EXTERNAL_FILE_PATH = "C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\jasper\\invoice_template.jrxml";


    public JasperPrint exportPdfFile() throws SQLException, JRException, IOException {
//        Connection conn = jdbcTemplate.getDataSource().getConnection();

        String path = resourceLoader.getResource("C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\jasper\\invoice_template.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        // Parameters for reportOld
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report());

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return print;
    }

    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (ContribuableBis product : contribuableDao.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", product.getId());
            item.put("name", product.getCONT_IMMATR());
            item.put("price", product.getCONT_NOM());
            item.put("quantity", product.getCONT_PREN());
            item.put("categoryName", product.getCONT_RAIS());
            result.add(item);
        }
        return result;
    }





    @Resource
    private InvoiceService invoiceService;


//    @RequestMapping(value = "/export", method = RequestMethod.POST)
//    public void export() throws IOException, JRException, SQLException {
//
//        System.out.println("Start invoice generation...");
//
//        OrderModel order = orderService.getOrderByCode("XYZ123456789");
//
//        invoiceService.generateInvoiceFor(order, Locale.FRANCE);
//
//
//
//    }



    @RequestMapping(value = "/quittancePDF", method = RequestMethod.GET)
    public @ResponseBody void contribPDF(HttpServletResponse response) {
        try{

                File file = Paths.get(EXTERNAL_FILE_PATH).toFile();

            System.out.println(file.getAbsolutePath()+"------------>"+file.getPath());

            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
            Map<String, Object> parametersMap = new HashMap<>();
            List<Departement> departements = departementDao.findAll();


            JRDataSource jRDataSource = new JRBeanCollectionDataSource(departements);

            parametersMap.put("datasource",jRDataSource);
            parametersMap.put("montantLettre", "Arrêté la présente quittance à la somme de:  "+FrenchNumberToWords.convert(1955354450)+" (1955354450) FCFA");
            parametersMap.put("nomCaissier", "Tolodé");

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametersMap, jRDataSource);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline: filename-quittance.pdf");

            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        }
        catch (JRException j){
            System.out.println(j.getMessage());
            System.out.println("JRException");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Exception");

        }
    }


    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export( HttpServletResponse response) throws IOException, JRException, SQLException {
        JasperPrint jasperPrint = null;

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));

        OutputStream out = response.getOutputStream();
        jasperPrint = exportPdfFile();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }



    @GetMapping(value = "/listActeBordereau/{numBordereau}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<Acte> getlistActesBordereau(@PathVariable String numBordereau) {

        BordereauActe bordereauActe0 = bordereauActeDao.findByNumeroEquals(numBordereau);
//        List<Acte> listActe = acteDao.findOne(bordereauActe0.getId());

        return acteDao.findByBordereauActeEquals(bordereauActe0.getId());
    }


    @GetMapping(value = "/lastNumberActesBordereau/{numBordereau}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public String getNumLastActesBordereau(@PathVariable String numBordereau) {

        System.out.println("----------------numBordereau-------------------"+ numBordereau);

        BordereauActe bordereauActe0 = bordereauActeDao.findByNumeroEquals(numBordereau);

        if (bordereauActe0 != null){
            System.out.println("-----------bordereauActe0.getId()----------"+ bordereauActe0.getId());
            Long idBordereauActe = acteDao.maxIdByBordereau(bordereauActe0.getId());
//        idBordereauActe = idBordereauActe==null ?1L: idBordereauActe;
            System.out.println("----------------idBordereauActe-------------------"+idBordereauActe);

             if (idBordereauActe != null){
                 Acte bordereauActe = acteDao.findOne(idBordereauActe);

                 String[] decoupe = bordereauActe.getNumero().split("_");

                 System.out.println(bordereauActe.getNumero());
                 return decoupe[1];
             }else{
            return null;
        }

        }else{
            return null;
        }



    }



    @GetMapping(value = "/listActes")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Acte> getActes() {

		return acteDao.findAll();
	}




    @PostMapping("/calculPaiementAss")
	public SortiePaiement calculPaiementAss( @RequestBody Acte userForm) {


		return calculDroit.calculPaiement(userForm);

	}

    @PostMapping("/ajoutActe")
	public Acte ajoutActe( @Valid @RequestBody Acte userForm) {
		System.out.println("------------AJOUT ACTE-----------------" + userForm);

		Acte userSearch = acteDao.findOne(userForm.getId());

        System.out.println("Ajou");
//        System.out.println(userForm.getContribuableBeneficiaire());

        if (userSearch == null) {
            acteDao.saveAndFlush(userForm);
        } else {
            throw new RuntimeException(userSearch + "Exercice inexistant");
        }

        return userForm;


//		if (userSearch == null) {
//
////			Date date = new Date();
////			userForm.setPenalite(calculDroit.calculPaiement(userForm).getPenalite());
////			userForm.setAmende(calculDroit.calculPaiement(userForm).getAmende());
////			userForm.setDroitSimple(calculDroit.calculPaiement(userForm).getDroitSimple());
////			userForm.setManqueeGain(calculDroit.calculPaiement(userForm).getManqueeGain());
////			userForm.setRedevance(calculDroit.calculPaiement(userForm).getRedevance());
////			userForm.setDateActe(LocalDate.parse(userForm.getDateActe().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
////			userForm.setDateEnregistrement(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
////			Double mtDu = (Double) calculDroit.calculPaiement(userForm).getAmende()+calculDroit.calculPaiement(userForm).getDroitSimple()+
////					calculDroit.calculPaiement(userForm).getRedevance()+ calculDroit.calculPaiement(userForm).getPenalite();
////			userForm.setMontantDu(mtDu);
////			userForm.setMontantPaye(0.0);
//
//
//
////			workingOnController.addTache(new WorkingOn(userForm, LocalDate.now(),userForm.getAgentLiquidateur()));
////			System.out.println(workingOnController.getAll());
//			acteDao.saveAndFlush(userForm);
//		} else {
//
//			userForm.setId(userForm.getId());
//			userForm.setPenalite(calculDroit.calculPaiement(userForm).getPenalite());
//			userForm.setAmende(calculDroit.calculPaiement(userForm).getAmende());
//			userForm.setDroitSimple(calculDroit.calculPaiement(userForm).getDroitSimple());
//			userForm.setManqueeGain(calculDroit.calculPaiement(userForm).getManqueeGain());
//			userForm.setRedevance(calculDroit.calculPaiement(userForm).getRedevance());
//			userForm.setDateActe(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
//			userForm.setDateEnregistrement(LocalDate.parse(userForm.getDateActe().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH)));
//
////			workingOnController.addTache(new WorkingOn(userForm, LocalDate.now(),userForm.getAgentLiquidateur()));
//			System.out.println(workingOnController.getAll());
//			Double mtDu = (Double) calculDroit.calculPaiement(userForm).getAmende()+calculDroit.calculPaiement(userForm).getDroitSimple()+
//					calculDroit.calculPaiement(userForm).getRedevance()+ calculDroit.calculPaiement(userForm).getPenalite();
//			Double mtPaye = mtDu - userForm.getMontantPaye();
//			userForm.setMontantDu(mtPaye);
//
//
//			acteDao.saveAndFlush(userForm);
//			throw new RuntimeException(userSearch + "Acte inexistant");
//		}
//
//		return userForm;

	}

	@DeleteMapping(value = "/deleteActe/{id}")
	public Acte deleteActe(@PathVariable Long id) {
		// contactRepository.delete(id);

		Acte acte = acteDao.findOne(id);

		if (acte != null) {

			return acte;
		} else {

			return null;
		}

	}

	@PutMapping(value = "/mergePActe/{numero}")
	public Acte updateAgent( @Valid @RequestBody Acte userForm,
                              @PathVariable String numero) {

        Acte acte = acteDao.findByNumeroEquals(numero);
        if (acte != null) {

            userForm.setId(acte.getId());
            acteDao.saveAndFlush(userForm);

        } else {

            acteDao.saveAndFlush(acte);
        }
        return acte;
	}

	@PatchMapping(value = "/mergeActe")
//	@PatchMapping(value = "/mergeAgent/{matricule}")
	public Acte updatePartielActe( @Valid @RequestBody Acte userForm) {


        Acte acte = acteDao.findByNumeroEquals(userForm.getNumero());

        if (acte != null) {

            userForm.setId(acte.getId());
            acteDao.saveAndFlush(userForm);

        } else {

            acteDao.saveAndFlush(acte);
        }
        return acte;
	}







}
