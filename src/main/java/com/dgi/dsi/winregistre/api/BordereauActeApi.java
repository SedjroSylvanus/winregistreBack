package com.dgi.dsi.winregistre.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.dgi.dsi.winregistre.WinregistreApplication;
import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.AgentDao;
import com.dgi.dsi.winregistre.entites.*;
import com.dgi.dsi.winregistre.payload.ApiResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dgi.dsi.winregistre.dao.BanqueDao;
import com.dgi.dsi.winregistre.dao.BordereauActeDao;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class BordereauActeApi {

//
//	@Autowired
//	private BordereauActeDao bordereauActeDao;

    @Autowired
    private BordereauActeDao bordereauActeDao;

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private ActeDao acteDao;

    Logger log = LogManager.getLogger(WinregistreApplication.class);


    @GetMapping(value = "/listBordereauActes")
    public List<BordereauActe> getBordereauActes() {
        return bordereauActeDao.findAll();
    }

    @GetMapping(value = "/listBordereauActeAuthentiqueNonValide")
    public List<BordereauActe> getlistBordereauActeAuthentiqueNonValide() {
        return bordereauActeDao.ListeBordereauActeAuthentiqueValide(false,false,"%AUT%");
    }

  @GetMapping(value = "/listBordereauActeAuthentiqueNonTransferer")
    public List<BordereauActe> getlistBordereauActeAuthentiqueNonTransferer() {
        return bordereauActeDao.ListeBordereauActeAuthentiqueTransferer(false,"%AUT%");
    }


    @GetMapping(value = "/oneBordereauActeByNumero/{numero}")
    public BordereauActe oneBordereauActeById(@PathVariable String numero) {
        // contactRepository.delete(id);

        if( numero.isEmpty() || numero == null){
            return null;
        }else {
//            System.out.println(11);
            BordereauActe bordereauActe = bordereauActeDao.findByNumeroEquals(numero);

            if (bordereauActe == null) {
                return null;
            } else {
                return bordereauActe;
            }
        }


    }


    @GetMapping(value = "/listBordereauActeParAgent/{matriculeAgent}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<BordereauActe> getListBordereauActeParAgent(@PathVariable String matriculeAgent) {
        // Vonfition pour savoir si un acte est à transférer
        System.out.println("----------------numBordereau-------------------" + matriculeAgent);
        if (matriculeAgent.isEmpty() || matriculeAgent == null) {
            return null;
        } else {

            Agent agent = agentDao.findByMatriculeEquals(matriculeAgent);

            if (agent != null) {
                System.out.println("-----------agent.getId()----------" + agent.getId());
                List<BordereauActe> BoredereauActes = bordereauActeDao.listeBordereauActeAgent("%AUT%", agent.getId());
                if (BoredereauActes.isEmpty()) {
                    System.out.println("<<<<<<<<<<<>>>>><" + 1);
                    return null;
//                    new ResponseEntity(new ApiResponse(false, "Aucun Bordereau ne correspond!!"),
//                            HttpStatus.OK);
                } else {
                    System.out.println("<<<<<<<<<<<<<<<<<>>>" + 2);
                    return BoredereauActes;
//                    return null;
                }


            } else {


//                return new ResponseEntity(new ApiResponse(false, "Ce matricule ne correspond à aucun agent!!"),
//                        HttpStatus.OK);
                return null;


            }


        }

    }


    @GetMapping(value = "/lastNumberBordereauActes/{numBordereau}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public ResponseEntity<?> getNumLastBordereauActes(@PathVariable String numBordereau) {

        System.out.println("----------------numBordereau-------------------" + numBordereau.toUpperCase());
        if( numBordereau.isEmpty() || numBordereau == null){
            return null;
        }else {
            System.out.println("----------------idBordereauActe-------------------");
            Long idBordereauActe = bordereauActeDao.maxIdByBordereau("%" + numBordereau.toUpperCase() + "%");
            System.out.println("----------------idBordereauActe-------------------" + idBordereauActe);
            if(idBordereauActe !=null) {
                System.out.println("----------------idBordereauActe-------------------" + idBordereauActe);
                BordereauActe bordActe = bordereauActeDao.findByIdIs(idBordereauActe);
                Map<String, String> map = new HashMap<>();
                map.put("numeroBordereauActe", bordActe.getNumero());
                return ResponseEntity.ok()
                        .body(map);
            }else{
                System.out.println("----------------idBordereauActe-------------------" );

//            return new ResponseEntity(new ApiResponse(false, "idBordereau Acte est null!!"),
//                    HttpStatus.OK);
                return null;


            }
        }



    }

    @GetMapping(value = "/lastBordereauActes/{numBordereau}")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public ResponseEntity<?> getLastBordereauActes(@PathVariable String numBordereau) {

        System.out.println("----------------numBordereau-------------------" + numBordereau.toUpperCase());
        if( numBordereau.isEmpty() || numBordereau == null){
            return null;
        }else {
            Long idBordereauActe = bordereauActeDao.maxIdByBordereau("%" + numBordereau.toUpperCase() + "%");
            System.out.println("----------------idBordereauActe-------------------" +idBordereauActe);
            if(idBordereauActe !=null) {
                System.out.println("----------------idBordereauActe-------------------" + idBordereauActe);
                BordereauActe bordActe = bordereauActeDao.findByIdIs(idBordereauActe);
                if(bordActe == null){
                    return new ResponseEntity(new ApiResponse(false, "Bordereau Acte est introuvable!!"),
                            HttpStatus.OK);
                }else{
                    Map<String, String> map = new HashMap<>();
                    map.put("numeroBordereauActe", bordActe.getNumero());

                    return ResponseEntity.ok().body(bordActe);

                }



            }else{
                System.out.println("----------------idBordereauActe-------------------" );
//            return new ResponseEntity(new ApiResponse(false, "idBordereau Acte est null!!"),
//                    HttpStatus.OK);
                return null;

            }
        }



    }


    @PostMapping("/ajoutBordereauActe")
    public BordereauActe ajoutBordereauActe(@RequestBody BordereauActe userForm) {


//        System.out.println("-----------Je suis bien dans l'ajout de Bordereau acte--------------------");
        if(userForm == null){
            return null;
        }else{
            BordereauActe userSearch = bordereauActeDao.findByIdIs(userForm.getId());

            if (userSearch == null) {
                bordereauActeDao.save(userForm);
            } else {

                userForm.setId(userSearch.getId());
                bordereauActeDao.save(userForm);
            }


            return userForm;

        }

    }


    @DeleteMapping(value = "/deleteBordereauActe")
    public boolean deleteBordereauActe(@Valid @RequestBody BordereauActe bordereauActe) {
        // contactRepository.delete(id);

        if(bordereauActe == null){
            return false;
        }else{
            BordereauActe exercice = bordereauActeDao.findByIdIs(bordereauActe.getId());

            if (exercice != null) {
                bordereauActeDao.delete(exercice);
                return true;
            } else {

                return false;
            }
        }



    }

    @PutMapping(value = "/mergePBordereauActe")
    public BordereauActe updateBordereauActe(@Valid @RequestBody BordereauActe bordereauActe) {

        BordereauActe exercice = bordereauActeDao.findByIdIs(bordereauActe.getId());
        if (exercice != null) {
            exercice.setId(bordereauActe.getId());
            return bordereauActeDao.save(exercice);
        }
        return exercice;
    }

    @PatchMapping(value = "/mergeBordereauActe")
    public BordereauActe updatePartielBordereauActe(@Valid @RequestBody BordereauActe bordereauActe) {

        BordereauActe borActe = new BordereauActe();
        if(bordereauActe.getId()  !=0.){
        BordereauActe bordereauActeR = bordereauActeDao.findByIdIs(bordereauActe.getId());
        if (bordereauActeR != null) {
            bordereauActeR.setId(bordereauActe.getId());
            borActe =  bordereauActeDao.save(bordereauActeR);
        }
        }else{
            log.info("Modification non autorisée car l'Objet n'existe pas");
            borActe = null;
        }
        return borActe;
    }

}
