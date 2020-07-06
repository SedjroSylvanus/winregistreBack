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
    private Double montantTotalTimbre;

    public SortiePaiement() {
    }

    public SortiePaiement(Double penalite, Double amende, Double redevance, Double droitSimple, Double manqueeGain) {
        this.penalite = penalite;
        this.amende = amende;
        this.redevance = redevance;
        this.droitSimple = droitSimple;
        this.manqueeGain = manqueeGain;
//        this.montantTotalTimbre = montantTotalTimbre;
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

    public Double getMontantTotalTimbre() {
        return montantTotalTimbre;
    }

    public void setMontantTotalTimbre(Double montantTotalTimbre) {
        this.montantTotalTimbre = montantTotalTimbre;
    }
}
