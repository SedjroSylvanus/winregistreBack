package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;
import com.dgi.dsi.winregistre.dao.TypePenaliteAmendeDao;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.Departement;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
import com.dgi.dsi.winregistre.payload.ApiResponse;
import com.dgi.dsi.winregistre.service.ZXingHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.text.Normalizer.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

//import org.jfree.util.Log;

@RestController
@CrossOrigin("*")
public class ImportFichierExcelApi {

//	@Autowired
//	private agentDao agentDao;

	@Autowired
	private ActeDao acteDao;

    @PostMapping("/importExcel")
    public ResponseEntity<?> mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {

        PrintWriter writer = new PrintWriter("C:\\Users\\Admin\\Documents\\dgiprojet\\win\\winregistre\\src\\main\\resources\\test.txt");

        List<Departement> tempStudentList = new ArrayList<>();
        Departement tempStudent = new Departement();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);


        String code = (worksheet.getRow(0).getCell(0)).toString();
        String libelle = (worksheet.getRow(0).getCell(1)).toString();
        if(!code.toUpperCase().equals("code".toUpperCase())){
            System.out.println("Premiere Cellule non conforme. Vérifier s'il y a libellé");
            return new ResponseEntity(new ApiResponse(false, "Premiere Cellule non conforme. Vérifier s'il y a libellé!!"),
                    HttpStatus.BAD_REQUEST);
        }
        if(!libelle.toUpperCase().equals("libelle".toUpperCase())){
            System.out.println("Deuxieme Cellule non conforme. Vérifier s'il y a libellé");
            return new ResponseEntity(new ApiResponse(false, "Deuxieme Cellule non conforme. Vérifier s'il y a libellé!!"),
                    HttpStatus.BAD_REQUEST);
        }

        for(int i=1; i<worksheet.getPhysicalNumberOfRows() ;i++) {
            try{

                XSSFRow row = worksheet.getRow(i);

                tempStudent.setId(i+0L);
                tempStudent.setCode(((Double) row.getCell(0).getNumericCellValue()).toString());
                tempStudent.setDesignation(row.getCell(1).getStringCellValue());
                tempStudentList.add(tempStudent);
            }catch (Exception e){
                System.out.println("Veuillez revoir la ligne ===============> "+i+" du fichier "+reapExcelDataFile.getOriginalFilename());
            //Voir comment écrire dans un fichier txt les erreurs  a uploader
                System.out.println(e.getMessage());

                writer.println("Veuillez revoir la ligne ===============> "+i+" " +
                        "du fichier "+reapExcelDataFile.getOriginalFilename()+"// "+tempStudent);


            }

        }

        writer.close();

        return ResponseEntity.ok().body(tempStudentList);
    }





}
