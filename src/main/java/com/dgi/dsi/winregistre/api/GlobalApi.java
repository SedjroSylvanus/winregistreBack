package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;
import com.dgi.dsi.winregistre.dao.TypePenaliteAmendeDao;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;
import com.dgi.dsi.winregistre.service.JourFerieService;
import com.dgi.dsi.winregistre.service.ZXingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
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
	private CalculPenaliteASS calculPenaliteASS;

	@Autowired
	private WorkingOnController workingOnController;

	@Autowired
	private NatureActeDao natureActeDao;

	@Autowired
	private TypePenaliteAmendeDao typePenaliteAmendeDao;

	@Autowired
	private JourFerieService jourFerieService;

	@RequestMapping(value = "qrcode/{id}", method = RequestMethod.GET)
	public void qrcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getQRCodeImage(id, 200, 200));
		outputStream.flush();
		outputStream.close();
	}

	@GetMapping(value = "/getActeByNumeroActe/{numeroActe}")
	public Long getActeByNumeroActe(@PathVariable String numeroActe) {

		return acteDao.getActeByNumeroEquals(numeroActe.toUpperCase()).getId();
	}

	@GetMapping(value = "/getActeNumeroByNumero/{numeroActe}")
	public Acte getActeNumeroByNumero(@PathVariable String numeroActe) {

		return acteDao.getActeByNumeroEquals(numeroActe.toUpperCase());
	}

	@GetMapping(value = "/getActeNumeroActe/{numeroActe}")
	public Acte getActeNumeroActe(@PathVariable String numeroActe) {

		try {
			// acte = new Acte();

			System.out.println("-------------> Debut");
			Acte acte = acteDao.getActeByNumeroEquals(numeroActe.toUpperCase());
			System.out.println("----------> acte"+ acte);


			return acte;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("------>" + e.getMessage());
			e.printStackTrace();
			return null;

		}

	}

	@GetMapping(value = "/dateServeur")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
//    ResponseEntity<?>
	public ResponseEntity<?> getDateServeur() {

//		return LocalDate.parse(LocalDate.now().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.FRANCE));
		Map<String, String> map = new HashMap();
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

		map.put("dateServeur", dt1.format(new Date()));

		return ResponseEntity.ok().body(map);

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

		// JourFerieService testFonctionJF = new JourFerieService();
		return jourFerieService.isFerie(date);
	}

}
