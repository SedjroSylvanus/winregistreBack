package com.dgi.dsi.winregistre.payload;

import com.dgi.dsi.winregistre.dao.PenaliteAmendeDao;
import com.dgi.dsi.winregistre.entites.Commune;
import com.dgi.dsi.winregistre.entites.NatureActe;
import com.dgi.dsi.winregistre.entites.TypePenaliteAmende;

import java.time.LocalDate;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

public class EntrePaiement {

    private Commune communeActe;
    private LocalDate dateActe;
    private LocalDate dateEnregistrement;
    private NatureActe natureActe;
    private TypePenaliteAmende typePenaliteAmende;

    private Double montantActe;

    public EntrePaiement() {
    }

    public EntrePaiement(Commune communeActe, LocalDate dateActe, LocalDate dateEnregistrement, NatureActe natureActe, TypePenaliteAmende typePenaliteAmende) {
        this.communeActe = communeActe;
        this.dateActe = dateActe;
        this.dateEnregistrement = dateEnregistrement;
        this.natureActe = natureActe;
        this.typePenaliteAmende = typePenaliteAmende;
    }

    public Commune getCommuneActe() {
        return communeActe;
    }

    public void setCommuneActe(Commune communeActe) {
        this.communeActe = communeActe;
    }

    public LocalDate getDateActe() {
        return dateActe;
    }

    public void setDateActe(LocalDate dateActe) {
        this.dateActe = dateActe;
    }

    public LocalDate getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(LocalDate dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public NatureActe getNatureActe() {
        return natureActe;
    }

    public void setNatureActe(NatureActe natureActe) {
        this.natureActe = natureActe;
    }

    public TypePenaliteAmende getTypePenaliteAmende() {
        return typePenaliteAmende;
    }

    public void setTypePenaliteAmende(TypePenaliteAmende typePenaliteAmende) {
        this.typePenaliteAmende = typePenaliteAmende;
    }

    public Double getMontantActe() {
        return montantActe;
    }

    public void setMontantActe(Double montantActe) {
        this.montantActe = montantActe;
    }
}

