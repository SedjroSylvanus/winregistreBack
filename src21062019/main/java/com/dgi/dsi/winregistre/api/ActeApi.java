package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.*;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.payload.EntrePaiement;
import com.dgi.dsi.winregistre.payload.SortiePaiement;

//import org.jfree.util.Log;
import com.github.royken.converter.FrenchNumberToWords;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
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
	private BordereauActeDao bordActeDao;
	@Autowired
	private BordereauCaisseDao bordereauCaisseDao;
	@Autowired
	private QuittanceDao quittanceDao;
	@Autowired
	private DepartementDao departementDao;

	@Autowired
	private ContribuableDao contribuableDao;

//	private static final String EXTERNAL_FILE_PATH = "/home/dgi/winregistre/src/main/resources/jasper/reportQuittance/quittance.jrxml";
//	private static final String EXTERNAL_FILE_PATH = "C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\jasper\\report\\quittance.jrxml";
	private static final String EXTERNAL_FILE_PATH = "C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\jasper\\reportQuittance\\quittance.jrxml";




    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Quittance quittance : quittanceDao.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("numeroQuittance", quittance.getNumeroQuittance());
            item.put("dateQuittance", quittance.getDateQuittance());
            item.put("montantPaye", quittance.getMontantPaye());
            item.put("montantPaye", quittance.getMontantPaye());
            item.put("montantPaye", quittance.getMontantPaye());
            item.put("quittancesDetail", quittance.getQuittancesDetail());
            result.add(item);
        }
        return result;
    }




    @Autowired
    private ApplicationContext appContext;


    @GetMapping(value = "report")
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report());


//        String path = getServletContext().getRealPath("jasper/invoice_template.jrxml");
        InputStream input = new FileInputStream(new File("C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\jasper\\reportQuittance\\quittance.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


//        InputStream inputStream = this.getClass().getResourceAsStream("jasper/invoice_template.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }




    @GetMapping(value = "/listActesContribuables")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<Acte> getActesContribuable() {



        List<Acte> actes = acteDao.getActeContribuables(false);


            return actes;
    }

    @GetMapping(value = "/listActesAquittancer")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<Acte> getActesAquittancer() {



        List<Acte> actes = acteDao.getActeQuittancer(0.);


            return actes;
    }




    @GetMapping(value = "/oneBordereauActe/{numeroActe}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public BordereauActe getBordereauActe(@PathVariable String numeroActe) {


        System.out.println(numeroActe);

        Acte acte = acteDao.findByNumeroActe(numeroActe);
        if(acte != null) {
            BordereauActe bordActe0 = bordActeDao.findOne(acte.getBordereauActe().getId());


            if (bordActe0 == null) {
                return null;
            } else {
                System.out.println(bordActe0);
                return bordActe0;
            }
        }else{
            return null;
        }
//        List<Acte> listActe = acteDao.findOne(bordActe0.getId());


    }
    @GetMapping(value = "/oneActeByNumero/{numeroActe}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public Acte getActeByNumero(@PathVariable String numeroActe) {

        System.out.println(numeroActe);

        Acte acte = acteDao.findByNumeroActe(numeroActe);
        return acte;

    }


    @GetMapping(value = "/listActeBordereau/{numBordereau}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<Acte> getlistActesBordereau(@PathVariable String numBordereau) {


        System.out.println(numBordereau);

        BordereauActe bordActe0 = bordActeDao.findByNumeroEquals(numBordereau);


        if(bordActe0 ==null){
            return null;
        }else{
            return acteDao.listActeBordereau(bordActe0.getId());
        }

//        List<Acte> listActe = acteDao.findOne(bordActe0.getId());


    }



    @GetMapping(value = "/lastNumberActesBordereau/{numBordereau}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public String getNumLastActesBordereau(@PathVariable String numBordereau) {

        System.out.println("----------------numBordereau-------------------"+ numBordereau);

        BordereauActe bordActe0 = bordActeDao.findByNumeroEquals(numBordereau);

        if (bordActe0 != null){
            System.out.println("-----------bordActe0.getId()----------"+ bordActe0.getId());
            Long idBordereauActe = acteDao.maxIdByBordereau(bordActe0.getId());
//        idBordereauActe = idBordereauActe==null ?1L: idBordereauActe;
            System.out.println("----------------idBordereauActe-------------------"+idBordereauActe);

             if (idBordereauActe != null){
                 Acte bordActe = acteDao.findOne(idBordereauActe);

                 String[] decoupe = bordActe.getNumero().split("_");

                 System.out.println(bordActe.getNumero());
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
	public SortiePaiement calculPaiementAss( @RequestBody Acte userForm) throws Exception{


		return calculDroit.calculPaiement(userForm);

	}

	@RequestMapping(value = "/quittancePDF", method = RequestMethod.GET)
    public @ResponseBody void quittancePDF(HttpServletResponse response) {
        try {

////            Map params = new HashMap();
////            params.put("nom", "HOUNNOUKON");
//            File jasper = new File(getClass().getClassLoader().getResource("C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\jasper\\report\\quittance.jasper").getFile()); //.replace("/C:", "C:"));
//
//            List<Departement> departements = departementDao.findAll();
//
//            JRDataSource jRDataSource = new JRBeanCollectionDataSource(departements);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, jRDataSource);
////            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            response.setContentType("application/pdf;charset=utf-8");
//
//            ServletOutputStream outputStream = response.getOutputStream();
//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//            outputStream.flush();
//            outputStream.close();
////            FacesContext.getCurrentInstance().responseComplete();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//





                File file = Paths.get(EXTERNAL_FILE_PATH).toFile();

            System.out.println(file.getAbsolutePath()+"------------>"+file.getPath());

            JasperReport report = JasperCompileManager.compileReport(file.getPath());
            Map<String, Object> parametersMap = new  HashMap<>();
            List<Quittance> departements = quittanceDao.findAll();

//            departements.get(0).getc


            JRDataSource jRDataSource = new JRBeanCollectionDataSource(departements);

            Integer montant =  departements.get(0).getMontantPaye().intValue();
            String nomCaissier = departements.get(0).getEncodeur();
//            String ifu = departements.get(0).getActeQuittance().getContribuableBeneficiaire().getCONT_IMMATR();
//            String modePaiement = departements.get(0).getActeQuittance().get();
            String numeroQuittance = departements.get(0).getNumeroQuittance();

            parametersMap.put("datasource",jRDataSource);
            parametersMap.put("montantLettre", "Arrêté la présente quittance à la somme de:  "+ FrenchNumberToWords.convert(montant)  + ((montant))+" FCFA");
            parametersMap.put("nomCaissier", nomCaissier);
//            parametersMap.put("ifu", ifu);
//            parametersMap.put("ifu", ifu);
            parametersMap.put("numeroQuittance", numeroQuittance);

            System.out.println("--------------------"+parametersMap.get("montantLettre"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametersMap, jRDataSource);
            response.setContentType("application/pdf;charset=utf-8");
            response.setHeader("Content-Disposition", "inline: filename-quittance.pdf");

            final OutputStream outputStream = response.getOutputStream();
            System.out.println("---------OutputStream-------"+outputStream);
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



    @PostMapping("/ajoutActe")
	public Acte ajoutActe( @Valid @RequestBody Acte userForm) {
		System.out.println("------------AJOUT ACTE-----------------" + userForm.getId());


        System.out.println("userForm.getBordActe()------------"+userForm.getBordereauActe());
		    if (userForm.getBordereauActe() != null) {
                BordereauActe bordActe = bordActeDao.findByNumeroEquals(userForm.getBordereauActe().getNumero());

                System.out.println("----------Ajout Acte-------------------");
                userForm.setBordereauActe(bordActe);
                    acteDao.saveAndFlush(userForm);
                } else {
                    throw new RuntimeException(userForm + "Bordereau inexistant");
                }


        return userForm;


//

	}

	@DeleteMapping(value = "/deleteActe/{id}")
	public Boolean deleteActe(@PathVariable Long id) {
		// contactRepository.delete(id);

		Acte acte = acteDao.findOne(id);

        if (acte != null) {
            acteDao.delete(acte);
            return true;
        }else {

            return  false;
        }

    }

	@PutMapping(value = "/mergePActe/{numero}")
	public Acte updateAgent( @Valid @RequestBody Acte userForm,
                              @PathVariable String numero) {

//        Acte acte = acteDao.findByNumeroEquals(numero);
//        if (acte != null) {
//
//            userForm.setId(acte.getId());
//            acteDao.saveAndFlush(userForm);
//
//        } else {
//
//            acteDao.saveAndFlush(acte);
//        }
        if(userForm.getId()!=null){
             acteDao.save(userForm);
        }


        return userForm;
	}

	@PatchMapping(value = "/mergeActe")
//	@PatchMapping(value = "/mergeAgent/{matricule}")
	public Acte updatePartielActe( @Valid @RequestBody Acte userForm) {


        if(userForm.getId()!=null){
            acteDao.save(userForm);
        }


        return userForm;
	}







}
