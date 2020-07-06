package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.ActeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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








	@GetMapping(value = "/dateServeur")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public LocalDate getDateServeur() {
		return LocalDate.parse(LocalDate.now().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH));
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
