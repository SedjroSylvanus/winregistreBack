package com.dgi.dsi.winregistre.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.entites.Service;
import com.dgi.dsi.winregistre.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dgi.dsi.winregistre.dao.AgentDao;

import com.dgi.dsi.winregistre.entites.Agent;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;


import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@CrossOrigin("*")


public class AgentApi {

//	@Autowired
//	private agentDao agentDao;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ActeDao acteDao;

	@Autowired
	private CalculDroit calculDroit;

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

//	private JourFerieService jourFerie = new JourFerieService();


//    @GetMapping(value = "/isSamedi/{date}")
//    public String isSamedi(@PathVariable String date) {
//        List<Acte> actes = acteDao.findAll();
//        for(int i=0; i< actes.size();i++){
//            System.out.println(actes.get(i).getNumero());
//        }
//        LocalDate dateF = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH));
//
//        System.out.println(dateF);
//        if((dateF.format(DateTimeFormatter.ofPattern("EEEE", Locale.FRENCH))).equals("samedi")){
//            return "Samedi";
//        }else{
//            return "Non Samedi";
//        }

//    	Log.info("<<<<<<<<<<--est Samedi--->>>>>>>>>>>><"+jourFerie.isSamedi(date));
//	    return jourFerie.isSamedi(date);
//    }




	@GetMapping(value = "/serviceAgent/{matricule}")
	public Service serviceAgent(@PathVariable String matricule) {
		// contactRepository.delete(id);

		Service service = (agentDao.findByMatriculeEquals(matricule)).getService();


		return service;

	}

//    @GetMapping(value = "/etatAgent")
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
//    public ModelAndView getEtatAgent(ModelMap modelMap) {
//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setUrl("classpath:/reprot/report.jrxml");
//        view.setApplicationContext(applicationContext);
//         Map<String, Object> params = new HashMap<>();
//         params.put("datasource", agentDao.findAll());
//
//
//         return new ModelAndView(view, params);
//    }



    @GetMapping(value = "/listAgents")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public List<Agent> getAgents() {
		return agentDao.findAll();
	}

    @GetMapping(value = "/listAgents/{matricule}")
    public Agent getAgents(@PathVariable String matricule) {
	    return agentDao.findByMatricule(matricule);
    }

    @PostMapping("/ajoutAgent")
	public Agent ajoutAgent( @Valid @RequestBody Agent userForm) {


			userService.signup(modelMapper.map(userForm, Agent.class));
		return userForm;

	}

	@DeleteMapping(value = "/deleteAgent/{id}")
	public Boolean deleteAgent(@PathVariable Long id) {
		// contactRepository.delete(id);

		Agent exercice = agentDao.findOne(id);

		if (exercice != null) {
			agentDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePAgent/{matricule}")
	public Agent updateAgent( @Valid @RequestBody Agent userForm,
                              @PathVariable String matricule) {

        Agent exercice = agentDao.findByMatricule(matricule);
        if (exercice != null) {

            userForm.setId(exercice.getId());
            agentDao.saveAndFlush(userForm);

        } else {

            agentDao.saveAndFlush(exercice);
        }
        return exercice;
	}

	@PatchMapping(value = "/mergeAgent")
//	@PatchMapping(value = "/mergeAgent/{matricule}")
	public Agent updatePartielAgent( @Valid @RequestBody Agent userForm) {
		//,
//                                     @PathVariable String matricule) {

        Agent agent = agentDao.findByMatricule(userForm.getMatricule());
//        System.out.println(agent.size());
        if (agent != null) {

            userForm.setId(agent.getId());
            userForm.setPassword(agent.getPassword());
            agentDao.saveAndFlush(userForm);

        } else {

            agentDao.saveAndFlush(agent);
        }
        return agent;
	}




}
