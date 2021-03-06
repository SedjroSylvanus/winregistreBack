package com.dgi.dsi.winregistre.service.serviceIreport;

import com.dgi.dsi.winregistre.entites.modelIreport.OrderModel;

//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
//import org.springframework.ui.jasperreports.JasperReportsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class InvoiceService {

//    Logger log = LogManager.getLogger(InvoiceService.class);
//
//    private static final String logo_path = "/jasper/images/stackextend-logo.png";
//    private final String invoice_template = "/jasper/invoice_template.jrxml";
//    private final Path rootLocation = Paths.get("C:\\Users\\Admin\\Documents\\Aatout");
//
//
//    public void generateInvoiceFor(OrderModel order, Locale locale) throws IOException {
//
//        File pdfFile = File.createTempFile("Quittance", ".pdf", rootLocation.toFile());
//
//        log.info(String.format("Le Chemin du fichier à télécharger : %s", pdfFile.getAbsolutePath()));
//
//        try(FileOutputStream pos = new FileOutputStream(pdfFile))
//        {
//            // Load invoice jrxml template.
//            final JasperReport jrxmlFichier = loadTemplate();
//
//            // Create parameters map.
//            final Map<String, Object> parameters = parameters(order, locale);
//
//            // Create an empty datasource.
//            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Winregistre"));
//
//            // Render as PDF.
//            JasperReportsUtils.renderAsPdf(jrxmlFichier, parameters, dataSource, pos);
//
//        }
//        catch (final Exception e)
//        {
//            log.error(String.format("An error occured during PDF creation: %s", e));
//        }
//    }
//
//    // Fill template order parametres
//    private Map<String, Object> parameters(OrderModel order, Locale locale) {
//        final Map<String, Object> parameters = new HashMap<>();
//
//        parameters.put("logo", getClass().getResourceAsStream(logo_path));
//        parameters.put("order",  order);
//        parameters.put("REPORT_LOCALE", locale);
//
//        return parameters;
//    }
//
//    // Load invoice jrxml template
//    private JasperReport loadTemplate() throws JRException {
//
//        log.info(String.format("loadTemplate --------> : %s", invoice_template));
//
//        final InputStream reportInputStream = getClass().getResourceAsStream(invoice_template);
//        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
//
//        return JasperCompileManager.compileReport(jasperDesign);
//    }

}
