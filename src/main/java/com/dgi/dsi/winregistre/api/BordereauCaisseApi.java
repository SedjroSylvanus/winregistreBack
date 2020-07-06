package com.dgi.dsi.winregistre.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.dgi.dsi.winregistre.entites.BordereauActe;
import com.dgi.dsi.winregistre.entites.Quittance;
import org.springframework.beans.factory.annotation.Autowired;

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
import com.dgi.dsi.winregistre.dao.BordereauCaisseDao;
import com.dgi.dsi.winregistre.entites.Banque;
import com.dgi.dsi.winregistre.entites.BordereauCaisse;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class BordereauCaisseApi {


//	@Autowired
//	private ExerciceDao bordereaCaisseDao;
	
	@Autowired
	private BordereauCaisseDao bordereaCaisseDao;


//		@GetMapping(value = "/oneBordereauCaisseByINumero/{numero}")
//	public BordereauCaisse oneBordereauCaisseById(@PathVariable String numero) {
//		// contactRepository.delete(id);
//
//			BordereauCaisse bordereauCaisse = bordereaCaisseDao.findByNumeroEquals(numero);
//
//
//		return bordereauCaisse;
//
//	}


//	@GetMapping(value = "/lastNumberBordereauActe/{numBordereauCaisse}/{codeNatureImpot}")
////	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
//	public ResponseEntity<?> getNumLastQuittancesBordereauCaisse(@PathVariable String numBordereauCaisse, @PathVariable String codeNatureImpot) {
//
//		System.out.println("----------------numBordereauCaisse-------------------"+ numBordereauCaisse);
//
//		BordereauCaisse bordereauCaisse = bordereauCaisseDao.findByNumeroEquals(numBordereauCaisse);
//		Map<String, String> map = new HashMap();
//
//		if (bordereauCaisse != null){
//			System.out.println("-----------bordereauCaisse.getId()----------"+ bordereauCaisse.getId());
//			Long idBordereauCaisse = quittanceDao.maxIdByBordereauCaisse(bordereauCaisse.getId(), "%"+codeNatureImpot+"%");
////        idBordereauActe = idBordereauActe==null ?1L: idBordereauActe;
//			System.out.println("----------------idBordereauCaisse-------------------"+idBordereauCaisse);
//
//			if (idBordereauCaisse != null){
//				Quittance quittance = quittanceDao.findOne(idBordereauCaisse);
//
//				System.out.println("----------------getNumeroQuittance-------------------"+quittance.getNumeroQuittance());
//				String[] decoupe = quittance.getNumeroQuittance().split("_");
//
//				System.out.println(quittance.getNumeroQuittance());
////				return decoupe[1];
//				map.put("numeroQuittance",quittance.getNumeroQuittance());
////				return quittance.getNumeroQuittance();
//				return ResponseEntity.ok()
//						.body(map);
//
//			}else{
//				return ResponseEntity.notFound().build();
//
//			}
//
//		}else{
//			return ResponseEntity.notFound().build();
//
//
//		}
//
//
//
//	}





	@GetMapping(value = "/oneBordereauCaisseByNumero/{numero}")
    public BordereauCaisse oneBordereauCaisseById(@PathVariable String numero) {
        // contactRepository.delete(id);

        if(numero.isEmpty() || numero == null){
            return null;
        }else{
            BordereauCaisse bordereauCaisse = bordereaCaisseDao.findByNumeroEquals(numero);

            if(bordereauCaisse==null){
                return null;
            }else{
                return bordereauCaisse;
            }
        }


    }


	@GetMapping(value = "/listBordereauCaisses")
	public List<BordereauCaisse> getBordereauCaisses() {
		return bordereaCaisseDao.findAll();
	}

	@PostMapping("/ajoutBordereauCaisse")
	public BordereauCaisse ajoutBordereauCaisse(@RequestBody BordereauCaisse userForm) {

        if( userForm == null){
            return null;
        }else{
            BordereauCaisse userSearch = bordereaCaisseDao.findByIdIs(userForm.getId());

            if (userSearch == null) {
                bordereaCaisseDao.saveAndFlush(userForm);
            } else {
                throw new RuntimeException(userSearch + "Exercice inexistant");
            }

            return userForm;
        }


	}
	
	
	@DeleteMapping(value = "/deleteBordereauCaisse/{id}")
	public boolean deleteBordereauCaisse(@PathVariable Long id) {
		// contactRepository.delete(id);
		
		BordereauCaisse bordereaCaisse = bordereaCaisseDao.findByIdIs(id);
		
		if (bordereaCaisse != null) {
			bordereaCaisseDao.delete(bordereaCaisse);
			return true;
		}else {

			return  false;
		}


	}

	@PutMapping(value = "/mergePBordereauCaisse/{id}")
	public BordereauCaisse updateBordereauCaisse(@PathVariable Long id) {

		BordereauCaisse bordereaCaisse = bordereaCaisseDao.findByIdIs(id);
		if (bordereaCaisse != null) {
			bordereaCaisse.setId(id);
			return bordereaCaisseDao.save(bordereaCaisse);
		}
		return bordereaCaisse;
	}
	
	@PatchMapping(value = "/mergeBordereauCaisse")
	public BordereauCaisse updatePartielBanque( @RequestBody BordereauCaisse bordereauCaisse) {

		BordereauCaisse bordereauCaisseRech = bordereaCaisseDao.findByIdIs(bordereauCaisse.getId());
		if (bordereauCaisseRech != null) {
			bordereauCaisse.setId(bordereauCaisseRech.getId());
			return bordereaCaisseDao.save(bordereauCaisse);
		}else{
			bordereaCaisseDao.save(bordereauCaisse);
		}
		return bordereauCaisse;
	}

}
