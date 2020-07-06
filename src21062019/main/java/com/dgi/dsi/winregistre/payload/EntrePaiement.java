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
    private String dateActe;
    private String dateEnregistrement;
    private NatureActe natureActe;
    private Timbre timbre;
//    private TypePenaliteAmende typePenaliteAmende;

    private Double montantActe;

    public EntrePaiement() {
    }

    public EntrePaiement(Commune communeActe, String dateActe, String dateEnregistrement, NatureActe natureActe) {
        this.communeActe = communeActe;
        this.dateActe = dateActe;
        this.dateEnregistrement = dateEnregistrement;
        this.natureActe = natureActe;
//        this.typePenaliteAmende = typePenaliteAmende;
    }

    public Commune getCommuneActe() {
        return communeActe;
    }

    public void setCommuneActe(Commune communeActe) {
        this.communeActe = communeActe;
    }

    public String getDateActe() {
        return dateActe;
    }

    public void setDateActe(String dateActe) {
        this.dateActe = dateActe;
    }

    public String getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(String dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public NatureActe getNatureActe() {
        return natureActe;
    }

    public void setNatureActe(NatureActe natureActe) {
        this.natureActe = natureActe;
    }



    public Double getMontantActe() {
        return montantActe;
    }

    public void setMontantActe(Double montantActe) {
        this.montantActe = montantActe;
    }

    public Timbre getTimbre() {
        return timbre;
    }

    public void setTimbre(Timbre timbre) {
        this.timbre = timbre;
    }
}

