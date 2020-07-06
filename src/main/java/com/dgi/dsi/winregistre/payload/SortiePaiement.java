package com.dgi.dsi.winregistre.payload;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

public class SortiePaiement {
    private Double penalite;
    private Double amende;
    private Double redevance;
    private Double droitSimple;
    private Double manqueeGain;
    private Double valeurTimbre;;
    private Double nombreTimbre;
    private Double montantPaye; //Complément de droit à gérer
    private Double plusValueImmobiliere;

    public SortiePaiement() {

       droitSimple = 0.0;
        penalite = 0.0;
        amende = 0.0;
        redevance = 0.0;
       valeurTimbre = 0.;//A créer dans acte
       nombreTimbre = 1.;//A créer dans acte
        manqueeGain = 0.;
        montantPaye = 0.;
        plusValueImmobiliere = 0.;
    }

    public SortiePaiement(Double plusValueImmobiliere, Double penalite, Double amende, Double redevance, Double droitSimple, Double manqueeGain, Double valeurTimbre, Double nombreTimbre, Double montantPaye) {
        this.penalite = penalite;
        this.amende = amende;
        this.redevance = redevance;
        this.droitSimple = droitSimple;
        this.manqueeGain = manqueeGain;
        this.valeurTimbre = valeurTimbre;
        this.nombreTimbre = nombreTimbre;
        this.montantPaye = montantPaye;
    }

    public Double getPenalite() {
        return penalite;
    }

    public void setPenalite(Double penalite) {
        this.penalite = penalite;
    }

    public Double getAmende() {
        return amende;
    }

    public void setAmende(Double amende) {
        this.amende = amende;
    }

    public Double getRedevance() {
        return redevance;
    }

    public void setRedevance(Double redevance) {
        this.redevance = redevance;
    }

    public Double getDroitSimple() {
        return droitSimple;
    }

    public void setDroitSimple(Double droitSimple) {
        this.droitSimple = droitSimple;
    }

    public Double getManqueeGain() {
        return manqueeGain;
    }

    public void setManqueeGain(Double manqueeGain) {
        this.manqueeGain = manqueeGain;
    }

    public Double getValeurTimbre() {
        return valeurTimbre;
    }

    public void setValeurTimbre(Double valeurTimbre) {
        this.valeurTimbre = valeurTimbre;
    }

    public Double getNombreTimbre() {
        return nombreTimbre;
    }

    public void setNombreTimbre(Double nombreTimbre) {
        this.nombreTimbre = nombreTimbre;
    }

    public Double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

	public Double getPlusValueImmobiliere() {
		return plusValueImmobiliere;
	}

	public void setPlusValueImmobiliere(Double plusValueImmobiliere) {
		this.plusValueImmobiliere = plusValueImmobiliere;
	}
    
    
}
