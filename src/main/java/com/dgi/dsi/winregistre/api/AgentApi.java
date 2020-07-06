package com.dgi.dsi.winregistre.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.payload.ApiResponse;
import com.dgi.dsi.winregistre.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.http.HttpStatus;


import com.dgi.dsi.winregistre.dao.AgentDao;

import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
//import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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


	@Autowired
	private AuthAgentApi authAgentApi;



	@GetMapping(value = "/serviceAgent/{matricule}")
	public Service serviceAgent(@PathVariable String matricule) {
		// contactRepository.delete(id);

		Service service = (agentDao.findByMatriculeEquals(matricule)).getService();


		return service;

	}





	@GetMapping(value = "/lastNumTransfertActeAgent/{indiceAgent}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public ResponseEntity<?> getNumTransfertActeAgent(@PathVariable String indiceAgent) {

		System.out.println("----------------indiceAgent-------------------"+ indiceAgent);
		Agent agent = agentDao.findByIndiceAgentEquals(indiceAgent.toUpperCase());
		if (agent != null){
			System.out.println("-----------agent.getId()----------"+ agent.getId());
			Long idActe = acteDao.maxIdByAgentLiquidateur(agent.getId());
			System.out.println("----------------idBordereauActe-------------------"+idActe);

			Map<String, String> map = new HashMap<>();

			if (idActe != null){
				Acte acte = acteDao.findByIdIs(idActe);
				map.put("numeroTransfert", acte.getNumeroTransfert());
				return ResponseEntity.ok().body(map);
			}else{
				return new ResponseEntity(new ApiResponse(false, "ID de l'acte est null!!"),
						HttpStatus.OK);

			}

		}else{
			return new ResponseEntity(new ApiResponse(false, "Agent recherché n'existe pas !"),
					HttpStatus.BAD_REQUEST);

		}

	}


	@GetMapping(value = "/listActeAtransfererAgentAA/{matriculeAgent}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public ResponseEntity<?> getActeAtransfererAgentAA(@PathVariable String matriculeAgent) {
		// Vérifion pour savoir si un acte est à transférer
		System.out.println("----------------numBordereau-------------------" + matriculeAgent);
		Agent agent = agentDao.findByMatriculeEquals(matriculeAgent);
		if (agent != null) {
			System.out.println("-----------agent.getId()----------" + agent.getId());
			List<Acte> actes = acteDao.listeActeAgentTransfert(0.,"AUT%", agent.getId());

			return  ResponseEntity.ok().body(actes);

		}else{



			return new ResponseEntity(new ApiResponse(false, "Ce matricule ne correspond à aucun agent!!"),
						HttpStatus.BAD_REQUEST);


		}

	}
	

	@GetMapping(value = "/listActeAtransfererAgent")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public ResponseEntity<?> getActeAtransfererAgent( HttpServletRequest req) {
		// Vérifion pour savoir si un acte est à transférer
		String matricule = authAgentApi.whoami(req).getMatricule();
		System.out.println("----------------numBordereau-------------------" + matricule );



			List<Acte> actes = acteDao.listeActeAgentTransfert(0.,"ASS%", authAgentApi.whoami(req).getId());

			return  ResponseEntity.ok().body(actes);



	}


//	@GetMapping(value = "/etatAgent")
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

		Agent exercice = agentDao.findByIdIs(id);

		if (exercice != null) {
			agentDao.delete(exercice);
			return true;
		}else {

			return  false;
		}


	}
	
	@PostMapping(value = "/mergeAgentB")
	public Agent updateAgentB( @Valid @RequestBody Agent userForm){


		System.out.println("Merge Agent--------------> "+ userForm);
        Agent agent = agentDao.findByMatricule(userForm.getMatricule());
        if (agent != null) {

            userForm.setId(agent.getId());
            System.out.println("---------------------> " + userForm);
            userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));

            agent = agentDao.saveAndFlush(userForm);
        }

        return agent;
	}

	

	  @Autowired
	  private PasswordEncoder passwordEncoder;
	
	@PutMapping(value = "/mergeAgent")
	public Agent updateAgent( @Valid @RequestBody Agent userForm){
//                              @PathVariable String matricule) {

		System.out.println("MErge Agent");
        Agent agent = agentDao.findByMatricule(userForm.getMatricule());
        if (agent != null) {

            userForm.setId(agent.getId());
//            agentDao.saveAndFlush(userForm);


            userService.signup(userForm);
//            userService.signup(modelMapper.map(userForm, Agent.class));


        }
//        else {
//
//            agentDao.saveAndFlush(agent);
//        }
        return agent;
	}

//	@PatchMapping(value = "/mergeAgent")
////	@PatchMapping(value = "/mergeAgent/{matricule}")
//	public Agent updatePartielAgent( @Valid @RequestBody Agent userForm) {
//		//,
////                                     @PathVariable String matricule) {
//
//        Agent agent = agentDao.findByMatricule(userForm.getMatricule());
////        System.out.println(agent.size());
//        if (agent != null) {
//
//            userForm.setId(agent.getId());
//            userForm.setPassword(agent.getPassword());
//            agentDao.saveAndFlush(userForm);
//
//        } else {
//
//            agentDao.saveAndFlush(agent);
//        }
//        return agent;
//	}
//



}
