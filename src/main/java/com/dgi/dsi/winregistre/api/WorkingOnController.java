package com.dgi.dsi.winregistre.api;



import com.dgi.dsi.winregistre.entites.traitementCours.WorkingOn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/working-on")
@Api(value = "WorkingOn", description = "API d'accès pour connaître toutes les demandes en cours de traitement chez un utilisateur", tags = {"Demande en cours d'enregistrement d'acte"})
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class WorkingOnController {


    @ApiOperation(value = "Retrourne la liste des demandes ouverte chez les agents ")
    @GetMapping("")
    //  @PreAuthorize("hasRole('ROLE_USER')")
    public List<WorkingOn> getAll() {
        return WorkingOnSingleton.getInstance().getWorkingOns();
    }


    @ApiOperation(value = "Libère une demande en cours de traitement chez un agent ")
    @PostMapping("/liberate")
    //  @PreAuthorize("hasRole('ROLE_USER')")
    public boolean liberateAWorkingOn(WorkingOn workingOn) {
        return WorkingOnSingleton.getInstance().deleteAWorkingOn(workingOn);
    }

    @ApiOperation(value = "Libère une liste de  demande en cours de traitement chez des agents")
    @PostMapping("/liberate-selected")
    //  @PreAuthorize("hasRole('ROLE_USER')")
    public boolean liberateAWorkingOn(List<WorkingOn> workingOns) {
        return WorkingOnSingleton.getInstance().deleteAllSelectedWorkingOn(workingOns);
    }

    @ApiOperation(value = "Libère toutes les  demandes en cours de traitement chez des agents ")
    @GetMapping("/liberate")
    //  @PreAuthorize("hasRole('ROLE_USER')")
    public boolean liberateAll() {
        return WorkingOnSingleton.getInstance().deleteAll();
    }


}
